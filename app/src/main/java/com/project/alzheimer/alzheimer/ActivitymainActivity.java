package com.project.alzheimer.alzheimer;

import android.Manifest;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class ActivitymainActivity extends AppCompatActivity {
    mydatabase _mydatabase;
    TextView txtname,txtemail,txtmobile,txtphone,txtaddress,txtrelative,txtnotes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitymain);

        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 100);



        _mydatabase=new mydatabase(this);

        try
        {
            _mydatabase.createdatabase();
        }
        catch(IOException ex) {
            throw new Error("unable to create database");
        }






        txtname=(TextView) findViewById(R.id.txtname);
        txtemail=(TextView) findViewById(R.id.txtemail);
        txtphone=(TextView) findViewById(R.id.txtphone);
        txtmobile=(TextView) findViewById(R.id.txtmobile);
        txtaddress=(TextView) findViewById(R.id.txtaddress);
        txtrelative=(TextView) findViewById(R.id.txtrelative);
        txtnotes=(TextView) findViewById(R.id.txtnotes);
/**/
if( !_mydatabase.data().trim().isEmpty())
{
    String[] split = _mydatabase.data().split("-");

    txtname.setText(split[0]);
    txtemail.setText(split[1]);
    txtmobile.setText(split[2]);
    txtphone.setText(split[3]);
    txtaddress.setText(split[4]);
    txtrelative.setText(split[5]);
    txtnotes.setText(split[6]);

}
else
{
  Toast t=  Toast.makeText(this,"Please Fill Your Profile",Toast.LENGTH_LONG);
    t.setGravity(Gravity.CENTER,0,0);
    t.show();
}

    }

    public void startmain(View view) {
        Intent i = new Intent(getBaseContext(), DashboardActivity.class);

        startActivity(i);
    }

    public void editprof(View view) {
        Intent i = new Intent(getBaseContext(), Insertprofile.class);

        startActivity(i);
    }
}
