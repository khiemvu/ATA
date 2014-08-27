package com.us.ata.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.us.ata.R;
import com.us.ata.model.Vehicle;
import com.us.ata.utils.Utils;

import java.sql.SQLException;
import java.util.List;

/**
 * User: Khiemvx
 * Date: 8/22/14
 */
public class MyOtherVehicleActivity extends Activity implements View.OnClickListener
{
    private Button btSave, btDelete;
    private ImageButton ibtPrevious, ibtNext;
    private ImageView btBack;
    private EditText etName, etRego, etMake, etModel, etPhone, etAddress,
            etInsuranceComany, etInsurancePhone, etPolicy, etBroker;

    List<Vehicle> vehicleList;
    int sizeOfList;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_other_vehicle);
        getAllDataFromDB();
        initViewAndAction();
        sizeOfList = vehicleList.size();
        if (sizeOfList > 0)
        {
            ibtNext.setVisibility(View.VISIBLE);
            bindDataOnView(0);
        }
        else
        {
            findViewById(R.id.scroll_view).setVisibility(View.GONE);
        }
    }

    private void bindDataOnView(int position)
    {
        Vehicle temp = vehicleList.get(position);
        etName.setText(temp.getName());
        etRego.setText(temp.getRego());
        etMake.setText(temp.getMake());
        etModel.setText(temp.getModel());
        etPhone.setText(temp.getYourPhone());
        etAddress.setText(temp.getYourAddress());
        etInsuranceComany.setText(temp.getInsuranceCompany());
        etPolicy.setText(temp.getInsurancePolicy());
        etInsurancePhone.setText(temp.getInsurancePhone());
        etBroker.setText(temp.getBroker());
    }

    private void getAllDataFromDB()
    {
        try
        {
            vehicleList = Utils.getHelper(this).getVehicleDAO().queryForAll();
        }
        catch (SQLException e)
        {
            Log.e("all_in_one", e.getMessage());
        }
    }

    @Override
    public void onClick(View view)
    {

        switch (view.getId())
        {
            case R.id.my_other_vehicle_ibtNext:
                if (sizeOfList > 1 && count < sizeOfList - 1)
                {
                    ibtPrevious.setVisibility(View.VISIBLE);
                    bindDataOnView(count + 1);
                    count++;
                    if (count == sizeOfList - 1)
                    {
                        ibtNext.setVisibility(View.GONE);
                    }
                }
                break;
            case R.id.my_other_vehicle_ibtPrevious:
                if (count > 0)
                {
                    bindDataOnView(count - 1);
                    count--;
                    if (count == 0)
                    {
                        ibtPrevious.setVisibility(View.GONE);
                    }
                }
                break;
            case R.id.my_other_vehicle_btBack:
                finish();
                break;
            case R.id.my_other_vehicle_btDelete:
                try
                {
                    Utils.getHelper(this).getVehicleDAO().delete(vehicleList.get(count));
                    finish();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
                break;
            case R.id.my_other_vehicle_btSave:
                try
                {
                    Vehicle vehicle = new Vehicle();
                    vehicle.setId(vehicleList.get(count).getId());
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
                    Utils.getHelper(this).getVehicleDAO().update(vehicle);
                    finish();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
                break;
        }

    }

    private void initViewAndAction()
    {
        etName = (EditText) findViewById(R.id.my_other_vehicle_etName);
        etRego = (EditText) findViewById(R.id.my_other_vehicle_etRego);
        etMake = (EditText) findViewById(R.id.my_other_vehicle_etMake);
        etModel = (EditText) findViewById(R.id.my_other_vehicle_etModel);
        etPhone = (EditText) findViewById(R.id.my_other_vehicle_etPhone);
        etInsuranceComany = (EditText) findViewById(R.id.my_other_vehicle_etInsuranceCompany);
        etAddress = (EditText) findViewById(R.id.my_other_vehicle_etAddress);
        etInsurancePhone = (EditText) findViewById(R.id.my_other_vehicle_etInsurancePhone);
        etPolicy = (EditText) findViewById(R.id.my_other_vehicle_etPolicy);
        etBroker = (EditText) findViewById(R.id.my_other_vehicle_etBroker);

        btBack = (ImageView) findViewById(R.id.my_other_vehicle_btBack);
        btDelete = (Button) findViewById(R.id.my_other_vehicle_btDelete);
        btSave = (Button) findViewById(R.id.my_other_vehicle_btSave);
        ibtPrevious = (ImageButton) findViewById(R.id.my_other_vehicle_ibtPrevious);
        ibtNext = (ImageButton) findViewById(R.id.my_other_vehicle_ibtNext);

        btBack.setOnClickListener(this);
        btSave.setOnClickListener(this);
        btDelete.setOnClickListener(this);
        ibtPrevious.setOnClickListener(this);
        ibtNext.setOnClickListener(this);
    }


}
