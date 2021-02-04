package com.team9.spda_team9;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

public class FireBaseMessaging extends FirebaseMessagingService {



        private static final String TAG = "MyToken New";

        @Override
        public void onMessageReceived(RemoteMessage remoteMessage){
            super.onMessageReceived(remoteMessage);

            Log.d(TAG, "From: " + remoteMessage.getFrom());

            if(remoteMessage.getData().size()>0) {
                Log.d(TAG, "Message data payload" + remoteMessage.getData());

            }
            // Check if message contains a notification payload.
            if (remoteMessage.getNotification() != null) {

                String body=remoteMessage.getNotification().getBody();
                String title=remoteMessage.getNotification().getTitle();
                Log.d(TAG,"Message Notification Title:"+title);
                Log.d(TAG,"Message Notification Body:"+body);
                showNotification(title,body);
            }


        }
        public void showNotification(String title,String data)
        {
            String channelID="fcmchannel";
            NotificationManager notificationManager=(NotificationManager) getSystemService
                    (NOTIFICATION_SERVICE);
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
            {
                NotificationChannel notificationChannel=new NotificationChannel(channelID,"" +
                        "MyFCMNotification",NotificationManager.IMPORTANCE_HIGH);
                notificationChannel.setDescription("This is GCM notification");
                notificationChannel.enableLights(true);
                notificationManager.createNotificationChannel(notificationChannel);
            }
            NotificationCompat.Builder builder=new NotificationCompat.Builder(this,channelID);
            builder.setAutoCancel(true)
                    .setContentText(data)
                    .setContentTitle(title)
                    .setSmallIcon(R.mipmap.ic_launcher);
            notificationManager.notify(new Random().nextInt(),builder.build());


    }

    @Override
    public void onNewToken(@NonNull String s){
            super.onNewToken(s);

            Log.d("TokenFireBase",s);
    }


}
