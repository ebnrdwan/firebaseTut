package com.example.android.firebasetut;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Abdulrhman on 10/06/2017.
 *
 */

public class firebaseMessagService extends FirebaseMessagingService {
    private static final String TAG = "MESTAG";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("mes",remoteMessage.getNotification().getBody());
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder notificaitonBuilder = new NotificationCompat.Builder(this);
        notificaitonBuilder.setContentText(remoteMessage.getNotification().getBody());
        notificaitonBuilder.setContentTitle(remoteMessage.getNotification().getTitle());
        notificaitonBuilder.setAutoCancel(true);
        notificaitonBuilder.setSmallIcon(R.mipmap.ic_launcher);
        notificaitonBuilder.setContentIntent(pendingIntent);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0,notificaitonBuilder.build());

    }
}
