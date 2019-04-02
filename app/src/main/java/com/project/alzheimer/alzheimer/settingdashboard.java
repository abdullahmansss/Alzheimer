package com.project.alzheimer.alzheimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class settingdashboard extends AppCompatActivity implements View.OnClickListener  {
    private CardView btnsetlocation,btnsettvprogram,btnsetmeals,btnsetmedicine,btnmainnoty,btnsecurity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingdashboard);
        btnsetlocation=(CardView)findViewById(R.id.btnsetlocation);
        btnsettvprogram=(CardView)findViewById(R.id.btnsettvprogram);
        btnsetmeals=(CardView)findViewById(R.id.btnsetmeals);
        btnsetmedicine=(CardView)findViewById(R.id.btnsetmedicine);
        btnmainnoty=(CardView)findViewById(R.id.btnmainnoty);
        btnsecurity=(CardView)findViewById(R.id.btnsecurity);
        btnsetlocation.setOnClickListener(this);
        btnsettvprogram.setOnClickListener(this);
        btnsetmeals.setOnClickListener(this);
        btnsetmedicine.setOnClickListener(this);
        btnmainnoty.setOnClickListener(this);
        btnsecurity.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        Intent i;
        switch(v.getId())
        {

            case R.id.btnsettvprogram:
                i=new Intent(this,Tvprograms.class);
                startActivity(i);
                break;
            case R.id.btnsetmeals:
                i=new Intent(this,Meals.class);
                startActivity(i);
                break;
            case R.id.btnsetmedicine:
                i=new Intent(this,MedicineActivity.class);
                startActivity(i);
                break;
            case R.id.btnmainnoty:
                i=new Intent(this,Configeralert.class);
                startActivity(i);
                break;
            case R.id.btnsetlocation:
                i=new Intent(this,Confglocation.class);
                startActivity(i);
                break;

            case R.id.btnsecurity:
                i=new Intent(this,Authentication.class);
                startActivity(i);
                break;

        }
    }

}
