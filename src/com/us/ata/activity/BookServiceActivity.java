package com.us.ata.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.us.ata.R;

/**
 * Created by Jodie Pham on 8/24/14.
 */
public class BookServiceActivity extends Activity  implements View.OnClickListener
{
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
        btback = (Button)findViewById(R.id.book_service_btBack);
        btSend = (Button)findViewById(R.id.book_service_btSend);
        btRightSend = (Button)findViewById(R.id.book_service_btRightSend);
        btRightRegoDate = (Button)findViewById(R.id.book_service_btRegoDateRight);
        btRightOtherWork = (Button)findViewById(R.id.book_service_btOtherWork);

        edtDistance = (EditText)findViewById(R.id.book_service_edtKilometer);
        edtBookDate = (EditText)findViewById(R.id.book_service_edtBookDate);
        edtUnKnow = (EditText)findViewById(R.id.book_service_edtUnKnow);
        edtRego = (EditText)findViewById(R.id.book_service_edtRego);
        edtMake = (EditText)findViewById(R.id.book_service_edtMake);
        edtModel = (EditText)findViewById(R.id.book_service_edtModel);
        edtPhone = (EditText)findViewById(R.id.book_service_edtYourPhone);
        edtAddress = (EditText)findViewById(R.id.book_service_edtYourAddress);
        edtRegoDate = (EditText)findViewById(R.id.book_service_edtRegoDate);
        edtOtherWork = (EditText)findViewById(R.id.book_service_edtOtherWork);

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
              break;
          case R.id.book_service_btRightSend:
              break;
          case R.id.book_service_btRegoDateRight:
              break;
          case R.id.book_service_btOtherWork:
              break;

      }
    }
}