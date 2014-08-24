package com.us.ata.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import com.us.ata.R;

/**
 * Created by Jodie Pham on 8/24/14.
 */
public class MyWitnessActivity extends Activity implements View.OnClickListener,AdapterView.OnItemClickListener
{
    private Button btBack;
    private ListView lvWitness;
    private WitnessAdapter adapter;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_witness);
        btBack = (Button)findViewById(R.id.my_witness_btBack);
        lvWitness = (ListView)findViewById(R.id.my_list_lvWitness);
        btBack.setOnClickListener(this);
        lvWitness.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.my_witness_btBack:
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {

    }
}