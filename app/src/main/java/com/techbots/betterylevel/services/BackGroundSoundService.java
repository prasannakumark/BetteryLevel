package com.techbots.betterylevel.services;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.techbots.betterylevel.R;
import com.techbots.betterylevel.activities.MainActivity;
import com.techbots.betterylevel.utility.Utility;

import java.io.IOException;

public class BackGroundSoundService extends Service {
    public BackGroundSoundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Utility.getInstance().startSong(this, intent.getExtras().getString("song_type"));
        addView(this);
        return Service.START_STICKY_COMPATIBILITY;
    }

    public void addView(Context context) {
        NotificationCompat.Builder mBuilder =   new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.settings) // notification icon
                .setContentTitle("Notification!") // title for notification
                .setContentText("Hello word") // message for notification
                .setAutoCancel(true); // clear notification after click
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this,0,intent,Intent.FLAG_ACTIVITY_NEW_TASK);
        mBuilder.setContentIntent(pi);
        final NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, mBuilder.build());
        Handler h = new Handler();
        long delayInMilliseconds = 5000;
        h.postDelayed(new Runnable() {
            public void run() {
                mNotificationManager.cancel(0);
            }
        }, delayInMilliseconds);

    }
}
