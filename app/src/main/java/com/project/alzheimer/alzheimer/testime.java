package com.project.alzheimer.alzheimer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class testime extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testime);
    }

    public void tstime(View view) {

/*
        long mills = date1.getTime() - date2.getTime();
        int hours = millis/(1000 * 60 * 60);
        int mins = (mills/(1000*60)) % 60;

        String diff = hours + ":" + mins;
        */

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

            Date EndTime = dateFormat.parse("11:05");

            Date CurrentTime = dateFormat.parse(dateFormat.format(new Date()));
            long mills =  CurrentTime.getTime()-EndTime.getTime() ;
            long diffMinutes = mills / (60 * 1000);
            if (CurrentTime.after(EndTime)&&diffMinutes<5&&mills>=1&&diffMinutes>=0)
            {
                // System.out.println("timeeee end ");
                Toast.makeText(this,"timeeee end ",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(this,"not yeat ",Toast.LENGTH_LONG).show();
            }
            Toast.makeText(this,String.valueOf(diffMinutes)+"-"+String.valueOf(mills),Toast.LENGTH_LONG).show();

        }
        catch (Exception ex)
        {
            Toast.makeText(this,"eror",Toast.LENGTH_LONG).show();
        }

    }
}
