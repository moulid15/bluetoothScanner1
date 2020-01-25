package com.example.finalscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private int BLUETOOTH_ON = 1;
    private static final String TAG = "MainActivity";
    private BluetoothAdapter ad = BluetoothAdapter.getDefaultAdapter();
    private ArrayList<BluetoothDevice> arr;
    private ArrayAdapter<String> arrAD;
    private ListView listV;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listV = findViewById(R.id.listView);
        button = findViewById(R.id.scan);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    ad.startDiscovery();
                    IntentFilter F2 = new IntentFilter(BluetoothDevice.ACTION_FOUND);
                    registerReceiver(b1,F2);
            }
        });

    }
    private BroadcastReceiver b1 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String in = intent.getAction();
            Log.i(TAG, "onReceive: yesssssssss");
            if(BluetoothDevice.ACTION_FOUND.equals(in)){
                BluetoothDevice b2 = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                arrAD.add(b2.getName());
                listV.setAdapter(arrAD);

            }
        }
    };
}
