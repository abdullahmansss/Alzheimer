package com.project.alzheimer.alzheimer;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Mymedicine extends AppCompatActivity {
ListView lstmedic;

mydatabase _mydatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mymedicine);
        lstmedic=(ListView)findViewById(R.id.lstmedic);
        _mydatabase=new mydatabase(this);

        Cursor cursor=_mydatabase.getalldata();

        int count=(int)_mydatabase.getmdiccount();
       //Toast.makeText(this,String.valueOf(count) ,Toast.LENGTH_LONG).show();

        medicianpropclass mdic_data[]=new medicianpropclass[count];
        if(cursor.moveToFirst()){
            for (int x=0;x<count;x++)
            {
                mdic_data[x]=new medicianpropclass(cursor.getString(cursor.getColumnIndex("medic_name")),
                        cursor.getString(cursor.getColumnIndex("medic_time")));
                cursor.moveToNext();
            }
        }



medicianadapter adapter=new medicianadapter(this,R.layout.mdcrow,mdic_data);
        lstmedic.setAdapter(adapter);

    }
}
