package com.project.alzheimer.alzheimer;

import android.app.Service;
import android.bluetooth.BluetoothClass;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ahmed on 3/16/2018.
 */

public class notfyservice extends Service {
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
        MyTimerTask myTask = new MyTimerTask();
       player=MediaPlayer.create(this,R.raw.alzahimer);
        _mydatabase=new mydatabase(this);
        value=_mydatabase.getmainnotfytime();
       permiliscond=Integer.parseInt(value)*60000;
        /*
        player.setLooping(true);
        player.start();
        1 minute =
60 000 milliseconds
        */
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

            player.start();


        }
    }
}
