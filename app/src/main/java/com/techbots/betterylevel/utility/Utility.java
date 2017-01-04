package com.techbots.betterylevel.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;

/**
 * Created by Prasanna on 1/3/17.
 */
public class Utility {
    private static Utility ourInstance = new Utility();

    //mediaplayer should be one, only one song we can play at time
    public static MediaPlayer mediaPlayer;

    public static Utility getInstance() {
        return ourInstance;
    }

    private Utility() {
    }

    /**
     * Create sharedpreference for save song uri so that we can read easly
     *
     * @param context return sharedPreferences
     */
    public SharedPreferences getSharedPreference(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(AppConstants.DB_SONGS, Context.MODE_PRIVATE);
        sharedPreferences.edit().commit();
        return sharedPreferences;
    }

    /**
     * save song uri in sharedpreferance
     *
     * @param songName
     * @param context
     * @param songType
     */
    public void addSongUriToSharedPreference(String songName, Context context, String songType) {
        SharedPreferences sharedPreferences = getSharedPreference(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(songType, songName);
        editor.commit();
    }

    /**
     * play song
     */
    public MediaPlayer startSong(Context context, String strUri) {
        Uri uri = Uri.parse(strUri);
        stopSong();
        mediaPlayer = MediaPlayer.create(context, uri);
        mediaPlayer.start();
        return mediaPlayer;
    }

    /**
     * stop song
     */
    public void stopSong() {
        if (mediaPlayer != null && mediaPlayer.isPlaying())
            mediaPlayer.stop();
    }
}
