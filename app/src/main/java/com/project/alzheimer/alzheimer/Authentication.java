package com.project.alzheimer.alzheimer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
// Authentication for muradi Gym Project
public class Authentication extends AppCompatActivity {

    mydatabase _mydatabase;
    EditText txt_set_user_name,txt_set_user_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authentication);
        txt_set_user_name=(EditText) findViewById(R.id.txt_set_user_name);
        txt_set_user_password=(EditText) findViewById(R.id.txt_set_user_password);
        _mydatabase=new mydatabase(this);
    }
    private void clear()
    {
        txt_set_user_name.getText().clear();
        txt_set_user_password.getText().clear();

    }
    public void updatelogin(View view) {

        String username=txt_set_user_name.getText().toString();
        String password=txt_set_user_password.getText().toString();
        if(! username.trim().isEmpty()&&! password.trim().isEmpty())
        {
            _mydatabase.updateauthorize(username,password);
            Toast t= Toast.makeText(this,"Data changed Success",Toast.LENGTH_LONG);
            t.setGravity(Gravity.CENTER,0,0);
            t.show();
            clear();
        }
        else {
            Toast t= Toast.makeText(this,"Complete your data",Toast.LENGTH_LONG);
            t.setGravity(Gravity.CENTER,0,0);
            t.show();
        }



    }
}
