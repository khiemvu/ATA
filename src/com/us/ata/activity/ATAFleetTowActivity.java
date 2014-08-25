package com.us.ata.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.us.ata.R;
import com.us.ata.utils.Utils;

/**
 * User: Khiemvx
 * Date: 8/25/2014
 */
public class ATAFleetTowActivity extends Activity implements View.OnClickListener
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ata_fleet_tow);
        initViewAndAction();

    }

    private void initViewAndAction()
    {
        findViewById(R.id.ata_fleet_tow_btBack).setOnClickListener(this);
        findViewById(R.id.ata_fleet_tow_llCallTow).setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.ata_fleet_tow_btBack:
                finish();
                break;
            case R.id.ata_fleet_tow_llCallTow:
                Utils.performDial("1800633990", this);
                break;
        }
    }
}
