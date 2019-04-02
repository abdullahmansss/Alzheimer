package com.project.alzheimer.alzheimer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class Meals extends AppCompatActivity {
    mydatabase _mydatabase;
    EditText txt_meal_name,txt_meal_time;
    TimePicker mealtimepicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meals);

        _mydatabase=new mydatabase(this);
        txt_meal_name=(EditText) findViewById(R.id.txt_meal_name);

        mealtimepicker=(TimePicker) findViewById(R.id.mealtimepicker);


    }

    public void insertmeal(View view) {
        String meal_name=txt_meal_name.getText().toString();
        String meal_time=mealtimepicker.getCurrentHour()+":"+mealtimepicker.getCurrentMinute();
        if(!meal_name.trim().isEmpty()&&!meal_time.trim().isEmpty())
        {
            _mydatabase.insertmealsdata(meal_name,meal_time);
            Toast t= Toast.makeText(this,"Data Saved",Toast.LENGTH_LONG);
            t.setGravity(Gravity.CENTER,0,0);
            t.show();

            txt_meal_name.getText().clear();


        }
        else
        {
            Toast t=  Toast.makeText(this,"please fill your data",Toast.LENGTH_LONG);
            t.setGravity(Gravity.CENTER,0,0);
            t.show();
        }

    }
}
