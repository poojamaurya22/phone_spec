package com.mobilinker.sprintoid;

/**
 * Created by Pooja on 02-06-2017.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter{
    String [] item;
    Context context;
    String [] item_detail;

    private static LayoutInflater inflater=null;

    public CustomAdapter(Context context, String[] item, String[] item_detail) {
        // TODO Auto-generated constructor stub
        this.item=item;
        this.context=context;
        this.item_detail=item_detail;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return item.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        View rowView;
        rowView = inflater.inflate(R.layout.list_detail, null);
        TextView tv1=(TextView) rowView.findViewById(R.id.textView1);
        TextView tv2=(TextView) rowView.findViewById(R.id.textView2);
        tv1.setText(item[position]);
        tv2.setText(item_detail[position]);

        return rowView;
    }

}