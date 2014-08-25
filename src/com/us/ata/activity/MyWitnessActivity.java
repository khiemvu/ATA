package com.us.ata.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
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
public class MyWitnessActivity extends Activity implements View.OnClickListener
{
    private Button btBack;
    private WitnessAdapter adapter;
    private DatabaseHelper databaseHelper;
    private Button btPrevious;
    private Button btNext;
    private Button btSave;
    private Button btDelete;

    private EditText edtName;
    private EditText edtPhone;
    private EditText edtDescription;
    private EditText edtLocation;
    List<Witness> witnesses = new ArrayList<Witness>();
    private int i = 0;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_witness);
        databaseHelper = new DatabaseHelper(this);
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
        btPrevious = (Button) findViewById(R.id.my_witness_btPrevious);
        btNext = (Button) findViewById(R.id.my_witness_btNext);
        btDelete = (Button) findViewById(R.id.my_witness_btDelete);
        btSave = (Button) findViewById(R.id.my_witness_btSave);

        edtName = (EditText) findViewById(R.id.my_witness_edtName);
        edtPhone = (EditText) findViewById(R.id.my_witness_edtPhone);
        edtDescription = (EditText) findViewById(R.id.my_witness_edtDescription);
        edtLocation = (EditText) findViewById(R.id.my_witness_edtLocation);

        if (witnesses != null && witnesses.size() > 0)
        {
            edtName.setText(witnesses.get(0).getName());
            edtPhone.setText(witnesses.get(0).getPhone());
            edtDescription.setText(witnesses.get(0).getDescription());
            edtLocation.setText(witnesses.get(0).getCrashLocation());
        }

        if (witnesses.size() == 1)
        {
            btNext.setVisibility(View.GONE);
            btPrevious.setVisibility(View.GONE);
        }
        else if (witnesses.size() == 0)
        {
            btNext.setVisibility(View.GONE);
            btPrevious.setVisibility(View.GONE);
        }
        else
        {
            btNext.setVisibility(View.VISIBLE);
            btPrevious.setVisibility(View.VISIBLE);
        }

        btBack.setOnClickListener(this);
        btPrevious.setOnClickListener(this);
        btNext.setOnClickListener(this);
        btDelete.setOnClickListener(this);
        btSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.my_witness_btBack:
                finish();
                break;
            case R.id.my_witness_btPrevious:
                if (i != 0)
                {
                    i--;
                }
                edtName.setText(witnesses.get(i).getName());
                edtPhone.setText(witnesses.get(i).getPhone());
                edtDescription.setText(witnesses.get(i).getDescription());
                edtLocation.setText(witnesses.get(i).getCrashLocation());
                if (i >= witnesses.size() - 1)
                {
                    btNext.setVisibility(View.GONE);
                }
                else
                {
                    btNext.setVisibility(View.VISIBLE);
                }
                if (i < 1)
                {
                    btPrevious.setVisibility(View.GONE);
                }
                else
                {
                    btPrevious.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.my_witness_btNext:
                if (i < witnesses.size() - 1)
                {
                    i++;
                }
                edtName.setText(witnesses.get(i).getName());
                edtPhone.setText(witnesses.get(i).getPhone());
                edtDescription.setText(witnesses.get(i).getDescription());
                edtLocation.setText(witnesses.get(i).getCrashLocation());
                if (i >= witnesses.size() - 1)
                {
                    btNext.setVisibility(View.GONE);
                }
                else
                {
                    btNext.setVisibility(View.VISIBLE);
                }
                if (i < 1)
                {
                    btPrevious.setVisibility(View.GONE);
                }
                else
                {
                    btPrevious.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.my_witness_btDelete:
                try
                {
                    databaseHelper.getWitnessDAO().delete(witnesses.get(i));
                    finish();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
                break;
            case R.id.my_witness_btSave:
                try
                {
                    Witness witness = new Witness();
                    witness.setId(witnesses.get(i).getId());
                    witness.setName(edtName.getText().toString());
                    witness.setDescription(edtDescription.getText().toString());
                    witness.setPhone(edtPhone.getText().toString());
                    witness.setCrashLocation(edtLocation.getText().toString());
                    databaseHelper.getWitnessDAO().update(witness);
                    finish();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
                break;
        }
    }
}