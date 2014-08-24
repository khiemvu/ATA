package com.us.ata.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.*;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.us.ata.R;
import com.us.ata.model.Image;
import com.us.ata.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jodie Pham on 8/24/14.
 */
public class PhotoListAdapter extends BaseAdapter
{
    private Context context;
    private List<Image> images = new ArrayList<Image>();
    private LinearLayout.LayoutParams mImageViewLayoutParams;
    private LayoutInflater mInflater;
    public ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options;

    public PhotoListAdapter(Context context, List<Image> images)
    {
        this.context = context;
        this.images = images;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mImageViewLayoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
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
    }

    @Override
    public int getCount()
    {
        return images.size();
    }

    @Override
    public Object getItem(int position)
    {
        return images.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final ViewHolder holder;
        if (convertView == null)
        {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.photo_list_item, parent, false);
            holder.imageview = (ImageView) convertView
                    .findViewById(R.id.photo_list_item_ivImage);
            holder.relativeLayout = (RelativeLayout) convertView
                    .findViewById(R.id.photo_list_item_rlContainer);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        imageLoader.displayImage(images.get(position).getUrl(), holder.imageview, options);
        imageLoader.displayImage(images.get(position).getUrl(), holder.imageview, options, new ImageLoadingListener()
                {
                    @Override
                    public void onLoadingStarted(String imageUri, View view)
                    {
                        holder.imageview.setImageResource(0);
                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason)
                    {
                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage)
                    {
                        Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
                        int width = display.getWidth();
                        int height = display.getHeight();
                        if (null != loadedImage)
                        {
                            if (null != Utils.scaleCenterCrop(loadedImage, width / 3, width / 3))
                            {
                                Bitmap bm = Utils.scaleCenterCrop(loadedImage,width/3,width/3);
                                holder.imageview.setImageBitmap(Utils.getRoundedCornerBitmap(bm,25));
                                holder.imageview.setScaleType(ImageView.ScaleType.FIT_XY);
                            }

                        }
                    }

                    @Override
                    public void onLoadingCancelled(String imageUri, View view)
                    {
                    }
                }, new ImageLoadingProgressListener()
                {
                    @Override
                    public void onProgressUpdate(String imageUri, View view, int current, int total)
                    {
                    }
                }
        );
        holder.relativeLayout.setLayoutParams(mImageViewLayoutParams);
        return convertView;
    }

    class ViewHolder
    {
        ImageView imageview;
        RelativeLayout relativeLayout;
    }
}
