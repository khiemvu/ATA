package com.us.ata.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.us.ata.R;
import com.us.ata.model.Witness;
import com.us.ata.ormlite.DatabaseHelper;

import java.sql.SQLException;

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
    private DatabaseHelper databaseHelper;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.witness_detail);
        databaseHelper = new DatabaseHelper(this);
        btBack = (Button) findViewById(R.id.witness_detail_btBack);
        btSaveDetail = (Button) findViewById(R.id.witness_detail_btSaveDetail);
        edtName = (EditText) findViewById(R.id.witness_detail_edtWitnessName);
        edtPhone = (EditText) findViewById(R.id.witness_detail_edtWitnessPhone);
        edtEmail = (EditText) findViewById(R.id.witness_detail_edtWitnessEmailId);
        edtAddress = (EditText) findViewById(R.id.witness_detail_edtWitnessAddress);
        Witness witness = null;
        if (getIntent().getExtras() != null)
        {
            witness = (Witness) getIntent().getExtras().get("witness");
        }

        if (witness != null)
        {
            edtName.setText(witness.getName());
            edtPhone.setText(witness.getPhone());
            edtEmail.setText(witness.getDescription());
            edtAddress.setText(witness.getCrashLocation());
        }

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
                Witness witness = new Witness();
                witness.setName(edtName.getText().toString());
                witness.setCrashLocation(edtAddress.getText().toString());
                witness.setDescription(edtEmail.getText().toString());
                witness.setPhone(edtPhone.getText().toString());
                try
                {
                    databaseHelper.getWitnessDAO().create(witness);
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
                Intent intent = new Intent(this, MyWitnessActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}