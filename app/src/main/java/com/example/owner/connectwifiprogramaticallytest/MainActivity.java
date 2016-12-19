package com.example.owner.connectwifiprogramaticallytest;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initWifiConnectBroadcastReceiver();
        turnOnWifi();
        connectToWifiHotspot();
    }



    public class WifiConnectReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {



//            final String action = intent.getAction();
//            if (action.equals(WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION)) {
//                if (intent.getBooleanExtra(WifiManager.EXTRA_SUPPLICANT_CONNECTED, false)){
//                    //do stuff
//                    Toast.makeText(MainActivity.this, "와이파이 연결됨!", Toast.LENGTH_SHORT).show();
//                } else {
//                    // wifi connection was lost
//                }
//            }
        }
    }


    private void initWifiConnectBroadcastReceiver() {
        WifiConnectReceiver wifiConnectReceiver = new WifiConnectReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION);
        registerReceiver(wifiConnectReceiver, intentFilter);
    }

    private void connectToWifiHotspot() {
        String networkSSID = "iptime5G";
        WifiConfiguration conf = new WifiConfiguration();
        conf.SSID = "\"" + networkSSID + "\"";
        conf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        wifiManager.addNetwork(conf);

        List<WifiConfiguration> list = wifiManager.getConfiguredNetworks();
        for( WifiConfiguration i : list ) {
            if(i.SSID != null && i.SSID.equals("\"" + networkSSID + "\"")) {
                wifiManager.disconnect();
                wifiManager.enableNetwork(i.networkId, true);
                wifiManager.reconnect();

                break;
            }
        }
    }

    private void turnOnWifi() {
        if(!isWifiEnabled()) {
            enableWifi();
        }
    }

    private void enableWifi() {
        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(true);
    }

    private boolean isWifiEnabled() {
        WifiManager wifi = (WifiManager)getSystemService(Context.WIFI_SERVICE);
        return wifi.isWifiEnabled();
    }
}
