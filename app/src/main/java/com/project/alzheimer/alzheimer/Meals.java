package com.project.alzheimer.alzheimer;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.project.alzheimer.alzheimer.BroadcastReceivers.MealReceiver;
import com.project.alzheimer.alzheimer.BroadcastReceivers.TvReceiver;

import java.util.Calendar;

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

    public void insertmeal(View view)
    {
        String medic_name = txt_meal_name.getText().toString();
        String medic_time = mealtimepicker.getCurrentHour() + ":" + mealtimepicker.getCurrentMinute();

        if (!medic_name.trim().isEmpty() && !medic_time.trim().isEmpty())
        {
            _mydatabase.insertmealsdata(medic_name, medic_time);
            Toast t = Toast.makeText(this, "Data Saved \nNotification Set", Toast.LENGTH_LONG);
            t.setGravity(Gravity.CENTER, 0, 0);
            t.show();
            txt_meal_name.getText().clear();

            registerMeals(mealtimepicker.getCurrentHour() , mealtimepicker.getCurrentMinute());
        } else {
            Toast t = Toast.makeText(this, "please fill your data", Toast.LENGTH_LONG);
            t.setGravity(Gravity.CENTER, 0, 0);
            t.show();
        }
    }

    private void registerMeals(Integer currentHour, Integer currentMinute)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,currentHour);
        calendar.set(Calendar.MINUTE,currentMinute);
        calendar.set(Calendar.SECOND,0);

        Intent intent = new Intent(getApplicationContext(), MealReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }
}
