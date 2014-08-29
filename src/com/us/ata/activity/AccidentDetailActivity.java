package com.us.ata.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.us.ata.R;
import com.us.ata.model.Image;
import com.us.ata.ormlite.DatabaseHelper;
import com.us.ata.utils.Constant;
import com.us.ata.utils.Utils;

import java.sql.SQLException;

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

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accident_detail);

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
        tvTime.setText(today.format("%k:%M:%S"));

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
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                Image image = new Image();
                Uri fileUri = Utils.getOutputMediaFileUri(Utils.MEDIA_TYPE_IMAGE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                image.setUrl(fileUri.toString());
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
                callAppForSentEmail();
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