package com.robson.wifi5ghz;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;



import com.robson.wifi5ghz.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        // Check if the device is connected to a 5GHz Wi-Fi network
        if (isConnectedTo5GHzWiFi()) {
            Log.d("test", "Device is connected to a 5GHz Wi-Fi network");
        } else {
            Log.d("test", "Device is not connected to a 5GHz Wi-Fi network");
        }

        // Check if the device is connected to a 60GHz Wi-Fi network
        if (isConnectedTo60GHzWiFi()) {
            Log.d("test", "Device is connected to a 60GHz Wi-Fi network");
        } else {
            Log.d("test", "Device is not connected to a 60GHz Wi-Fi network");
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private boolean isConnectedTo5GHzWiFi() {
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        if (wifiManager != null) {
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            int frequency = wifiInfo.getFrequency(); // in MHz
            Log.d("test", "Device frequency"+frequency);
            return frequency >= 5170 && frequency <= 5835; // 5GHz frequency range
        }

        return false;
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private boolean isConnectedTo60GHzWiFi() {
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        if (wifiManager != null) {
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            int frequency = wifiInfo.getFrequency(); // in MHz

            return frequency >= 56160 && frequency <= 56580; // 60GHz frequency range
        }

        return false;
    }


}