package com.project.alzheimer.alzheimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Aboutus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutus);
    }

    public void helpclick(View view) {
        Intent i = new Intent(getBaseContext(), Help.class);
        startActivity(i);
    }
}
