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
public class ViewVehicleDetailsActivity extends Activity implements View.OnClickListener
{
    private Button btSelect, btSave, btDelete;
    private ImageButton ibtPrevious, ibtNext;
    private ImageView btBack;
    private EditText etName, etRego, etMake, etModel, etPhone, etAddress, etRegoDate,
            etInsuranceComany, etInsurancePhone, etPolicy, etBroker;

    List<Vehicle> vehicleList;
    int sizeOfList;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_vehicle_detail);
        getAllDataFromDB();
        initViewAndAction();
        sizeOfList = vehicleList.size();
        if (sizeOfList > 0)
        {
            ibtNext.setVisibility(View.VISIBLE);
        }
        bindDataOnView(0);
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
        etRegoDate.setText(temp.getRegoReminder());
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
            case R.id.view_vehicle_detail_ibtNext:
                if (sizeOfList > 1 && count < sizeOfList - 1)
                {
                    ibtPrevious.setVisibility(View.VISIBLE);
                    bindDataOnView(count + 1);
                    count++;
                }
                break;
            case R.id.view_vehicle_detail_ibtPrevious:
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
            case R.id.view_vehicle_detail_btBack:
                finish();
                break;
        }

    }

    private void initViewAndAction()
    {
        etName = (EditText) findViewById(R.id.view_vehicl_detail__etName);
        etRego = (EditText) findViewById(R.id.view_vehicle_detail_etRego);
        etMake = (EditText) findViewById(R.id.view_vehicle_detail_etMake);
        etModel = (EditText) findViewById(R.id.view_vehicle_detail_etModel);
        etPhone = (EditText) findViewById(R.id.view_vehicle_detail_etPhone);
        etInsuranceComany = (EditText) findViewById(R.id.view_vehicle_detail_etInsuranceCompany);
        etAddress = (EditText) findViewById(R.id.view_vehicle_detail_etAddress);
        etRegoDate = (EditText) findViewById(R.id.view_vehicle_detail_etRegoDate);
        etInsurancePhone = (EditText) findViewById(R.id.view_vehicle_detail_etRegoDate);
        etPolicy = (EditText) findViewById(R.id.view_vehicle_detail_etPolicy);
        etBroker = (EditText) findViewById(R.id.view_vehicle_detail_etBroker);

        btBack = (ImageView) findViewById(R.id.view_vehicle_detail_btBack);
        btDelete = (Button) findViewById(R.id.view_vehicle_detail_btDelete);
        btSave = (Button) findViewById(R.id.view_vehicle_detail_btSave);
        btSelect = (Button) findViewById(R.id.view_vehicle_detail_btSelect);
        ibtPrevious = (ImageButton) findViewById(R.id.view_vehicle_detail_ibtPrevious);
        ibtNext = (ImageButton) findViewById(R.id.view_vehicle_detail_ibtNext);

        btBack.setOnClickListener(this);
        btSave.setOnClickListener(this);
        btDelete.setOnClickListener(this);
        btSelect.setOnClickListener(this);
        ibtPrevious.setOnClickListener(this);
        ibtNext.setOnClickListener(this);
    }


}