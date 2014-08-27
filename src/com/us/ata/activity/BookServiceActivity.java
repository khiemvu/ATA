package com.us.ata.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.us.ata.R;

/**
 * Created by Jodie Pham on 8/24/14.
 */
public class BookServiceActivity extends Activity implements View.OnClickListener
{
    private static final int ADD_TIME_REMINDER = 1;
    private static final int ADD_OTHER_WORK = 2;
    private Button btback;
    private Button btSend;
    private Button btRightSend;
    private EditText edtDistance;
    private EditText edtBookDate;
    private EditText edtUnKnow;
    private EditText edtRego;
    private EditText edtMake;
    private EditText edtModel;
    private EditText edtPhone;
    private EditText edtAddress;
    private EditText edtRegoDate;
    private EditText edtOtherWork;
    private Button btRightRegoDate;
    private Button btRightOtherWork;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_service);
        initViewAndAction();

    }

    private void initViewAndAction()
    {
        btback = (Button) findViewById(R.id.book_service_btBack);
        btSend = (Button) findViewById(R.id.book_service_btSend);
        btRightSend = (Button) findViewById(R.id.book_service_btRightSend);
        btRightRegoDate = (Button) findViewById(R.id.book_service_btRegoDateRight);
        btRightOtherWork = (Button) findViewById(R.id.book_service_btOtherWork);

        edtDistance = (EditText) findViewById(R.id.book_service_edtKilometer);
        edtBookDate = (EditText) findViewById(R.id.book_service_edtBookDate);
        edtUnKnow = (EditText) findViewById(R.id.book_service_edtUnKnow);
        edtRego = (EditText) findViewById(R.id.book_service_edtRego);
        edtMake = (EditText) findViewById(R.id.book_service_edtMake);
        edtModel = (EditText) findViewById(R.id.book_service_edtModel);
        edtPhone = (EditText) findViewById(R.id.book_service_edtYourPhone);
        edtAddress = (EditText) findViewById(R.id.book_service_edtYourAddress);
        edtRegoDate = (EditText) findViewById(R.id.book_service_edtRegoDate);
        edtOtherWork = (EditText) findViewById(R.id.book_service_edtOtherWork);

        btback.setOnClickListener(this);
        btSend.setOnClickListener(this);
        btRightSend.setOnClickListener(this);
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
//                callAppForSentEmail();
                break;
            case R.id.book_service_btRightSend:
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
                edtRegoDate.setText(data.getStringExtra("timeReminderResult"));
            }
            else if (requestCode == ADD_OTHER_WORK)
            {
                edtOtherWork.setText(data.getStringExtra("otherWorkResult"));
            }
        }
    }

    public void callAppForSentEmail()
    {
        String subject = "ATT App: Servicing Booking";
        String to = "atamechanical@bigpond.com";
        String html = "<html xmlns=\"http://www.w3.org/1999/xhtml\">" +
                "<br><head>" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />" +
                "<table align=\"center\" border=\"2px;\" width=\"300px\">" +
                "<tr><td>Service Type:</td><td>%@</td></tr>" +
                "<tr><td>Other Work:</td><td>%@</td></tr>" +
                "<tr><td>Booking Date:</td><td>%@</td></tr>" +
                "<tr><td>Name:</td><td>%@</td></tr>" +
                "<tr><td>Rego:</td><td>%@</td></tr></tr>" +
                "<tr><td>Make:</td><td>%@</td></tr>" +
                "<tr><td>Model:</td><td>%@</td></tr>" +
                "<tr><td>Phone:</td><td>%@</td></tr></tr>" +
                "<tr><td>Address:</td><td>%@</td></tr>" +
                "<tr><td>Rego Date:</td><td>%@</td></tr>" +
                "</table>" +
                "</body>" +
                "</html>";

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        emailIntent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(html));
        emailIntent.setType("text/html");
        startActivity(Intent.createChooser(emailIntent, "email"));
//        emailIntent.setType("message/rfc822");
//        startActivity(Intent.createChooser(emailIntent, "Complete action using: "));
    }

}