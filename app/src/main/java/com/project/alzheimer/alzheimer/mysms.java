package com.project.alzheimer.alzheimer;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.widget.Toast;

import static android.support.v4.app.ActivityCompat.requestPermissions;

/**
 * Created by Administrator on 4/1/2018.
 */

public class mysms  {

   public  void sendSmsMsg(String mblNumVar, String smsMsgVar)
    {



        SmsManager smsMgrVar = SmsManager.getDefault();
        smsMgrVar.sendTextMessage(mblNumVar, null, smsMsgVar, null, null);

    }
}
