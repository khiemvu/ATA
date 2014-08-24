package com.us.ata.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.us.ata.R;
import com.us.ata.model.Vehicle;
import com.us.ata.utils.DateTimePickerDialog;
import com.us.ata.utils.DateTimePickerHelper;
import com.us.ata.utils.Utils;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * User: Khiemvx
 * Date: 8/22/14
 */
public class AddVehicleActivity extends Activity implements View.OnClickListener
{
    protected Button btSave;
    protected ImageView btBack;
    private EditText etName, etRego, etMake, etModel, etPhone, etAddress, etRegoDate,
            etInsuranceComany, etInsurancePhone, etPolicy, etBroker;

    Calendar calendar;

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
        etRegoDate = (EditText) findViewById(R.id.add_vehicle_etRegoDate);
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
                calendar = Calendar.getInstance();
                showReminderPicker();
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

    private void showReminderPicker()
    {
        DateTimePickerDialog dtpDialog = new DateTimePickerDialog(
                AddVehicleActivity.this);
        dtpDialog.setDateTime(calendar);
        dtpDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Save", dialog_onclick);
        dtpDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", dialog_onclick);
        dtpDialog.show();
    }

    DialogInterface.OnClickListener dialog_onclick = new DialogInterface.OnClickListener()
    {

        @Override
        public void onClick(DialogInterface dialog, int which)
        {
            try
            {
                if (dialog.getClass() != DateTimePickerDialog.class)
                {
                    return;
                }
                switch (which)
                {
                    case DialogInterface.BUTTON_POSITIVE:
                        DateTimePickerDialog reminderDl = (DateTimePickerDialog) dialog;

                        Date date = reminderDl.getDate();
                        if (date.compareTo(calendar.getTime()) > 0)
                        {
                            etRegoDate.setText(DateTimePickerHelper.dateToString(date,
                                    DateTimePickerHelper.NORMAL_FORMAT));
                            etRegoDate.setTag(date.getTime());
                        }
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        dialog.dismiss();
                        break;
                    default:
                        break;
                }
            }
            catch (Exception ex)
            {
                Log.e("all_in_one", ex.getMessage());
            }
        }
    };
}
