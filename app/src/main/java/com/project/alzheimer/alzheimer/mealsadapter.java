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
 * Created by Ahmed Elarabi on 3/8/2018.
 */

public class mealsadapter  extends ArrayAdapter<mealpropclass> {


    Context context;
    int rowmealclayoutid;
    mealpropclass data[]=null;


    public mealsadapter(Context context, int rowmealclayoutid,  mealpropclass[] data) {
        super(context, rowmealclayoutid, data);
        this.context=context;
        this.rowmealclayoutid=rowmealclayoutid;
        this.data=data;
    }



    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        mealsadapter.mealsholder holder=null;
        if(row==null)
        {
            LayoutInflater inflater=((Activity)context).getLayoutInflater();
            row=inflater.inflate(rowmealclayoutid,parent,false);
            holder=new mealsadapter.mealsholder();
            holder.txtmealname=(TextView)row.findViewById(R.id.txtmealname);
            holder.txtmealcontent=(TextView)row.findViewById(R.id.txtmealcontent);
            row.setTag(holder);
        }
        else
        {
            holder=(mealsadapter.mealsholder)row.getTag();
        }
        mealpropclass mealprpclass=data[position];
        holder.txtmealname.setText(mealprpclass.mealsname);
        holder.txtmealcontent.setText(mealprpclass.mealscontent);
        return row;
    }





    static class mealsholder
    {
        TextView txtmealname;
        TextView txtmealcontent;
    }




}
