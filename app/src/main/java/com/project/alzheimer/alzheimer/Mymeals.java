package com.project.alzheimer.alzheimer;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class Mymeals extends AppCompatActivity {

    ListView lstmeals;

    mydatabase _mydatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mymeals);

        lstmeals=(ListView)findViewById(R.id.lstmeals);
        _mydatabase=new mydatabase(this);



        Cursor cursor=_mydatabase.getallmealdata();

        int count=(int)_mydatabase.getmealccount();


        mealpropclass meal_data[]=new mealpropclass[count];
        if(cursor.moveToFirst()){
            for (int x=0;x<count;x++)
            {
                meal_data[x]=new mealpropclass(cursor.getString(cursor.getColumnIndex("meal_name")),cursor.getString(cursor.getColumnIndex("meal_time")));
                cursor.moveToNext();
            }
        }



        mealsadapter adapter=new mealsadapter(this,R.layout.mealsrow,meal_data);
        lstmeals.setAdapter(adapter);



    }
}
