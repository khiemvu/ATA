package com.us.ata.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import com.us.ata.R;

/**
 * Created by Jodie Pham on 8/24/14.
 */
public class VehicleAccidentsActivity extends Activity implements View.OnClickListener
{
    private Button btBack;
    private Button btAccidentDetail;
    private Button btCallForTow;
    private Button btCallNumber;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vehicle_accident);

        btBack = (Button) findViewById(R.id.vehicle_accident_btBack);
        btAccidentDetail = (Button) findViewById(R.id.vehicle_accident_btAccidentDetail);
        btCallForTow = (Button) findViewById(R.id.vehicle_accident_btCallForTow);
        btCallNumber = (Button) findViewById(R.id.vehicle_accident_btCall000);

        btBack.setOnClickListener(this);
        btAccidentDetail.setOnClickListener(this);
        btCallForTow.setOnClickListener(this);
        btCallNumber.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.vehicle_accident_btBack:
                finish();
                break;
            case R.id.vehicle_accident_btAccidentDetail:
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
                Long section = prefs.getLong("section", 0);
                SharedPreferences.Editor editor =  prefs.edit();
                editor.putLong("section", section + 1);
                editor.commit();
                Intent vehicleDetail = new Intent(this, AccidentDetailActivity.class);
                startActivity(vehicleDetail);
                break;
            case R.id.vehicle_accident_btCallForTow:
                Intent callForTow = new Intent(this, ATAFleetTowActivity.class);
                startActivity(callForTow);
                break;
            case R.id.vehicle_accident_btCall000:
                Intent needHelpNow = new Intent(this, NeedHelpNowActivity.class);
                startActivity(needHelpNow);
                break;
        }
    }
}