package com.example.myapplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;


import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class NotifyWorker extends Worker {
    Context contextt;

    public NotifyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {

        super(context, workerParams);
        contextt =context;
    }

    @NonNull
    @Override
    public Result doWork() {
        displayNotification("My Worker", "My notification");
        return Result.success();
    }
    private void displayNotification(String title, String task) {
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {


            NotificationChannel channel = new NotificationChannel("tinu", "tinu", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }
        Intent notificationIntent = new Intent(contextt, MainActivity.class);

        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent intent = PendingIntent.getActivity(contextt, 0,
                notificationIntent, 0);


        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext(), "tinu")
                .setContentTitle(title)
                .setContentText(task)
                .setSound(alarmSound)
                .setSmallIcon(R.mipmap.ic_launcher);
        PendingIntent contentIntent = PendingIntent.getActivity(contextt, 0,
                new Intent(contextt, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(contentIntent);

        notificationManager.notify(1, notification.build());
    }
}
