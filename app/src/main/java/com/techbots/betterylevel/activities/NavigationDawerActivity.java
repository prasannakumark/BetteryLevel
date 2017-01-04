package com.techbots.betterylevel.activities;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.techbots.betterylevel.R;
import com.techbots.betterylevel.services.PowerReceiver;

/**
 * Created by leela on 2/1/17.
 */

public class NavigationDawerActivity extends AppCompatActivity {
    Toolbar toolbar;
    ImageView imageView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);
        init();

    }
    public void init(){
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        toolbar.setTitle("BatteryLevel");
        setSupportActionBar(toolbar);
        imageView = (ImageView)findViewById(R.id.settings_image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigationDawerActivity.this, SettingsPageActivity.class);
                startActivity(intent);
            }
        });
    }
}

