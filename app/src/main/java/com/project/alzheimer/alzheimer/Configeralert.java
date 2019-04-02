package com.project.alzheimer.alzheimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Configeralert extends AppCompatActivity {
    mydatabase _mydatabase;
    EditText txt_delaytime;
    int value=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configeralert);
        txt_delaytime=(EditText) findViewById(R.id.txt_delaytime);
        _mydatabase=new mydatabase(this);
    }

    public void startmainnotify(View view) {
        String myvalue=txt_delaytime.getText().toString();
       if(!myvalue.trim().isEmpty())
       {
           value=Integer.parseInt(myvalue.toString());
           _mydatabase.updatedatamainnotifytime(value);
       }

        startService(new Intent(this,notfyservice.class));
        Toast t= Toast.makeText(this,"Service Start",Toast.LENGTH_LONG);
        t.setGravity(Gravity.CENTER,0,0);
        t.show();
    }
    public void stopmainnotify(View view) {

        stopService(new Intent(this,notfyservice.class));
        Toast t= Toast.makeText(this,"Service Stopped",Toast.LENGTH_LONG);
        t.setGravity(Gravity.CENTER,0,0);
        t.show();
    }

    public void startmdcnotify(View view) {
        startService(new Intent(this,mdicnotfyservice.class));
        Toast t= Toast.makeText(this,"Service Start",Toast.LENGTH_LONG);
        t.setGravity(Gravity.CENTER,0,0);
        t.show();
    }

    public void stopmdcnotify(View view) {
        stopService(new Intent(this,mdicnotfyservice.class));
        Toast t= Toast.makeText(this,"Service Stopped",Toast.LENGTH_LONG);
        t.setGravity(Gravity.CENTER,0,0);
        t.show();
    }

    public void starttvnotify(View view) {

        startService(new Intent(this,tvnotfyservice.class));
        Toast t= Toast.makeText(this,"Service Start",Toast.LENGTH_LONG);
        t.setGravity(Gravity.CENTER,0,0);
        t.show();
    }
    public void stoptvnotify(View view) {
        stopService(new Intent(this,tvnotfyservice.class));
        Toast t= Toast.makeText(this,"Service Stopped",Toast.LENGTH_LONG);
        t.setGravity(Gravity.CENTER,0,0);
        t.show();
    }

    public void startmealnotify(View view) {
        startService(new Intent(this,mealnotfyservice.class));
        Toast t= Toast.makeText(this,"Service Start",Toast.LENGTH_LONG);
        t.setGravity(Gravity.CENTER,0,0);
        t.show();
    }
    public void stopmealnotify(View view) {
        stopService(new Intent(this,mealnotfyservice.class));
        Toast t= Toast.makeText(this,"Service Stopped",Toast.LENGTH_LONG);
        t.setGravity(Gravity.CENTER,0,0);
        t.show();
    }
}
