package com.project.alzheimer.alzheimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Switch;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {
   private CardView btnlocation,btnsetting,btnmeals,btnmedicine,btntvprogram;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitydashboard);
        btnlocation=(CardView)findViewById(R.id.btnlocation);
        btnsetting=(CardView)findViewById(R.id.btnsetting);
        btnmeals=(CardView)findViewById(R.id.btnmeals);
        btnmedicine=(CardView)findViewById(R.id.btnmedicine);
        btntvprogram=(CardView)findViewById(R.id.btntvprogram);

        btnlocation.setOnClickListener(this);
        btnsetting.setOnClickListener(this);
        btnmeals.setOnClickListener(this);
        btnmedicine.setOnClickListener(this);
        btntvprogram.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch(v.getId())
        {

            case R.id.btnlocation:
                i=new Intent(this,Sendlocation.class);
                startActivity(i);
                break;
            case R.id.btnsetting:
                i=new Intent(this,login.class);
                startActivity(i);
                break;
            case R.id.btnmeals:
                i=new Intent(this,Mymeals.class);
                startActivity(i);
                break;
            case R.id.btnmedicine:
                i=new Intent(this,Mymedicine.class);
                startActivity(i);
                break;
            case R.id.btntvprogram:
                i=new Intent(this,Mytvprogram.class);
                startActivity(i);
                break;
        }
    }
}
