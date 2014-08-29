package com.us.ata.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.us.ata.R;

/**
 * Created by Jodie Pham on 8/24/14.
 */
public class WitnessActivity extends Activity implements View.OnClickListener
{
    private Button btBack;
    private Button btAddWitness;
    private Button btMyWitness;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.witness);
        btBack = (Button) findViewById(R.id.witness_btBack);
        btAddWitness = (Button) findViewById(R.id.witness_btAddWitness);
        btMyWitness = (Button) findViewById(R.id.witness_btMyWitness);

        btBack.setOnClickListener(this);
        btAddWitness.setOnClickListener(this);
        btMyWitness.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.witness_btBack:
                finish();
                break;
            case R.id.witness_btAddWitness:
                Intent addWitness = new Intent(this,AddWitnessActivity.class);
                startActivity(addWitness);
                finish();
                break;
            case R.id.witness_btMyWitness:
                Intent myWitness = new Intent(this,MyWitnessActivity.class);
                startActivity(myWitness);
                finish();
                break;
        }

    }
}