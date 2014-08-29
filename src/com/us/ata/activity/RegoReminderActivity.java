package com.us.ata.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.us.ata.R;
import com.us.ata.utils.Constant;
import com.us.ata.utils.DateTimePickerDialog;
import com.us.ata.utils.DateTimePickerHelper;

import java.util.Calendar;
import java.util.Date;

/**
 * User: Khiemvx
 * Date: 8/24/2014
 */
public class RegoReminderActivity extends Activity implements View.OnClickListener
{
    private Button btSave, btReminder;
    private ImageView ibtBack;
    String addTimeReminder;

    Calendar calendar;
    String timeReminderResult;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rego_remind);
        initViewAndAction();

        Bundle extras = getIntent().getExtras();
        addTimeReminder = getIntent().getStringExtra("addReminder");
    }

    private void initViewAndAction()
    {
        btReminder = (Button) findViewById(R.id.rego_remind_btSelectDate);
        btSave = (Button) findViewById(R.id.rego_remind_btSave);
        ibtBack = (ImageView) findViewById(R.id.rego_remind_btBack);

        btReminder.setOnClickListener(this);
        btSave.setOnClickListener(this);
        ibtBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.rego_remind_btBack:
                finish();
                break;
            case R.id.rego_remind_btSave:
                callBack();
                break;
            case R.id.rego_remind_btSelectDate:
                calendar = Calendar.getInstance();
                showReminderPicker();
                break;
        }
    }

    private void showReminderPicker()
    {
        DateTimePickerDialog dtpDialog = new DateTimePickerDialog(
                RegoReminderActivity.this);
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
                            timeReminderResult = (DateTimePickerHelper.dateToString(date,
                                    DateTimePickerHelper.NORMAL_FORMAT));
                        }

                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        timeReminderResult = Constant.BLANK;
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

    private void callBack()
    {
        Intent intent = new Intent(this, AddVehicleActivity.class);
        intent.putExtra("timeReminderResult", timeReminderResult);
        if (!addTimeReminder.equals("RegoReminderActivity"))
        {
            intent.putExtra("reminderResult", "bookDateTime");

        }
        intent.putExtra("timeReminderResult", timeReminderResult);
        setResult(RESULT_OK, intent);
        finish();
    }


}
