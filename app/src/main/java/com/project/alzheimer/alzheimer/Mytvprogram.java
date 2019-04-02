package com.project.alzheimer.alzheimer;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class Mytvprogram extends AppCompatActivity {
ListView lsttv;
    mydatabase _mydatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mytvprogram);
        lsttv=(ListView)findViewById(R.id.lsttv);
        _mydatabase=new mydatabase(this);


        Cursor cursor=_mydatabase.getalltvprohdata();

        int count=(int)_mydatabase.gettvprogcount();


        tvprogpropclass tv_data[]=new tvprogpropclass[count];
        if(cursor.moveToFirst()){
            for (int x=0;x<count;x++)
            {
                tv_data[x]=new tvprogpropclass(cursor.getString(cursor.getColumnIndex("tvprogname")),cursor.getString(cursor.getColumnIndex("tvprogtime")));
                cursor.moveToNext();
            }
        }



        tvprogramadaptor adapter=new tvprogramadaptor(this,R.layout.tvprogramrow,tv_data);
        lsttv.setAdapter(adapter);



    }
}
