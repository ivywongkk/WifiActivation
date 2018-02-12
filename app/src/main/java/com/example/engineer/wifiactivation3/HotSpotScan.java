/*
package com.example.engineer.wifiactivation3;

import java.io.BufferedReader;
import java.io.FileReader;

*/
/**
 * Created by engineer on 2/8/18.
 *//*


public class HotSpotScan {
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
                    String mac = splitted[3];
                    System.out.println("Mac : Outside If " + mac);
                    if (mac.matches("..:..:..:..:..:..")) {
                        macCount++;
                   */
/* ClientList.add("Client(" + macCount + ")");
                    IpAddr.add(splitted[0]);
                    HWAddr.add(splitted[3]);
                    Device.add(splitted[5]);*//*

                        sb1.append("Mac : " + mac + " IP Address : " + splitted[0]);
                        sb1.append("Mac_Count  " + macCount + " MAC_ADDRESS  " + mac);

                        hotspot.setText(sb1);
                        */
/*Toast.makeText(
                                getApplicationContext(),
                                "Mac_Count  " + macCount + "   MAC_ADDRESS  "
                                        + mac, Toast.LENGTH_SHORT).show();*//*


                    }
               */
/* for (int i = 0; i < splitted.length; i++)
                    System.out.println("Addressssssss     "+ splitted[i]);*//*


                }
            }
        } catch (Exception e) {

        }
    }
}
*/
