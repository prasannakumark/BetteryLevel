package com.techbots.betterylevel.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.techbots.betterylevel.R;
import com.techbots.betterylevel.services.PowerReceiver;

/**
 * Created by leela on 2/1/17.
 */

public class NavigationDawerActivity extends AppCompatActivity {


    TextView textBatteryLevel = null;
    String batteryLevelInfo = "Battery Level";
    Toolbar toolbar;
    ImageView imageView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);
        init();
        registerBatteryLevelReceiver();

    }
    public void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        toolbar.setTitle("BatteryLevel");
        setSupportActionBar(toolbar);
        imageView = (ImageView) findViewById(R.id.settings_image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigationDawerActivity.this, SettingsPageActivity.class);
                startActivity(intent);
            }
        });

        textBatteryLevel = (TextView) findViewById(R.id.txtBatteryInfo);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(battery_receiver);

        super.onDestroy();
    }

    private BroadcastReceiver battery_receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            boolean isPresent = intent.getBooleanExtra("present", false);
            String technology = intent.getStringExtra("technology");
            int plugged = intent.getIntExtra("plugged", -1);
            int scale = intent.getIntExtra("scale", -1);
            int health = intent.getIntExtra("health", 0);
            int status = intent.getIntExtra("status", 0);
            int rawlevel = intent.getIntExtra("level", -1);
            int voltage = intent.getIntExtra("voltage", 0);
            int temperature = intent.getIntExtra("temperature", 0);
            int level = 0;

            Bundle bundle = intent.getExtras();

            Log.v("BatteryLevel", bundle.toString());

            if (isPresent) {
                if (rawlevel >= 0 && scale > 0) {
                    level = (rawlevel * 100) / scale;
                }

                String info = "Battery Level     : " +  " "+level +"%\n";
                info +=      ("Technology       : " +" "+ technology +  "\n");
                info +=      ("Plugged             : " +" "+ getPlugTypeString(plugged) +  "\n");
                info +=      ("Health                : " +" "+ getHealthString(health) +  "\n");
                info +=      ("Status                : " +" "+ getStatusString(status) +  "\n");
                info +=      ("Voltage              : " +" "+ voltage + "\n");
                info +=      ("Temperature     : " + " "+temperature + "\n");

                setBatteryLevelText(info);
            } else {
                setBatteryLevelText("Battery not present!!!");
            }
        }
    };

    private String getPlugTypeString(int plugged) {
        String plugType = "Unknown";

        switch (plugged) {
            case BatteryManager.BATTERY_PLUGGED_AC:
                plugType = "AC";
                break;
            case BatteryManager.BATTERY_PLUGGED_USB:
                plugType = "USB";
                break;
        }

        return plugType;
    }

    private String getHealthString(int health) {
        String healthString = "Unknown";

        switch (health) {
            case BatteryManager.BATTERY_HEALTH_DEAD:
                healthString = "Dead";
                break;
            case BatteryManager.BATTERY_HEALTH_GOOD:
                healthString = "Good";
                break;
            case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                healthString = "Over Voltage";
                break;
            case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                healthString = "Over Heat";
                break;
            case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                healthString = "Failure";
                break;
        }

        return healthString;
    }

    private String getStatusString(int status) {
        String statusString = "Unknown";

        switch (status) {
            case BatteryManager.BATTERY_STATUS_CHARGING:
                statusString = "Charging";
                break;
            case BatteryManager.BATTERY_STATUS_DISCHARGING:
                statusString = "Discharging";
                break;
            case BatteryManager.BATTERY_STATUS_FULL:
                statusString = "Full";
                break;
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                statusString = "Not Charging";
                break;
        }

        return statusString;
    }

    private void setBatteryLevelText(String text) {
        textBatteryLevel.setText(text);
    }

    private void registerBatteryLevelReceiver() {
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);

        registerReceiver(battery_receiver, filter);
    }


}

