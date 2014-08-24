package com.us.ata.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.us.ata.R;

/**
 * Created by Jodie Pham on 8/24/14.
 */
public class ViewPhotoListActivity extends Activity implements View.OnClickListener
{
    private Button btBack;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_list);
        btBack = (Button) findViewById(R.id.photo_list_btBack);
        btBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.photo_list_btBack:
                finish();
                break;
        }
    }
}