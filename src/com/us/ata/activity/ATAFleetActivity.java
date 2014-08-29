package com.us.ata.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.us.ata.R;
import com.us.ata.model.Vehicle;
import com.us.ata.utils.Constant;
import com.us.ata.utils.Utils;

import java.sql.SQLException;
import java.util.List;

/**
 * User: Khiemvx
 * Date: 8/24/2014
 */
public class ATAFleetActivity extends Activity implements View.OnClickListener
{
    private Button btATASms, btDialNow, btEmailRepairer, btGetDirection;
    List<Vehicle> vehicleList;

    @Override
    protected void onStart()
    {
        super.onStart();
        setContentView(R.layout.ata_fleet);
        initViewAndAction();
    }

    private void initViewAndAction()
    {
        btATASms = (Button) findViewById(R.id.ata_fleet_btSmsATA);
        btDialNow = (Button) findViewById(R.id.ata_fleet_btDialNow);
        btEmailRepairer = (Button) findViewById(R.id.ata_fleet_btEmailRepairer);
        btGetDirection = (Button) findViewById(R.id.ata_fleet_btGetDirection);

        btATASms.setOnClickListener(this);
        btDialNow.setOnClickListener(this);
        btEmailRepairer.setOnClickListener(this);
        btGetDirection.setOnClickListener(this);

    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.ata_fleet_btDialNow:
                Utils.performDial("1800633990", this);
                break;
            case R.id.ata_fleet_btEmailRepairer:
                callAppForSentEmail();
                break;
            case R.id.ata_fleet_btSmsATA:
                sendSMS();
                break;
            case R.id.ata_fleet_btGetDirection:
                Intent getDirection = new Intent(this, MapLocationActivity.class);
                startActivity(getDirection);
                break;
        }
    }

    protected void sendSMS()
    {
        Log.i("Send SMS", "");

        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
        smsIntent.setData(Uri.parse("smsto:"));
        smsIntent.setType("vnd.android-dir/mms-sms");

        smsIntent.putExtra("address", new String("0425308069"));
        smsIntent.putExtra("sms_body", "Hi...");
        try
        {
            startActivity(smsIntent);
            finish();
            Log.i("Finished sending SMS...", "");
        }
        catch (android.content.ActivityNotFoundException ex)
        {
            Toast.makeText(getApplicationContext(),
                    "SMS faild, please try again later.", Toast.LENGTH_SHORT).show();
        }
    }


    public void callAppForSentEmail()
    {
        String to[] = {"smash@atafleet.com"};
        String subject = "ATT App Report";
        String message =
                "Name: %s\n"
                        + "Phone: %s\n"
                        + "REGO: %s\n"
                        + "Make: %s\n"
                        + "Model: %s\n\n";
        getAllDataFromDB();
        if (vehicleList != null && vehicleList.size() > 0)
        {
            Vehicle vehicle = vehicleList.get(0);
            message = String.format(message,
                    vehicle.getName(),
                    vehicle.getYourPhone(),
                    vehicle.getRego(),
                    vehicle.getMake(),
                    vehicle.getModel());
        }
        else
        {
            message = String.format(message,
                    Constant.BLANK,
                    Constant.BLANK,
                    Constant.BLANK,
                    Constant.BLANK,
                    Constant.BLANK);
        }

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);
        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent, "email"));
    }

    private void getAllDataFromDB()
    {
        try
        {
            vehicleList = Utils.getHelper(this).getVehicleDAO().queryForAll();
        }
        catch (SQLException e)
        {
            Log.e("all_in_one", e.getMessage());
        }
    }

}
