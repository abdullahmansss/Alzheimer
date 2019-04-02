package com.project.alzheimer.alzheimer;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ahmed on 3/1/2018.
 */

public class mydatabase extends SQLiteOpenHelper {
    /*
    public static final String dbname="alzheimer.sqlite";
    public static final String dblocation="/data/com.project.alzheimer.alzheimer/data/database/";
    private Context mycontext;
    private SQLiteDatabase mydb;
    public mydatabase(Context context) {
        super(context, dbname, null, 1);
        this.mycontext=context;
    }
*/


    public static final String dbname="alzheimer.sqlite";
    public static  String dbpath="";
    private Context mycontext;
    private SQLiteDatabase mydb;
    public mydatabase(Context context) {
        super(context, dbname, null, 1);
        this.mycontext=context;











        if(Build.VERSION.SDK_INT>=17)
        {
            dbpath=context.getApplicationInfo().dataDir+"/databases/";
        }
        else
        {
            dbpath="/data/"+"/data/"+context.getPackageName()+"/databases/";
        }
    }
    private boolean dbexist()
    {
        File dbfile=new File(dbpath+dbname);
        return dbfile.exists();
    }
    public void createdatabase() throws IOException
    {
        boolean chkdb=dbexist();
        if(!chkdb)
        {
            this.getReadableDatabase();
            this.close();
            try {
                copydatabase();
                Log.e("database","database created");
            }
            catch (IOException messag)
            {
                throw new Error("copy database error");
            }
        }
    }
    private void copydatabase() throws IOException
    {
        InputStream minput=mycontext.getAssets().open(dbname);
        String outfilename=dbpath+dbname;
        OutputStream moutput=new FileOutputStream(outfilename);
        byte[] mbuffer=new byte[1024];
        int mlength;
        while ((mlength=minput.read(mbuffer))>0)
        {
            moutput.write(mbuffer,0,mlength);
        }
        moutput.flush();
        moutput.close();
        minput.close();

    }







    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public  void opendatabase()
    {
        //String dbpath=mycontext.getDatabasePath(dbname).getPath();
        String dbpath=mycontext.getDatabasePath(dbname).getPath();
        if(mydb!=null && mydb.isOpen())
        {
            return;
        }


        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");

        try {
            Date EndDate = dateFormat.parse("23/04/2019");
            Date lstdat=dateFormat.parse("20/04/2018");
            Date CurrenDate = dateFormat.parse(dateFormat.format(new Date()));

            if (CurrenDate.after(EndDate)||CurrenDate.before(lstdat))
            {

                closedatabase();

            }
            else
            {
                mydb=SQLiteDatabase.openDatabase(dbpath,null,SQLiteDatabase.OPEN_READWRITE);
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }





    }
    public void insertdata(String name,String email,String mobile,String phone,String address,String relative,String notes)
    {
        opendatabase();
        ContentValues myvalues=new ContentValues();
        myvalues.put("name",name);
        myvalues.put("email",email);
        myvalues.put("mobile",mobile);
        myvalues.put("phone",phone);
        myvalues.put("address",address);
        myvalues.put("relative",relative);
        myvalues.put("notes",notes);
        mydb.insert("t_profile",null,myvalues);
    }
    public void insertmealsdata(String meal_name,String meal_time)
    {
        opendatabase();
        ContentValues myvalues=new ContentValues();
        myvalues.put("meal_name",meal_name);
        myvalues.put("meal_time",meal_time);
        mydb.insert("t_meals",null,myvalues);
    }

    public String getmainnotfytime()
    {
        String value="";
        opendatabase();
        Cursor rs=mydb.rawQuery("select value from t_confg where name='mainnotfy'",null);
        if(rs!=null && rs.getCount() > 0)
        {
            rs.moveToFirst();

            value=rs.getString(0);

        }


        closedatabase();
        return value;
    }


    public String data()
    {
        String name, email, mobile,phone, address,relative,notes,result="";
        opendatabase();
        Cursor rs=mydb.rawQuery("select * from t_profile where id=1",null);
        if(rs!=null && rs.getCount() > 0)
        {
            rs.moveToFirst();

            name=rs.getString(1);email=rs.getString(2);mobile=rs.getString(3);phone=rs.getString(4);
            address=rs.getString(5);relative=rs.getString(6);notes=rs.getString(7);
            result=name+"-"+ email+"-"+ mobile+"-"+phone+"-"+ address+"-"+relative+"-"+notes ;
        }



        return result;
    }

    public String smsnum()
    {
        String result="";
        opendatabase();
        Cursor rs=mydb.rawQuery("select stringvalue from t_confg where id=2",null);
        if(rs!=null && rs.getCount() > 0)
        {
            rs.moveToFirst();

            result=rs.getString(0);
        }



        return result;
    }

    public boolean cheklogin(String username,String password)
    {

        opendatabase();
        Cursor rs=mydb.rawQuery("select * from t_setadmin where username='"+username+"' and password='"+password+"'",null);
        if(rs!=null && rs.getCount() > 0)
        {
           return true;
        }



        return false;
    }

    public void inserttvprog(String tvprogname,String tvprogtime)
    {
        opendatabase();
        ContentValues myvalues=new ContentValues();
        myvalues.put("tvprogname",tvprogname);

        myvalues.put("tvprogtime",tvprogtime);
        mydb.insert("t_tvprogam",null,myvalues);
    }
    public void insertmedic(String medic_name,String medic_time)
    {
        opendatabase();
        ContentValues myvalues=new ContentValues();
        myvalues.put("medic_name",medic_name);

        myvalues.put("medic_time",medic_time);
        mydb.insert("t_medication",null,myvalues);
    }
public Cursor getalldata()
{
    opendatabase();
    String[]columns=new String[]{"medic_name","medic_time"};
    Cursor cursor=mydb.query("t_medication",columns,null,null,null,null,null);
    return cursor;
}
public long getmdiccount()
{
    opendatabase();
    long count= DatabaseUtils.queryNumEntries(mydb,"t_medication");
    return count;
}

    public long getmealccount()
    {
        opendatabase();
        long count= DatabaseUtils.queryNumEntries(mydb,"t_meals");
        return count;
    }

    public Cursor getallmealdata()
    {
        opendatabase();
        String[]columns=new String[]{"meal_name","meal_time"};
        Cursor cursor=mydb.query("t_meals",columns,null,null,null,null,null);
        return cursor;
    }

    public Cursor getalltvprohdata()
    {
        opendatabase();
        String[]columns=new String[]{"tvprogname","tvprogtime"};
        Cursor cursor=mydb.query("t_tvprogam",columns,null,null,null,null,null);
        return cursor;
    }
    public long gettvprogcount()
    {
        opendatabase();
        long count= DatabaseUtils.queryNumEntries(mydb,"t_tvprogam");
        return count;
    }
    public void updatedatamainnotifytime(int value)
    {
        opendatabase();
        ContentValues myvalues=new ContentValues();
        myvalues.put("value",value);

        mydb.update("t_confg",myvalues,"id=?",new String[]{"1"});
        closedatabase();
    }
    public void updatesmsphone(String value)
    {
        opendatabase();
        ContentValues myvalues=new ContentValues();

        myvalues.put("stringvalue",value);

        mydb.update("t_confg",myvalues,"id=?",new String[]{"2"});
        closedatabase();
    }

    public void updateauthorize(String username,String password)
    {
        opendatabase();
        ContentValues myvalues=new ContentValues();
        myvalues.put("username",username);
        myvalues.put("password",password);
       mydb.update("t_setadmin",myvalues,"id=?",new String[]{"1"});
        closedatabase();
    }




    public void updatedata(String name,String email,String mobile,String phone,String address,String relative,String notes)
    {
        opendatabase();
        ContentValues myvalues=new ContentValues();
        myvalues.put("name",name);
        myvalues.put("email",email);
        myvalues.put("mobile",mobile);
        myvalues.put("phone",phone);
        myvalues.put("address",address);
        myvalues.put("relative",relative);
        myvalues.put("notes",notes);
        mydb.update("t_profile",myvalues,"id=?",new String[]{"1"});
        closedatabase();
    }
    public  void closedatabase()
    {

        if(mydb!=null)
        {
            mydb.close();
        }

    }
}
