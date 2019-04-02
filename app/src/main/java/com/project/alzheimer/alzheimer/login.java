package com.project.alzheimer.alzheimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
EditText txt_user_name,txt_user_password;
    mydatabase _mydatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        txt_user_name=(EditText)findViewById(R.id.txt_user_name);
        txt_user_password=(EditText)findViewById(R.id.txt_user_password);
        _mydatabase=new mydatabase(this);
    }

    public void loginsetting(View view) {
        String username=txt_user_name.getText().toString();
        String password=txt_user_password.getText().toString();
        Intent i ;
        boolean result=false;


        if(!username.trim().isEmpty()&&!password.trim().isEmpty())
        {
            result=_mydatabase.cheklogin(username.trim(),password.trim());
            if(result==true)
            {
                i = new Intent(getBaseContext(), settingdashboard.class);
                startActivity(i);
            }
            else
            {
                Toast t= Toast.makeText(this,"Wrong Password or UserName",Toast.LENGTH_LONG);
                t.setGravity(Gravity.CENTER,0,0);
                t.show();

            }

        }
        else
        {
            Toast t= Toast.makeText(this,"Complete Your Data",Toast.LENGTH_LONG);
            t.setGravity(Gravity.CENTER,0,0);
            t.show();
        }
    }
}
