package com.techbots.betterylevel.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.techbots.betterylevel.utility.AppConstants;
import com.techbots.betterylevel.utility.Utility;

import java.io.File;

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
        MediaPlayer mediaPlayer = MediaPlayer.create(this, Uri.parse(intent.getExtras().getString("song_type")));
        mediaPlayer.start();
        //Utility.getInstance().startSong(this,intent.getExtras().getString("song_type"));
        return super.onStartCommand(intent, flags, startId);
    }
}
