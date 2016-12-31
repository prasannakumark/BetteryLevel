package com.techbots.betterylevel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView batteryTxt;

    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context ctxt, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            batteryTxt.setText("Battery Charging Level: " +String.valueOf(level) + "%");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* batteryTxt = (TextView)findViewById(R.id.text_view) ;




        //create an IntentFilter object that matches battery change action
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        //register broadcast receiver to receive a sticky intent
        Intent intent = this.registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        //get battery status from the intent
        int status=intent.getIntExtra(BatteryManager.EXTRA_STATUS,-1);
        if(status==BatteryManager.BATTERY_STATUS_CHARGING)
            Toast.makeText(this,"The battery is charging.", Toast.LENGTH_LONG).show();

        else if (status==BatteryManager.BATTERY_STATUS_DISCHARGING)
            Toast.makeText(this,"The battery is discharging.", Toast.LENGTH_LONG).show();

        else if(status== BatteryManager.BATTERY_STATUS_FULL)
            Toast.makeText(this,"The battery is full.", Toast.LENGTH_LONG).show();*/
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
       // this.unregisterReceiver(mBatInfoReceiver);
    }
}
