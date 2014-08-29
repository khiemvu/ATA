package com.us.ata.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.us.ata.R;
import com.us.ata.model.Image;
import com.us.ata.model.Vehicle;
import com.us.ata.model.Witness;
import com.us.ata.ormlite.DatabaseHelper;
import com.us.ata.utils.Constant;
import com.us.ata.utils.SharedPreferencesManager;
import com.us.ata.utils.Utils;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jodie Pham on 8/24/14.
 */
public class AccidentDetailActivity extends Activity implements View.OnClickListener
{
    private Button btBack;
    private ImageView btViewDetail;
    private ImageView btAddOtherVehicle;
    private ImageView btWitness;
    private ImageView btPolice;
    private Button btView;
    private Button btPhoto;
    private Button btEmailRepair;
    private TextView tvDate;
    private TextView tvTime;
    private DatabaseHelper databaseHelper;
    List<Vehicle> vehicleList;
    List<Witness> witnessList;
    SharedPreferences prefs ;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accident_detail);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        databaseHelper = new DatabaseHelper(this);
        btBack = (Button) findViewById(R.id.accident_detail_btBack);
        btViewDetail = (ImageView) findViewById(R.id.accident_detail_ivMyVehicleDetail);
        btAddOtherVehicle = (ImageView) findViewById(R.id.accident_detail_ivAddOtherVehicleDetails);
        btWitness = (ImageView) findViewById(R.id.accident_detail_ivWitness);
        btPolice = (ImageView) findViewById(R.id.accident_detail_ivPolice);
        btView = (Button) findViewById(R.id.accident_detail_btView);
        btPhoto = (Button) findViewById(R.id.accident_detail_btPhoto);
        btEmailRepair = (Button) findViewById(R.id.accident_detail_btSendEmailMyRepair);
        tvDate = (TextView) findViewById(R.id.accident_detail_tvDate);
        tvTime = (TextView) findViewById(R.id.accident_detail_tvTime);

        btBack.setOnClickListener(this);
        btViewDetail.setOnClickListener(this);
        btAddOtherVehicle.setOnClickListener(this);
        btWitness.setOnClickListener(this);
        btPolice.setOnClickListener(this);
        btView.setOnClickListener(this);
        btPhoto.setOnClickListener(this);
        btEmailRepair.setOnClickListener(this);

        Time today = new Time(Time.getCurrentTimezone());
        today.setToNow();
        tvDate.setText(today.monthDay + "-" + today.month + "-" + today.year);             // Day of the month (1-31)
        tvTime.setText(today.format("%k-%M-%S"));

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.accident_detail_btBack:
                finish();
                break;
            case R.id.accident_detail_ivMyVehicleDetail:
                Intent viewVehicleDetail = new Intent(this, ViewVehicleDetailsActivity.class);
                startActivity(viewVehicleDetail);
                break;
            case R.id.accident_detail_ivAddOtherVehicleDetails:
                Intent addOtherVehicle = new Intent(this, AddOtherVehicleActivity.class);
                startActivity(addOtherVehicle);
                break;
            case R.id.accident_detail_ivWitness:
                Intent witness = new Intent(this, WitnessActivity.class);
                startActivity(witness);
                break;
            case R.id.accident_detail_ivPolice:
                Intent police = new Intent(this, WitnessActivity.class);
                startActivity(police);
                break;
            case R.id.accident_detail_btView:
                Intent viewPhotoList = new Intent(this, ViewPhotoListActivity.class);
                startActivity(viewPhotoList);
                break;
            case R.id.accident_detail_btPhoto:

                Long section = prefs.getLong("section", 0);
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                Image image = new Image();
                Uri fileUri = Utils.getOutputMediaFileUri(Utils.MEDIA_TYPE_IMAGE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                image.setUrl(fileUri.toString());
                image.setSection(section);
                try
                {
                    databaseHelper.getImageDAO().create(image);
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
                startActivityForResult(intent, Constant.REQUEST_CODE_CAMERA);
                break;
            case R.id.accident_detail_btSendEmailMyRepair:
                try
                {
                    callAppForSentEmail();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
                break;
        }
    }

    public void callAppForSentEmail() throws SQLException
    {
        String to[] = {"smash@atafleet.com"};
        String subject = "ATA App Accident Report";
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
                        + "Broker Name: %s\n\n";

        String otherVehicle =
                getOtherVehicleFormat();

        String witness =
                getWitnessFormat();

        String police =
                "Witness:\n"
                        + "          Witness%s\n"
                        + "          Name: %s\n"
                        + "          Phone: %s\n"
                        + "          Email: %s\n"
                        + "          Location: %s\n";
        getAllOtherVehicleFromDB();
        Vehicle vehicleSelected = null;
        String vehicleId = new SharedPreferencesManager(this).getString(Constant.VEHICLE_ID);
        if (vehicleId != null && !vehicleId.equals(Constant.BLANK))
        {
            vehicleSelected = Utils.getHelper(this).getVehicleDAO().queryForId(vehicleId);
        }
        if (vehicleSelected == null && vehicleList.size() > 0)
        {
            vehicleSelected = vehicleList.get(0);
        }

        message = String.format(message,
                tvDate.getText().toString(),
                tvTime.getText().toString(),
                vehicleSelected == null ? Constant.BLANK : vehicleSelected.getName(),
                vehicleSelected == null ? Constant.BLANK : vehicleSelected.getYourPhone(),
                vehicleSelected == null ? Constant.BLANK : vehicleSelected.getRego(),
                vehicleSelected == null ? Constant.BLANK : vehicleSelected.getMake(),
                vehicleSelected == null ? Constant.BLANK : vehicleSelected.getModel(),
                vehicleSelected == null ? Constant.BLANK : vehicleSelected.getInsuranceCompany(),
                vehicleSelected == null ? Constant.BLANK : vehicleSelected.getInsurancePolicy(),
                vehicleSelected == null ? Constant.BLANK : vehicleSelected.getInsurancePhone(),
                vehicleSelected == null ? Constant.BLANK : vehicleSelected.getBroker()
        );

        String otherVehicleResult = "";
        if (vehicleList != null && vehicleList.size() > 0)
        {
            int count = 0;
            for (Vehicle temp : vehicleList)
            {
                count++;
                otherVehicle = String.format(otherVehicle,
                        Constant.BLANK,
                        count,
                        temp.getName(),
                        temp.getYourPhone(),
                        temp.getRego(),
                        temp.getMake(),
                        temp.getModel(),
                        temp.getInsuranceCompany(),
                        temp.getInsurancePolicy(),
                        temp.getInsurancePhone(),
                        temp.getBroker());
                otherVehicleResult = otherVehicleResult + otherVehicle;
                otherVehicle =
                        getOtherVehicleFormat();
            }
        }
        else
        {
            otherVehicle = String.format(otherVehicle,
                    Constant.BLANK,
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
            otherVehicleResult = otherVehicleResult + otherVehicle;
        }


        getAllWitnessFromDB();
        String witnessResult = "";
        if (witnessList != null && witnessList.size() > 0)
        {
            int count = 0;
            for (Witness temp : witnessList)
            {
                count++;
                witness = String.format(witness,
                        Constant.BLANK,
                        count,
                        temp.getName(),
                        temp.getPhone(),
                        temp.getEmail(),
                        temp.getAddress());
                witnessResult = witnessResult + witness;
                witness =
                        getWitnessFormat();
            }

        }
        else
        {
            witness = String.format(witness,
                    Constant.BLANK,
                    Constant.BLANK,
                    Constant.BLANK,
                    Constant.BLANK,
                    Constant.BLANK,
                    Constant.BLANK);
            witnessResult = witnessResult + witness;
        }
        Intent emailIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
        emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message + otherVehicleResult + witnessResult);
        List<Image> images = new ArrayList<Image>();
        try
        {
            images = databaseHelper.getImageDAO().queryForAll();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        ArrayList<Uri> uris = new ArrayList<Uri>();
        if (images != null && images.size() > 0)
        {
            for (Image image : images)
            {
                File file = new File(image.getUrl().substring(7,image.getUrl().length()));
                uris.add(Uri.fromFile(file));
            }
//            emailIntent.setType("text/plain");
            emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
        }
        emailIntent.setType("text/plain");
        startActivity(Intent.createChooser(emailIntent, "Complete action using: "));
    }

    private String getWitnessFormat()
    {
        return "Witness:%s\n"
                + "          Witness%s\n"
                + "          Name: %s\n"
                + "          Phone: %s\n"
                + "          Email: %s\n"
                + "          Location: %s\n";
    }

    private String getOtherVehicleFormat()
    {
        return "Other Vehicle:%s\n"
                + "          Other vehicle%s\n"
                + "          Name: %s\n"
                + "          Phone: %s\n"
                + "          Reg.No: %s\n"
                + "          Make: %s\n"
                + "          Model: %s\n"
                + "          Insurer: %s\n"
                + "          Policy: %s\n"
                + "          Insurance Phone: %s\n"
                + "          Broker Name: %s\n\n";
    }

    private void getAllWitnessFromDB()
    {
        try
        {
            witnessList = Utils.getHelper(this).getWitnessDAO().queryForAll();
        }
        catch (SQLException e)
        {
            Log.e("all_in_one", e.getMessage());
        }
    }

    private void getAllOtherVehicleFromDB()
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