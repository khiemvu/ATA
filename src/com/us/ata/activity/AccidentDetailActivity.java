package com.us.ata.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.us.ata.R;

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
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accident_detail);

        btBack = (Button)findViewById(R.id.accident_detail_btBack);
        btViewDetail = (ImageView)findViewById(R.id.accident_detail_ivMyVehicleDetail);
        btAddOtherVehicle = (ImageView)findViewById(R.id.accident_detail_ivAddOtherVehicleDetails);
        btWitness = (ImageView)findViewById(R.id.accident_detail_ivWitness);
        btPolice = (ImageView)findViewById(R.id.accident_detail_ivPolice);
        btView = (Button)findViewById(R.id.accident_detail_btView);
        btPhoto = (Button)findViewById(R.id.accident_detail_btPhoto);
        btEmailRepair = (Button)findViewById(R.id.accident_detail_btSendEmailMyRepair);

        btBack.setOnClickListener(this);
        btViewDetail.setOnClickListener(this);
        btAddOtherVehicle.setOnClickListener(this);
        btWitness.setOnClickListener(this);
        btPolice.setOnClickListener(this);
        btView.setOnClickListener(this);
        btPhoto.setOnClickListener(this);
        btEmailRepair.setOnClickListener(this);


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
                break;
            case R.id.accident_detail_ivAddOtherVehicleDetails:
                break;
            case R.id.accident_detail_ivWitness:
                Intent witness = new Intent(this, WitnessActivity.class);
                startActivity(witness);
                break;
            case R.id.accident_detail_ivPolice:
                break;
            case R.id.accident_detail_btView:
                Intent viewPhotoList = new Intent(this, ViewPhotoListActivity.class);
                startActivity(viewPhotoList);
                break;
            case R.id.accident_detail_btPhoto:
                break;
            case R.id.accident_detail_btSendEmailMyRepair:
                break;
        }
    }
}