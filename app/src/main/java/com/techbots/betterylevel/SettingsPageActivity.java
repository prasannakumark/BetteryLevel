package com.techbots.betterylevel;

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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by leela on 2/1/17.
 */

public class SettingsPageActivity extends AppCompatActivity {

    Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        button = (Button) findViewById(R.id.select_song);

         MediaPlayer mp = new MediaPlayer();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* if (mp.isPlaying()) {
                    mp.stop();
                }*/

                /*try {
                    mp.reset();
                    AssetFileDescriptor afd;
                    afd = getAssets().openFd("AudioFile.mp3");
                    mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                    mp.prepare();
                    mp.start();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
             Intent intent_upload = new Intent();
                intent_upload.setType("audio/*");
                intent_upload.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent_upload,1);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {

            if (resultCode == RESULT_OK) {

                //the selected audio.
                Uri uri = data.getData();
                //writeToSDFile();



            }
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

   /* private void writeToSDFile() {

        // Root of the external file system

        File root0 = android.os.Environment.getExternalStorageDirectory();

        File root = this.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);

        button.append("\n\nEXTERNAL FILE SYSTEM ROOT DIRECTORY:\n" + root0);

      button.append("\n\nEXTERNAL APP DATA ROOT DIRECTORY:\n" + root);

        // Create a Documents/download subdirectory in the data area for this app
        // See http://stackoverflow.com/questions/3551821/android-write-to-sd-card-folder

        File dir = new File(root.getAbsolutePath() + "/download");
        dir.mkdirs();
        File file = new File(dir, "audio.mp3");

        // Must catch FileNotFoundException and IOException
        try {
            FileOutputStream f = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(f);
            pw.println("Howdy do to you,");
            pw.println("and the horse you rode in on.");
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.v("TAG", "File not found");
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("TAG", "I/O exception");
        }
        button.append("\n\nFILE WRITTEN TO:\n" + file);
    }*/


}
