package com.us.ata.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.us.ata.R;

/**
 * Created by Jodie Pham on 8/24/14.
 */
public class ViewPhotoDetailsActivity extends Activity implements View.OnClickListener
{
    private ImageView ivView;
    private Button btBack;
    public ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photos);
        String url = getIntent().getStringExtra("url");
        ivView = (ImageView)findViewById(R.id.photos_ivView);
        btBack = (Button) findViewById(R.id.photo_btBack);
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(0)
                .showImageForEmptyUri(0)
                .showImageOnFail(0)
                .delayBeforeLoading(100)
                .cacheOnDisc(false)
                .cacheInMemory(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .displayer(new FadeInBitmapDisplayer(1000))
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .build();
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        imageLoader.displayImage(url, ivView, options);
        btBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.photo_btBack:
                finish();
                break;
        }
    }
}