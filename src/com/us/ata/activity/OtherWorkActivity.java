package com.us.ata.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.us.ata.R;

/**
 * User: Khiemvx
 * Date: 8/27/2014
 */
public class OtherWorkActivity extends Activity implements View.OnClickListener
{
    private ImageView ivBack;
    private Button btSave;
    private EditText etNote;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_work);
        initViewAndAction();
    }

    private void initViewAndAction()
    {
        ivBack = (ImageView) findViewById(R.id.other_work_btBack);
        btSave = (Button) findViewById(R.id.other_work_btSave);
        etNote = (EditText) findViewById(R.id.other_work_etNote);

        ivBack.setOnClickListener(this);
        btSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.other_work_btBack:
                finish();
                break;
            case R.id.other_work_btSave:
                callBack();
                break;
        }
    }

    private void callBack()
    {
        String otherWorkResult = etNote.getText().toString();
        Intent intent = new Intent(this, BookServiceActivity.class);
        intent.putExtra("otherWorkResult", otherWorkResult);
        setResult(RESULT_OK, intent);
        finish();
    }
}
