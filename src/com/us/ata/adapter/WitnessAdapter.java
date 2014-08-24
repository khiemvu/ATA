package com.us.ata.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
        return 0;
    }

    @Override
    public Object getItem(int position)
    {
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        return null;
    }
}
