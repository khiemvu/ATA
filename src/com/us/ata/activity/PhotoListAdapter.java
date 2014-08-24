package com.us.ata.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.us.ata.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jodie Pham on 8/24/14.
 */
public class PhotoListAdapter extends BaseAdapter
{
    private Context context;
    private List<String> imageUrl = new ArrayList<String>();
    private LayoutInflater mInflater;
    private LinearLayout.LayoutParams mImageViewLayoutParams;

    public PhotoListAdapter(Context context, List<String> imageUrl)
    {
        this.context = context;
        this.imageUrl = imageUrl;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mImageViewLayoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

    }

    @Override
    public int getCount()
    {
        return imageUrl.size();
    }

    @Override
    public Object getItem(int position)
    {
        return imageUrl.get(position);
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

        return convertView;
    }

    class ViewHolder
    {
        ImageView imageview;
        RelativeLayout relativeLayout;
    }
}
