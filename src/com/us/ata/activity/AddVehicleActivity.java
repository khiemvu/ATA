package com.us.ata.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.us.ata.R;
import com.us.ata.model.Vehicle;
import com.us.ata.utils.Utils;

import java.sql.SQLException;
import java.util.UUID;

/**
 * User: Khiemvx
 * Date: 8/22/14
 */
public class AddVehicleActivity extends Activity implements View.OnClickListener
{
    public static final int ADD_TIME_REMINDER = 1;
    protected Button btSave;
    protected ImageView btBack;
    private EditText etName, etRego, etMake, etModel, etPhone, etAddress,
            etInsuranceComany, etInsurancePhone, etPolicy, etBroker;
    private TextView etRegoDate;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_vehicle);
        initViewAndAction();
    }

    private void initViewAndAction()
    {
        btSave = (Button) findViewById(R.id.add_vehicle_btSave);
        btBack = (ImageView) findViewById(R.id.add_vehicle_btBack);
        etName = (EditText) findViewById(R.id.add_vehicle_etName);
        etRego = (EditText) findViewById(R.id.add_vehicle_etRego);
        etMake = (EditText) findViewById(R.id.add_vehicle_etMake);
        etModel = (EditText) findViewById(R.id.add_vehicle_etModel);
        etPhone = (EditText) findViewById(R.id.add_vehicle_etPhone);
        etAddress = (EditText) findViewById(R.id.add_vehicle_etAddress);
        etRegoDate = (TextView) findViewById(R.id.add_vehicle_etRegoDate);
        etInsuranceComany = (EditText) findViewById(R.id.add_vehicle_etInsuranceCompany);
        etInsurancePhone = (EditText) findViewById(R.id.add_vehicle_etInsurancePhone);
        etPolicy = (EditText) findViewById(R.id.add_vehicle_etPolicy);
        etBroker = (EditText) findViewById(R.id.add_vehicle_etBroker);

        etRegoDate.setOnClickListener(this);
        btSave.setOnClickListener(this);
        btBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.add_vehicle_etRegoDate:
                Intent regoReminder = new Intent(this, RegoReminderActivity.class);
                regoReminder.putExtra("addReminder", "RegoReminderActivity");
                startActivityForResult(regoReminder, ADD_TIME_REMINDER);
                break;
            case R.id.add_vehicle_btSave:
                Vehicle vehicle = getVehicle();
                try
                {
                    Utils.getHelper(this).getVehicleDAO().createIfNotExists(vehicle);
                }
                catch (SQLException e)
                {
                    Log.e("all_in_one", e.getMessage());
                }
                finish();
                break;
            case R.id.add_vehicle_btBack:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
        {
            if (requestCode == ADD_TIME_REMINDER)
            {
                etRegoDate.setText(data.getStringExtra("timeReminderResult"));
            }
        }
    }

    private Vehicle getVehicle()
    {
        Vehicle vehicle = new Vehicle();
        vehicle.setName(etName.getText().toString());
        vehicle.setRego(etRego.getText().toString());
        vehicle.setMake(etMake.getText().toString());
        vehicle.setModel(etModel.getText().toString());
        vehicle.setYourPhone(etPhone.getText().toString());
        vehicle.setYourAddress(etAddress.getText().toString());
        vehicle.setRegoReminder(etRegoDate.getText().toString());
        vehicle.setInsuranceCompany(etInsuranceComany.getText().toString());
        vehicle.setInsurancePhone(etInsurancePhone.getText().toString());
        vehicle.setInsurancePolicy(etPolicy.getText().toString());
        vehicle.setBroker(etBroker.getText().toString());
        vehicle.setId(UUID.randomUUID().toString());
        return vehicle;
    }


}
