package com.us.ata.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import com.us.ata.R;
import com.us.ata.adapter.WitnessAdapter;
import com.us.ata.model.Witness;
import com.us.ata.ormlite.DatabaseHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jodie Pham on 8/24/14.
 */
public class MyWitnessActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener
{
    private Button btBack;
    private ListView lvWitness;
    private WitnessAdapter adapter;
    private DatabaseHelper databaseHelper;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_witness);
        databaseHelper = new DatabaseHelper(this);
        List<Witness> witnesses = new ArrayList<Witness>();
        try
        {
            witnesses = databaseHelper.getWitnessDAO().queryForAll();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        adapter = new WitnessAdapter(this, witnesses);
        btBack = (Button) findViewById(R.id.my_witness_btBack);
        lvWitness = (ListView) findViewById(R.id.my_list_lvWitness);
        lvWitness.setAdapter(adapter);
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
        Intent intent = new Intent(this,AddWitnessActivity.class);
        Witness witness = (Witness) adapter.getItem(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable("witness",witness);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}