package com.project.alzheimer.alzheimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Insertprofile extends AppCompatActivity {
    mydatabase _mydatabase;
    EditText txtname,txtmail,txtphone,txtmobile,txtaddress,txtrelative,txtnotes;
    private static int RESULT_LOAD_IMAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insertprofile);
        _mydatabase=new mydatabase(this);
        txtname=(EditText) findViewById(R.id.txtname);
        txtmail=(EditText) findViewById(R.id.txtmail);
        txtphone=(EditText) findViewById(R.id.txtphone);
        txtmobile=(EditText) findViewById(R.id.txtmobile);
        txtaddress=(EditText) findViewById(R.id.txtaddress);
        txtrelative=(EditText) findViewById(R.id.txtrelative);
        txtnotes=(EditText) findViewById(R.id.txtnotes);
    }
private void clear()
{
    txtname.getText().clear();
    txtmail.getText().clear();
    txtphone.getText().clear();
    txtmobile.getText().clear();
    txtaddress.getText().clear();
    txtrelative.getText().clear();
    txtnotes.getText().clear();
}
    public void insertdata(View view) {


        String name=txtname.getText().toString();
        String mail=txtmail.getText().toString();
        String phone=txtphone.getText().toString();
        String mobile=txtmobile.getText().toString();
        String address=txtaddress.getText().toString();
        String relative=txtrelative.getText().toString();
        String notes=txtnotes.getText().toString();




if(!name.trim().isEmpty()&&!mail.trim().isEmpty()&&
        !phone.trim().isEmpty()&&!mobile.trim().isEmpty()&&!address.trim().isEmpty())
{

    _mydatabase.updatedata( name.trim(),mail.trim(), mobile.trim(),phone.trim(),address.trim(),relative.trim(),notes.trim());
    Toast t= Toast.makeText(this,"your profile changed",Toast.LENGTH_LONG);
    t.setGravity(Gravity.CENTER,0,0);
    t.show();
    clear();
}
else
{
    Toast t=  Toast.makeText(this,"please fill your data",Toast.LENGTH_LONG);
    t.setGravity(Gravity.CENTER,0,0);
    t.show();
}


    }

    public void opengalary(View view)
    {
        Intent i = new Intent(
                Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(getApplicationContext(), ActivitymainActivity.class);
        startActivity(intent);
    }
}
