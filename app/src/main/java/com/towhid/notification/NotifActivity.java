package com.towhid.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationManager;
import android.os.Bundle;
import android.text.util.Linkify;
import android.widget.TextView;

public class NotifActivity extends AppCompatActivity {
TextView showText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif);
        showText=(TextView)findViewById(R.id.showText);
        showText.setText(reciveData());
        Linkify.addLinks(showText,Linkify.ALL);

    }

    private String reciveData() {
        String massage="";
        int id=0;
        Bundle bundle=getIntent().getExtras();
        if (bundle!=null)
        {
            id= bundle.getInt("notificationId");
            massage= bundle.getString("massage");

        }
        massage="notificationId : "+id+"\nmassage : "+massage;
        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        manager.cancel(id);
        return massage;
    }
}
