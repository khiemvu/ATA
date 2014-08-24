package com.us.ata.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import com.us.ata.R;
import com.us.ata.utils.Constant;

/**
 * User: Khiemvx
 * Date: 8/24/2014
 */
public class ATAFleetActivity extends Activity implements View.OnClickListener
{
    private Button btATASms, btDialNow, btEmailRepairer, btGetDirection;


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
                break;
            case R.id.ata_fleet_btEmailRepairer:
                callAppForSentEmail();
                break;
            case R.id.ata_fleet_btSmsATA:
                break;
            case R.id.ata_fleet_btGetDirection:
                Intent getDirection = new Intent(this, MapLocationActivity.class);
                startActivity(getDirection);
                break;
        }
    }

    public void callAppForSentEmail()
    {
        String subject = "ATT App Accident...";
        String message =
                "Accident Date: %s\n"
                        + "Accident Time: %s\n\n"

                        + "Name: %s\n"
                        + "Phone: %s\n"
                        + "REGO: %s\n"
                        + "Make: %s\n"
                        + "Model: %s\n\n"

                        + "Insurance Company: %s\n"
                        + "Policy: %s\n"
                        + "Insurance Phone: %s\n"
                        + "Broker Name: %s\n";


        message = String.format(message, Constant.BLANK,
                Constant.BLANK,
                Constant.BLANK,
                Constant.BLANK,
                Constant.BLANK,
                Constant.BLANK,
                Constant.BLANK,
                Constant.BLANK,
                Constant.BLANK,
                Constant.BLANK,
                Constant.BLANK);

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);
        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent, "Complete action using: "));
    }

}
