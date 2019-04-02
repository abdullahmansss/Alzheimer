package com.project.alzheimer.alzheimer;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 3/24/2018.
 */

public class mdicnotfyservice extends Service {


    private MediaPlayer player;
    mydatabase _mydatabase;
    Timer myTimer;
    String value="";
    int permiliscond=0;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        myTimer = new Timer();
        mdicnotfyservice.MyTimerTask myTask = new mdicnotfyservice.MyTimerTask();
       player=MediaPlayer.create(this,R.raw.medicine);




        permiliscond=30000;

        myTimer.schedule(myTask,0,permiliscond);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        myTimer.cancel();
        player.stop();
        player.release();

    }
    class MyTimerTask extends TimerTask {
        public void run() {

           // player.start();
String mdctime;
            _mydatabase=new mydatabase(mdicnotfyservice.this);

            Cursor cursor=_mydatabase.getalldata();

            int count=(int)_mydatabase.getmdiccount();
            //Toast.makeText(this,String.valueOf(count) ,Toast.LENGTH_LONG).show();

            medicianpropclass mdic_data[]=new medicianpropclass[count];
            if(cursor.moveToFirst()){

                for (int x=0;x<count;x++)
                {
                    mdic_data[x]=new medicianpropclass(cursor.getString(cursor.getColumnIndex("medic_name")),
                            cursor.getString(cursor.getColumnIndex("medic_time")));
                    mdctime=cursor.getString(cursor.getColumnIndex("medic_time"));


                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

                        Date EndTime = dateFormat.parse(mdctime);

                        Date CurrentTime = dateFormat.parse(dateFormat.format(new Date()));
                        long mills =  CurrentTime.getTime()-EndTime.getTime() ;
                        long diffMinutes = mills / (60 * 1000);
                        if (CurrentTime.after(EndTime)&&diffMinutes<5&&mills>=1&&diffMinutes>=0)
                        {
                            player.start();
                            // System.out.println("timeeee end ");
                           // Toast.makeText(this,"timeeee end ",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            //Toast.makeText(this,"not yeat ",Toast.LENGTH_LONG).show();
                        }


                    }
                    catch (Exception ex)
                    {

                    }








                    cursor.moveToNext();
                }
            }

        }
    }


}
