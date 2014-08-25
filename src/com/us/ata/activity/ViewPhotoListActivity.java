package com.us.ata.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import com.us.ata.R;
import com.us.ata.adapter.PhotoListAdapter;
import com.us.ata.model.Image;
import com.us.ata.ormlite.DatabaseHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jodie Pham on 8/24/14.
 */
public class ViewPhotoListActivity extends Activity implements View.OnClickListener ,AdapterView.OnItemClickListener
{
    private Button btBack;
    private DatabaseHelper databaseHelper;
    private PhotoListAdapter adapter;
    private GridView gridView;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_list);

        databaseHelper = new DatabaseHelper(this);
        List<Image> images = new ArrayList<Image>();
        try
        {
            images = databaseHelper.getImageDAO().queryForAll();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        adapter = new PhotoListAdapter(this,images);
        btBack = (Button) findViewById(R.id.photo_list_btBack);
        gridView = (GridView)findViewById(R.id.photo_list_grvImage);
        gridView.setAdapter(adapter);
        btBack.setOnClickListener(this);
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.photo_list_btBack:
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Intent intent = new Intent(this,ViewPhotoDetailsActivity.class);
        intent.putExtra("url",((Image)adapter.getItem(position)).getUrl());
        startActivity(intent);
    }
}