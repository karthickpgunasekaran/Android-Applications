package com.gmail.kpkarthick1995.attendancecare;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class Receiver extends BroadcastReceiver {
	@Override
    public void onReceive(Context context, Intent intent)
    {
       Intent service1 = new Intent(context, myAlarmService.class);
       context.startService(service1);
        
    }   

	
}
