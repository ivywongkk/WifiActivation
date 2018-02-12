package com.example.engineer.wifiactivation3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/*
  Created by engineer on 2/1/18.
*/

public class WifiScan {

    WifiManager mainWifi;
    WifiReceiver receiverWifi;
    List<ScanResult> wifiList;
    StringBuilder sb = new StringBuilder();
    private String TAG = "WifiScan";
    Context mContext ;
    TextView wlocalTv;

    public WifiScan(Context mContext, TextView wifiTv) {
        this.mContext = mContext;
        this.wlocalTv = wifiTv;
    }

    public void wifiScanning() {

        mainWifi = (WifiManager) mContext.getApplicationContext()
                .getSystemService(Context.WIFI_SERVICE);
        Log.d(TAG, "get Wifi service");

        if (!mainWifi.isWifiEnabled())
        {
            Toast.makeText(mContext.getApplicationContext(), "Enabling Wifi",
                    Toast.LENGTH_LONG).show();

            mainWifi.setWifiEnabled(true);
            Log.d(TAG, "WifiOn");
        }

        receiverWifi = new WifiReceiver();
        mContext.registerReceiver(receiverWifi, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        mainWifi.startScan();
        wlocalTv.setText("Starting Scan...");
    }


    class WifiReceiver extends BroadcastReceiver {

        // This method call when number of wifi connections changed
        public void onReceive(Context c, Intent intent) {

            sb = new StringBuilder();

            wifiList = mainWifi.getScanResults();

            sb.append("\n   Number Of Wifi connections :" + wifiList.size() + "\n\n");


            for (int i = 0; i < wifiList.size(); i++) {
                sb.append(new Integer(i + 1).toString() + ". ");
                sb.append((wifiList.get(i)).toString());
                sb.append("\n\n");
            }

           wlocalTv.post(new Runnable() {
                             @Override
                             public void run() {
                                 wlocalTv.setText(sb);
                             }
                         });

        }
    }}
