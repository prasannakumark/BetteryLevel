package com.techbots.betterylevel;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

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

