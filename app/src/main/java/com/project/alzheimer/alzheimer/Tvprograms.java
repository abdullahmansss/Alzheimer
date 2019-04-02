package com.project.alzheimer.alzheimer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class Tvprograms extends AppCompatActivity {
    mydatabase _mydatabase;
    EditText txt_prog_name,txt_prog_time;
    TimePicker tvtimepicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvprograms);
        _mydatabase=new mydatabase(this);
        txt_prog_name=(EditText) findViewById(R.id.txt_prog_name);
        tvtimepicker=(TimePicker) findViewById(R.id.tvtimepicker);

    }

    public void insertdata(View view) {

        String tvprogname=txt_prog_name.getText().toString();

        String tvprogtime=tvtimepicker.getCurrentHour()+":"+tvtimepicker.getCurrentMinute();
        if(!tvprogname.trim().isEmpty()&&!tvprogtime.trim().isEmpty())
        {
            _mydatabase.inserttvprog(tvprogname,tvprogtime);
            Toast t= Toast.makeText(this,"Data Saved",Toast.LENGTH_LONG);
            t.setGravity(Gravity.CENTER,0,0);
            t.show();
            txt_prog_name.getText().clear();


        }
        else
        {
            Toast t=  Toast.makeText(this,"please fill your data",Toast.LENGTH_LONG);
            t.setGravity(Gravity.CENTER,0,0);
            t.show();
        }
    }
}
