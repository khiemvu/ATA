package com.us.ata.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.us.ata.R;

public class MainActivity extends Activity implements View.OnClickListener
{
    private Button btVehicleDetail, btVehicleAccident, btVehicleServicing, btContactFTA;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_service);
//        initViewAndAction();
    }

    private void initViewAndAction()
    {
        btVehicleDetail = (Button) findViewById(R.id.bt_vehicleDetail);
        btVehicleAccident = (Button) findViewById(R.id.bt_vehicleAccident);
        btVehicleServicing = (Button) findViewById(R.id.bt_vehicleServicing);
        btContactFTA = (Button) findViewById(R.id.bt_ContactFTAFleet);

        btVehicleDetail.setOnClickListener(this);
        btVehicleAccident.setOnClickListener(this);
        btVehicleServicing.setOnClickListener(this);
        btContactFTA.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.bt_vehicleDetail:
                Intent vehicleDetail = new Intent(this, VehicleDetailsActivity.class);
                startActivity(vehicleDetail);
                break;
        }
    }
}
