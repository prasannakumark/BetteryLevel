package com.techbots.betterylevel;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by leela on 29/12/16.
 */
public class PowerReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intents) {
        //get battery status from the intent
        try {
            IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
            //register broadcast receiver to receive a sticky intent
            Intent intent = context.registerReceiver(null, filter);
            //get battery status from the intent
            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

            float batteryPct = level / (float) scale;

            if (status == BatteryManager.BATTERY_STATUS_CHARGING) {
                showAlert("The battery is charging.", batteryPct, context);
            } else if (status == BatteryManager.BATTERY_STATUS_DISCHARGING) {
                showAlert("The battery is discharging.", batteryPct, context);
            } else if (status == BatteryManager.BATTERY_STATUS_FULL) {
                showAlert("The battery is full.", batteryPct, context);

            }


        } catch (Exception ex) {
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void showAlert(String messg, float chargingPercentage, Context context) {
        /*final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.activity_main);
        dialog.setTitle("No Internet Connection...");
        Button dialogButton = (Button) dialog.findViewById(R.id.ok_button);
        dialogButton.setText(messg + ":" + chargingPercentage);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_PANEL);
        dialog.show();*/
        // Build the notification and add the action.
        Notification newMessageNotification =
                new Notification.Builder(context)
                        .setSmallIcon(R.drawable.abc_ic_menu_overflow_material)
                        .setContentTitle(messg)
                        .setContentText("" + chargingPercentage)
                        .build();

// Issue the notification.
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, newMessageNotification);
    }
}
