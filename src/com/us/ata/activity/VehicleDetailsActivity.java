package com.us.ata.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.us.ata.R;

/**
 * User: Khiemvx
 * Date: 8/22/14
 */
public class VehicleDetailsActivity extends Activity implements View.OnClickListener
{
    protected Button btAddVehicle, btViewVehicle;
    private ImageView btBack;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vehicle_detail);
        initViewAndAction();
    }

    private void initViewAndAction()
    {
        btAddVehicle = (Button) findViewById(R.id.vehicle_detail_btAddVehicle);
        btViewVehicle = (Button) findViewById(R.id.vehicle_detail_btViewVehicleDetail);
        btBack = (ImageView) findViewById(R.id.vehicle_detail_btBack);

        btAddVehicle.setOnClickListener(this);
        btViewVehicle.setOnClickListener(this);
        btBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.vehicle_detail_btAddVehicle:
                Intent vehicleDetail = new Intent(this, AddVehicleActivity.class);
                startActivity(vehicleDetail);
                break;
            case R.id.vehicle_detail_btViewVehicleDetail:
                Intent viewVehicleDetail = new Intent(this, ViewVehicleDetailsActivity.class);
                startActivity(viewVehicleDetail);
                break;
            case R.id.vehicle_detail_btBack:
                finish();
                break;
        }
    }
}
