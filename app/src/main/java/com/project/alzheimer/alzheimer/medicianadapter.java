package com.project.alzheimer.alzheimer;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Ahmed Elarabi on 3/6/2018.
 */

public class medicianadapter extends ArrayAdapter<medicianpropclass> {
    Context context;
    int rowmdclayoutid;
    medicianpropclass data[]=null;

    public medicianadapter(Context context, int rowmdclayoutid,  medicianpropclass[] data) {
        super(context, rowmdclayoutid, data);
        this.context=context;
        this.rowmdclayoutid=rowmdclayoutid;
        this.data=data;
    }


    @Override
    public View getView(int position,  View convertView,ViewGroup parent) {
        View row=convertView;
        medicianholder holder=null;
        if(row==null)
        {
            LayoutInflater inflater=((Activity)context).getLayoutInflater();
            row=inflater.inflate(rowmdclayoutid,parent,false);
            holder=new medicianholder();
            holder.txtmname=(TextView)row.findViewById(R.id.txtmname);
            holder.txtmtime=(TextView)row.findViewById(R.id.txtmtime);
            row.setTag(holder);
        }
        else
        {
            holder=(medicianholder)row.getTag();
        }
        medicianpropclass medcianpropclass=data[position];
        holder.txtmtime.setText(medcianpropclass.medic_time);
        holder.txtmname.setText(medcianpropclass.medic_name);
        return row;
    }



    static class medicianholder
    {
        TextView txtmname;
        TextView txtmtime;
    }
}
