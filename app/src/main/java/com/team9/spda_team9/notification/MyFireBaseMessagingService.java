package com.team9.spda_team9.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.team9.spda_team9.R;

import java.util.Random;

public class MyFireBaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyToken New";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage){
        super.onMessageReceived(remoteMessage);

        Log.d(TAG, "From: " + remoteMessage.getFrom());

        if(remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload" + remoteMessage.getData());
        }

        String body = remoteMessage.getData().get("Message");
        String title = remoteMessage.getData().get("Title");
        Log.d(TAG,"Message Notification Title:" + title);
        Log.d(TAG,"Message Notification Body:" + body);
        showNotification(title, body);
    }

    public void showNotification(String title, String data)
    {
        String channelID = "fcmchannel";
        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel notificationChannel = new NotificationChannel(channelID,"" +
                    "MyFCMNotification", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription("This is GCM notification");
            notificationChannel.enableLights(true);
            notificationManager.createNotificationChannel(notificationChannel);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelID);
            builder.setAutoCancel(true)
                    .setContentText(data)
                    .setContentTitle(title)
                    .setSmallIcon(R.mipmap.ic_launcher);
            notificationManager.notify(new Random().nextInt(), builder.build());

        } else {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext())
                    .setSmallIcon(R.drawable.ic_dashboard_black_24dp)
                    .setContentTitle(title)
                    .setContentText(data);
            notificationManager.notify(0, builder.build());
        }
    }

    @Override
    public void onNewToken(@NonNull String s){
        super.onNewToken(s);

        Log.d("TokenFireBase",s);
    }
}
