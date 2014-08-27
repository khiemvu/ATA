package com.us.ata.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    int sizeOfList;
    int count = 0;

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
        if (witnesses.size() == 0)
        {
            findViewById(R.id.my_witness_rlContent).setVisibility(View.GONE);
            findViewById(R.id.my_witness_llButton).setVisibility(View.GONE);
        }
        else
        {
            findViewById(R.id.my_witness_rlContent).setVisibility(View.VISIBLE);
            findViewById(R.id.my_witness_llButton).setVisibility(View.VISIBLE);
        }
        initViewAndAction();
        sizeOfList = witnesses.size();
        if (witnesses != null && witnesses.size() > 0)
        {
            btNext.setVisibility(View.VISIBLE);
            bindDataOnView(0);

        }
    }

    private void bindDataOnView(int position)
    {
        edtName.setText(witnesses.get(position).getName());
        edtPhone.setText(witnesses.get(position).getPhone());
        edtDescription.setText(witnesses.get(position).getDescription());
        edtLocation.setText(witnesses.get(position).getCrashLocation());
    }

    private void initViewAndAction()
    {
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
                if (count > 0)
                {
                    btNext.setVisibility(View.VISIBLE);
                    bindDataOnView(count - 1);
                    count--;
                    if (count == 0)
                    {
                        btPrevious.setVisibility(View.GONE);
                    }
                }
                break;
            case R.id.my_witness_btNext:
                if (sizeOfList > 1 && count < sizeOfList - 1)
                {
                    btPrevious.setVisibility(View.VISIBLE);
                    bindDataOnView(count + 1);
                    count++;
                    if (count == sizeOfList - 1)
                    {
                        btNext.setVisibility(View.GONE);
                    }
                }
                break;
            case R.id.my_witness_btDelete:
                try
                {
                    databaseHelper.getWitnessDAO().delete(witnesses.get(count));
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
                    witness.setId(witnesses.get(count).getId());
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