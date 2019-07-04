package com.towhid.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.TaskStackBuilder;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    int massageCount=0;
    // static korte hoy Uri but ame kore nai cz ae app ta background e use korbo na
    Uri alarmSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel =
                    new NotificationChannel("MyNotifications", "MyNotifications", NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);

        }
        long[] patterns = {100, 300, 100, 300};
        alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
     /*   NotificationManager manager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);*/
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "MyNotifications")
                .setContentTitle("this is my title ")
                .setSmallIcon(R.drawable.penguin)

                .setContentText("this is my text")
                .setTicker("New Massage Alert!")

                .setSound(alarmSound)
                .setVibrate(patterns)
                .setNumber(++massageCount);

        Intent i=new Intent(MainActivity.this,NotifActivity.class);
        i.putExtra("notificationId",111);
        i.putExtra("massage","http://www.devosha.com");

        // Task Builder to maintain task for pending intent
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(NotifActivity.class);

        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(i);

        //pass request code and flag
        PendingIntent pendingIntent=stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        // notificationID allows to update the notification tater on
        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        manager.notify(111, builder.build());

    }
}
