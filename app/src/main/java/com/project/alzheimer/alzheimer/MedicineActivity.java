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

import com.project.alzheimer.alzheimer.BroadcastReceivers.MedicineReceiver;

import java.util.Calendar;

public class MedicineActivity extends AppCompatActivity
{
    mydatabase _mydatabase;
    EditText txt_medicine_name;
    TimePicker mdctimepicker;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);

        _mydatabase = new mydatabase(this);
        txt_medicine_name = findViewById(R.id.txt_medicine_name);
        mdctimepicker = findViewById(R.id.mdctimepicker);
    }

    public void insertmdic(View view)
    {
        String medic_name = txt_medicine_name.getText().toString();
        String medic_time = mdctimepicker.getCurrentHour() + ":" + mdctimepicker.getCurrentMinute();

        if (!medic_name.trim().isEmpty() && !medic_time.trim().isEmpty())
        {
            _mydatabase.insertmedic(medic_name, medic_time);
            Toast t = Toast.makeText(this, "Data Saved \nNotification Set", Toast.LENGTH_LONG);
            t.setGravity(Gravity.CENTER, 0, 0);
            t.show();
            txt_medicine_name.getText().clear();

            registerMedicine(mdctimepicker.getCurrentHour() , mdctimepicker.getCurrentMinute());
        } else {
            Toast t = Toast.makeText(this, "please fill your data", Toast.LENGTH_LONG);
            t.setGravity(Gravity.CENTER, 0, 0);
            t.show();
        }
    }

    private void registerMedicine(Integer currentHour, Integer currentMinute)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,currentHour);
        calendar.set(Calendar.MINUTE,currentMinute);
        calendar.set(Calendar.SECOND,0);

        Intent intent = new Intent(getApplicationContext(), MedicineReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }
}
