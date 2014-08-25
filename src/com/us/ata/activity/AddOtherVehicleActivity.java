package com.us.ata.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.us.ata.R;

/**
 * User: Khiemvx
 * Date: 8/25/2014
 */
public class AddOtherVehicleActivity extends Activity implements View.OnClickListener
{
    private Button btBack;
    private Button btAddOtherVehicle;
    private Button btOtherVehicle;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_other_vehicle);
        btBack = (Button) findViewById(R.id.add_other_vehicle_btBack);
        btAddOtherVehicle = (Button) findViewById(R.id.add_other_vehicle_btAddOther);
        btOtherVehicle = (Button) findViewById(R.id.add_other_vehicle_btOther);

        btBack.setOnClickListener(this);
        btAddOtherVehicle.setOnClickListener(this);
        btOtherVehicle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.witness_btBack:
                finish();
                break;
            case R.id.add_other_vehicle_btAddOther:
                Intent addOtherVehicleDetail = new Intent(this, AddOtherVehicleDetailActivity.class);
                startActivity(addOtherVehicleDetail);
                break;
            case R.id.add_other_vehicle_btOther:
                Intent myOtherVehicle = new Intent(this, MyOtherVehicleActivity.class);
                startActivity(myOtherVehicle);
                break;
        }

    }
}
