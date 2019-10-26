package com.gmail.kpkarthick1995.attendancecare;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
                            
 
@SuppressLint("NewApi")
public class myAlarmService extends Service 
{
      
   private NotificationManager mManager;
 
    @Override
    public IBinder onBind(Intent arg0)
    {
       // TODO Auto-generated method stub
        return null;
    }
 
    @Override
    public void onCreate() 
    {
       // TODO Auto-generated method stub  
       super.onCreate();
     
    }
 
   @SuppressWarnings("static-access")
   @Override
   public void onStart(Intent intent, int startId)
   {
       super.onStart(intent, startId);
       mManager = (NotificationManager) this.getApplicationContext().getSystemService(this.getApplicationContext().NOTIFICATION_SERVICE);
       Intent intent1 = new Intent(this.getApplicationContext(),MainScreen.class);
     
       Notification notification = new Notification(R.drawable.ic_launcher,"forgot???!", System.currentTimeMillis());
       intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP| Intent.FLAG_ACTIVITY_CLEAR_TOP);
 
       PendingIntent pendingNotificationIntent = PendingIntent.getActivity( this.getApplicationContext(),0, intent1,PendingIntent.FLAG_UPDATE_CURRENT);
       notification.flags |= Notification.FLAG_AUTO_CANCEL;
       notification.setLatestEventInfo(this.getApplicationContext(), "Attendence Care", "Mark your attendence for this week buddy!", pendingNotificationIntent);
 
       mManager.notify(0, notification);
      //  mManager.cancel(0);
    }
 
    @Override
    public void onDestroy() 
    {
        // TODO Auto-generated method stub
        super.onDestroy();
    }
 
}