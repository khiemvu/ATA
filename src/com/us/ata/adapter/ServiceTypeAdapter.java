package com.us.ata.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.us.ata.R;
import com.us.ata.model.ServiceType;

import java.util.List;

/**
 * User: khiemvx
 * Date: 7/3/14
 */
public class ServiceTypeAdapter extends BaseAdapter
{
    private final Activity context;
    private final List<ServiceType> arrayList;

    public ServiceTypeAdapter(Activity context, List<ServiceType> arrayList)
    {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount()
    {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        LayoutInflater inflater = context.getLayoutInflater();
        final ViewHolder holder;
        if (view == null)
        {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.service_type_dialog_item, null);
            holder.imageView = (ImageView) view.findViewById(R.id.service_type_imageView);
            holder.textView = (TextView) view
                    .findViewById(R.id.service_type_tvName);
            view.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }
        holder.textView.setText(arrayList.get(position).getName());
        ServiceType serviceType = arrayList.get(position);
        if (serviceType != null)
        {
            if (!serviceType.isSelected())
            {
                holder.imageView.setVisibility(View.GONE);
            }
            else
            {
                holder.imageView.setVisibility(View.VISIBLE);
            }
        }

        return view;
    }

    static class ViewHolder
    {
        ImageView imageView;
        TextView textView;
    }
}
