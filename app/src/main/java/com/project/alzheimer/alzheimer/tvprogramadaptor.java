package com.project.alzheimer.alzheimer;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Ahmed Elarabi on 3/7/2018.
 */

public class tvprogramadaptor extends ArrayAdapter<tvprogpropclass> {
    Context context;
    int rowtvproglayoutid;
    tvprogpropclass data[]=null;

    public tvprogramadaptor(Context context, int rowtvproglayoutid,  tvprogpropclass[] data) {
        super(context, rowtvproglayoutid, data);
        this.context=context;
        this.rowtvproglayoutid=rowtvproglayoutid;
        this.data=data;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        tvprogramadaptor.tvprogramholder holder=null;
        if(row==null)
        {
            LayoutInflater inflater=((Activity)context).getLayoutInflater();
            row=inflater.inflate(rowtvproglayoutid,parent,false);
            holder=new tvprogramadaptor.tvprogramholder();
            holder.txtprogname=(TextView)row.findViewById(R.id.txtprogname);
            holder.txtprogtime=(TextView)row.findViewById(R.id.txtprogtime);
            row.setTag(holder);
        }
        else
        {
            holder=(tvprogramadaptor.tvprogramholder)row.getTag();
        }
        tvprogpropclass tvprogpropclas=data[position];
        holder.txtprogname.setText(tvprogpropclas.tvprogname);
        holder.txtprogtime.setText(tvprogpropclas.tvprogtime);
        return row;
    }



    static class tvprogramholder
    {
        TextView txtprogname;
        TextView txtprogtime;
    }
}
