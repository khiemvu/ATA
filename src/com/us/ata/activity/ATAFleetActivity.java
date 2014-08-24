package com.us.ata.activity;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import com.us.ata.R;

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
                break;
            case R.id.ata_fleet_btSmsATA:
                break;
            case R.id.ata_fleet_btGetDirection:
                break;
        }
    }
}
