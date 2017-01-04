package com.techbots.betterylevel.activities;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.techbots.betterylevel.R;
import com.techbots.betterylevel.utility.Utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 * Created by leela on 2/1/17.
 */

public class SettingsPageActivity extends AppCompatActivity {

    Button buttonChargeConected,buttonChargeDisconnected,buttonChargeFulled;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        buttonChargeConected = (Button) findViewById(R.id.select_song);
        buttonChargeDisconnected = (Button) findViewById(R.id.select_song_discharge);
        buttonChargeFulled = (Button) findViewById(R.id.select_song_charge_full);

        buttonChargeFulled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_upload = new Intent();
                intent_upload.setType("audio/mpeg");
                intent_upload.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent_upload, 3);

            }
        });

        buttonChargeDisconnected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_upload = new Intent();
                intent_upload.setType("audio/mpeg");
                intent_upload.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent_upload, 2);

            }
        });

        buttonChargeConected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_upload = new Intent();
                intent_upload.setType("audio/mpeg");
                intent_upload.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent_upload, 1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            try {
                Uri uri = data.getData();
                Utility.getInstance().addSongUriToSharedPreference(uri.toString(),this,""+requestCode);
                Toast.makeText(this,"song uploaded",Toast.LENGTH_SHORT).show();
            } catch (Exception ex) {
            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }
}
