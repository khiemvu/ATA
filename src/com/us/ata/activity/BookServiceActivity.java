package com.us.ata.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.*;
import com.us.ata.R;
import com.us.ata.adapter.ServiceTypeAdapter;
import com.us.ata.model.ServiceType;
import com.us.ata.model.Vehicle;
import com.us.ata.utils.Constant;
import com.us.ata.utils.SharedPreferencesManager;
import com.us.ata.utils.Utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jodie Pham on 8/24/14.
 */
public class BookServiceActivity extends Activity implements View.OnClickListener
{
    private static final int ADD_TIME_REMINDER = 1;
    private static final int ADD_OTHER_WORK = 2;
    private Button btback, btPrevious, btNext;
    private Button btSend;
    private Button btRightSend;
    private TextView tvDistance, tvBookDate;
    private EditText etName;
    private EditText edtRego;
    private EditText edtMake;
    private EditText edtModel;
    private EditText edtPhone;
    private EditText edtAddress;
    private EditText edtRegoDate;
    private EditText edtOtherWork;
    private Button btRightRegoDate;
    private Button btRightOtherWork;
    ArrayList<ServiceType> serviceTypeArrayList;
    Dialog dialog;
    List<Vehicle> vehicleList;
    int sizeOfList;
    int count = 0;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_service);
        getAllDataFromDB();
        initViewAndAction();
        sizeOfList = vehicleList.size();
        if (sizeOfList > 0)
        {
            if (sizeOfList > 1)
            {
                btNext.setVisibility(View.VISIBLE);
            }
            bindDataOnView(0);
        }

    }

    private void bindDataOnView(int position)
    {
        Vehicle temp = vehicleList.get(position);
        etName.setText(temp.getName());
        edtRego.setText(temp.getRego());
        edtMake.setText(temp.getMake());
        edtModel.setText(temp.getModel());
        edtPhone.setText(temp.getYourPhone());
        edtAddress.setText(temp.getYourAddress());
        edtRegoDate.setText(temp.getRegoReminder());
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


    private void initViewAndAction()
    {
        btNext = (Button) findViewById(R.id.book_service_btNext);
        btPrevious = (Button) findViewById(R.id.book_service_btPrevious);
        btback = (Button) findViewById(R.id.book_service_btBack);
        btSend = (Button) findViewById(R.id.book_service_btSend);
        btRightRegoDate = (Button) findViewById(R.id.book_service_btRegoDateRight);
        btRightOtherWork = (Button) findViewById(R.id.book_service_btOtherWork);

        tvDistance = (TextView) findViewById(R.id.book_service_tvKilometer);
        tvBookDate = (TextView) findViewById(R.id.book_service_tvBookDate);
        etName = (EditText) findViewById(R.id.book_service_edtName);
        edtRego = (EditText) findViewById(R.id.book_service_edtRego);
        edtMake = (EditText) findViewById(R.id.book_service_edtMake);
        edtModel = (EditText) findViewById(R.id.book_service_edtModel);
        edtPhone = (EditText) findViewById(R.id.book_service_edtYourPhone);
        edtAddress = (EditText) findViewById(R.id.book_service_edtYourAddress);
        edtRegoDate = (EditText) findViewById(R.id.book_service_edtRegoDate);
        edtOtherWork = (EditText) findViewById(R.id.book_service_edtOtherWork);

        tvDistance.setOnClickListener(this);
        tvBookDate.setOnClickListener(this);
        btback.setOnClickListener(this);
        btSend.setOnClickListener(this);
        btPrevious.setOnClickListener(this);
        btNext.setOnClickListener(this);
        btRightRegoDate.setOnClickListener(this);
        btRightOtherWork.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.book_service_btBack:
                finish();
                break;
            case R.id.book_service_btSend:
                callAppForSentEmail();
                break;

            case R.id.book_service_btRegoDateRight:
                Intent regoReminder = new Intent(this, RegoReminderActivity.class);
                regoReminder.putExtra("addReminder", "RegoReminderActivity");
                startActivityForResult(regoReminder, ADD_TIME_REMINDER);
                break;
            case R.id.book_service_btOtherWork:
                Intent otherWork = new Intent(this, OtherWorkActivity.class);
                otherWork.putExtra("otherWork", "OtherWorkActivity");
                startActivityForResult(otherWork, ADD_OTHER_WORK);
                break;
            case R.id.book_service_tvKilometer:
                showDialog(fakeServiceType(tvDistance));
                break;
            case R.id.book_service_tvBookDate:
                Intent reminder = new Intent(this, RegoReminderActivity.class);
                reminder.putExtra("addReminder", "bookDate");
                startActivityForResult(reminder, ADD_TIME_REMINDER);
                break;
            case R.id.book_service_btNext:
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
            case R.id.book_service_btPrevious:
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

        }
    }

    public void showDialog(ArrayList<ServiceType> arrayList)
    {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.service_type_dialog);
        dialog.setCanceledOnTouchOutside(false);
        ListView listView = (ListView) dialog.findViewById(R.id.ervice_type_lvContent);
        ServiceTypeAdapter adapter = new ServiceTypeAdapter(this, arrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(onItemClickListener);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.findViewById(R.id.service_type_btCancel).setOnClickListener(onclickListener);
        dialog.findViewById(R.id.service_type_btSave).setOnClickListener(onclickListener);
        dialog.findViewById(R.id.service_type_btBack).setOnClickListener(onclickListener);
        dialog.show();
    }

    private View.OnClickListener onclickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            switch (v.getId())
            {
                case R.id.service_type_btCancel:
                case R.id.service_type_btBack:
                    String value = new SharedPreferencesManager(BookServiceActivity.this)
                            .getString(Constant.VALUE_CURRENT_KEY);
                    tvDistance.setText(value);
                    dialog.dismiss();
                    break;
                case R.id.service_type_btSave:
                    tvDistance.setText(new SharedPreferencesManager(BookServiceActivity.this)
                            .getString(Constant.VALUE_CURRENT_KEY));
                    dialog.dismiss();
                    break;
            }
        }
    };

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
        {
            for (int i = 0; i < serviceTypeArrayList.size(); i++)
            {
                if (i != position)
                {
                    serviceTypeArrayList.get(i).setSelected(false);
                }
            }
            ImageView imgChecked;
            for (int i = 0; i < adapterView.getAdapter().getCount(); i++)
            {
                View v1 = adapterView.getChildAt(i);
                if (v1 != null)
                {
                    imgChecked = (ImageView) v1.findViewById(R.id.service_type_imageView);
                    imgChecked.setVisibility(View.GONE);
                }
            }
            imgChecked = (ImageView) view.findViewById(R.id.service_type_imageView);
            ServiceType serviceType = serviceTypeArrayList.get(position);

            if (!serviceType.isSelected())
            {
                imgChecked.setVisibility(View.VISIBLE);
                serviceType.setSelected(true);
                new SharedPreferencesManager(BookServiceActivity.this).setValue(Constant.VALUE_CURRENT_KEY, serviceType.getName());
            }
            else
            {
                imgChecked.setVisibility(View.VISIBLE);
            }
        }
    };

    public ArrayList<ServiceType> fakeServiceType(TextView tvServiceType)
    {
        serviceTypeArrayList = new ArrayList<ServiceType>();
        ServiceType serviceType;
        for (int i = 0; i <= 100; i++)
        {
            if ((Integer.toString(5 + 5 * i) + ",000 km").equals(tvServiceType.getText().toString()))
            {
                serviceType = new ServiceType((Integer.toString(5 + 5 * i) + ",000 km"), true);
            }
            else
            {
                serviceType = new ServiceType((Integer.toString(5 + 5 * i) + ",000 km"), false);
            }
            serviceTypeArrayList.add(serviceType);
        }
        return serviceTypeArrayList;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
        {
            String classify = data.getStringExtra("reminderResult");
            if (requestCode == ADD_TIME_REMINDER)
            {
                if (classify != null && classify.equals("bookDateTime"))
                {
                    tvBookDate.setText(data.getStringExtra("timeReminderResult"));
                }
                else
                {
                    edtRegoDate.setText(data.getStringExtra("timeReminderResult"));
                }
            }
            else if (requestCode == ADD_OTHER_WORK)
            {
                edtOtherWork.setText(data.getStringExtra("otherWorkResult"));
            }
        }
    }

    public void callAppForSentEmail()
    {
        String to[] = {"atamechanical@bigpond.com"};
        String subject = "ATA App: Servicing Booking";
        String message =
                "Service Type: %s\n"
                        + "Other Work: %s\n"
                        + "Booking Date: %s\n"
                        + "Name: %s\n"
                        + "Rego: %s\n"
                        + "Make: %s\n"
                        + "Model: %s\n"
                        + "Phone: %s\n"
                        + "Address: %s\n"
                        + "Rego Date: %s\n";

        message = String.format(message,
                tvDistance.getText().toString(),
                edtOtherWork.getText().toString(),
                tvBookDate.getText().toString(),
                etName.getText().toString(),
                edtRego.getText().toString(),
                edtMake.getText().toString(),
                edtModel.getText().toString(),
                edtPhone.getText().toString(),
                edtAddress.getText().toString(),
                edtRegoDate.getText().toString());
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);
        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent, "email"));
    }

}