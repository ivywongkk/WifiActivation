package com.example.engineer.wifiactivation3;

import android.util.Log;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by engineer on 2/1/18.
 */

public class ConnectRunnable implements Runnable {
        private String TAG = "TCPClient";
        private long startTime = 0l;

        public void run() {
            String localhost = MainActivity.ipaddr;
            int port = MainActivity.conPort;
            try {
                Log.d(TAG, "Connecting...");

                InetAddress serverAddr = InetAddress.getByName(localhost);
                startTime = System.currentTimeMillis();

                Socket s = new Socket();
                s.connect(new InetSocketAddress(serverAddr, port ),5000);

                long time = System.currentTimeMillis() - startTime;
                Log.d(TAG, "Connected, duration: " + time + "ms");

                PrintWriter writer = new PrintWriter((s.getOutputStream()));
                //int hexopen = 0xa00100a1;

                //int open = Integer.parseInt ("ff00aa55", 16);
                //open code: A00100A1
                String msg = MainActivity.message;
                writer.println(msg);
                writer.close();

            } catch (Exception e) {
            }
            Log.d(TAG, "Connection thread stopped");
        }
}
