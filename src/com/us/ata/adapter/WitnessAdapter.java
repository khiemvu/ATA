package com.us.ata.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.us.ata.R;
import com.us.ata.model.Witness;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jodie Pham on 8/24/14.
 */
public class WitnessAdapter extends BaseAdapter
{
    private Context context;
    private List<Witness> witnessList = new ArrayList<Witness>();

    public WitnessAdapter(Context context, List<Witness> witnessList)
    {
        this.context = context;
        this.witnessList = witnessList;
    }


    @Override
    public int getCount()
    {
        return witnessList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return witnessList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder viewHolder = null;
        if (convertView == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater li = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.witness_item, null);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.witness_item_tvName);
            convertView.setTag(viewHolder);
        }
        else

        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvName.setText(witnessList.get(position).getName());
        return convertView;
    }

    class ViewHolder
    {
        public TextView tvName;

    }
}
