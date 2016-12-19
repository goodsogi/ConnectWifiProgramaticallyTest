package com.example.owner.connectwifiprogramaticallytest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.widget.Toast;

/**
 * Created by OWNER on 2016-10-28.
 */

public class WifiConnectReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
        if(info != null && info.isConnected()) {

            Toast.makeText(context, "와이파이 연결됨!", Toast.LENGTH_SHORT).show();

        }
    }
}
