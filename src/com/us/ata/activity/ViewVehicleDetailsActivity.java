package com.us.ata.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.us.ata.R;

/**
 * User: Khiemvx
 * Date: 8/22/14
 */
public class ViewVehicleDetailsActivity extends Activity implements View.OnClickListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_vehicle_detail);
    }

    @Override
    public void onClick(View view)
    {

    }
}
