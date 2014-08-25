package com.us.ata.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.us.ata.R;
import com.us.ata.model.Vehicle;
import com.us.ata.utils.Utils;

import java.sql.SQLException;
import java.util.UUID;

/**
 * User: Khiemvx
 * Date: 8/22/14
 */
public class AddOtherVehicleDetailActivity extends Activity implements View.OnClickListener
{
    public static final int ADD_TIME_REMINDER = 1;
    protected Button btSave;
    protected ImageView btBack;
    private EditText etName, etRego, etMake, etModel, etPhone, etAddress,
            etInsuranceComany, etInsurancePhone, etPolicy, etBroker;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_other_vehicle_form);
        initViewAndAction();
    }

    private void initViewAndAction()
    {
        btSave = (Button) findViewById(R.id.add_other_vehicle_form_btSave);
        btBack = (ImageView) findViewById(R.id.add_other_vehicle_form_btBack);
        etName = (EditText) findViewById(R.id.add_other_vehicle_form_etName);
        etRego = (EditText) findViewById(R.id.add_other_vehicle_form_etRego);
        etMake = (EditText) findViewById(R.id.add_other_vehicle_form_etMake);
        etModel = (EditText) findViewById(R.id.add_other_vehicle_form_etModel);
        etPhone = (EditText) findViewById(R.id.add_other_vehicle_form_etPhone);
        etAddress = (EditText) findViewById(R.id.add_other_vehicle_form_etAddress);
        etInsuranceComany = (EditText) findViewById(R.id.add_other_vehicle_form_etInsuranceCompany);
        etInsurancePhone = (EditText) findViewById(R.id.add_other_vehicle_form_etInsurancePhone);
        etPolicy = (EditText) findViewById(R.id.add_other_vehicle_form_etPolicy);
        etBroker = (EditText) findViewById(R.id.add_other_vehicle_form_etBroker);

        btSave.setOnClickListener(this);
        btBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.add_other_vehicle_form_btSave:
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
            case R.id.add_other_vehicle_form_btBack:
                finish();
                break;
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
        vehicle.setInsuranceCompany(etInsuranceComany.getText().toString());
        vehicle.setInsurancePhone(etInsurancePhone.getText().toString());
        vehicle.setInsurancePolicy(etPolicy.getText().toString());
        vehicle.setBroker(etBroker.getText().toString());
        vehicle.setId(UUID.randomUUID().toString());
        return vehicle;
    }


}
