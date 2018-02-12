package com.example.engineer.wifiactivation3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnOn, scanWifi, btnHot;
    EditText ipAdd, port, eTMessage;
    TextView wifi, hotspot;
    public static String ipaddr;
    public static String message;
    public static int conPort;
/*
    private RecyclerView recyclerView;
    private WifiRecycleHolder wifiAdaptor;
    private RecyclerView.LayoutManager layoutManager;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ipAdd = (EditText) findViewById(R.id.ipAdd);
        port = (EditText) findViewById((R.id.port));
        eTMessage = (EditText) findViewById((R.id.message));
        btnOn = (Button) findViewById(R.id.bon);
        scanWifi = (Button) findViewById(R.id.scanWifi);
        wifi = (TextView) findViewById(R.id.data);
        wifi.setMovementMethod(new ScrollingMovementMethod());
        hotspot = (TextView) findViewById(R.id.hotspot);
        btnHot = (Button) findViewById(R.id.BtnHot);


        ipaddr = ipAdd.getText().toString();
        message = eTMessage.getText().toString();
        conPort = Integer.parseInt(port.getText().toString());

        /*recyclerView = (RecyclerView) findViewById(R.id.wifiList);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        wifiAdaptor = new WifiRecyclerAdaptor();
        recyclerView.setAdapter(wifiAdaptor);*/

        //pass the context to the WifiScan class
        final WifiScan wifiscan = new WifiScan(this, wifi);

        btnOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                (new Thread(new ConnectRunnable())).start();
            }
        });

        btnHot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getHotSpotList();

            }
        });

        scanWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wifiscan.wifiScanning();

            }
        });

        ipAdd.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ipaddr = s.toString();
            }
        });

        eTMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                message = s.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        port.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                conPort = Integer.parseInt(s.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

    }

    public void getHotSpotList() {
        StringBuilder sb1 = new StringBuilder();
        int macCount = 0;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("/proc/net/arp"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] splitted = line.split(" +");
                if (splitted != null) {
                    // Basic sanity check
                    String mac = splitted[3];
                    System.out.println("Mac : Outside If " + mac);
                    if (mac.matches("..:..:..:..:..:..")) {
                        macCount++;

                        sb1.append("Mac : " + mac + " IP Address : " + splitted[0]);
                        sb1.append("Mac_Count  " + macCount + " MAC_ADDRESS  " + mac);

                        hotspot.setText(sb1);
                    }
                }
            }
        } catch (Exception e) {

        }
    }

}