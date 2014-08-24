package com.us.ata.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.us.ata.R;

/**
 * Created by Jodie Pham on 8/24/14.
 */
public class AddWitnessActivity extends Activity implements View.OnClickListener
{
    private Button btBack;
    private Button btSaveDetail;
    private EditText edtName;
    private EditText edtPhone;
    private EditText edtEmail;
    private EditText edtAddress;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.witness_detail);

        btBack = (Button)findViewById(R.id.witness_detail_btBack);
        btSaveDetail = (Button)findViewById(R.id.witness_detail_btSaveDetail);
        edtName = (EditText)findViewById(R.id.witness_detail_edtWitnessName);
        edtPhone = (EditText)findViewById(R.id.witness_detail_edtWitnessPhone);
        edtEmail = (EditText)findViewById(R.id.witness_detail_edtWitnessEmailId);
        edtAddress = (EditText)findViewById(R.id.witness_detail_edtWitnessAddress);

        btBack.setOnClickListener(this);
        btSaveDetail.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.witness_detail_btBack:
                finish();
                break;
            case R.id.witness_detail_btSaveDetail:
                break;
        }
    }
}