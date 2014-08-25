package com.us.ata.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.us.ata.R;
import com.us.ata.utils.Utils;

/**
 * User: Khiemvx
 * Date: 8/25/2014
 */
public class NeedHelpNowActivity extends Activity implements View.OnClickListener
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.need_help_now);
        initViewAndAction();
    }

    private void initViewAndAction()
    {
        findViewById(R.id.need_help_now_btBack).setOnClickListener(this);
        findViewById(R.id.need_help_now_btYes).setOnClickListener(this);
        findViewById(R.id.need_help_now_btNo).setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.need_help_now_btBack:
                finish();
                break;
            case R.id.need_help_now_btYes:
                Utils.performDial("000", this);
                break;
            case R.id.need_help_now_btNo:
                Intent accidentDetail = new Intent(NeedHelpNowActivity.this, AccidentDetailActivity.class);
                startActivity(accidentDetail);
                finish();
                break;
        }
    }
}
