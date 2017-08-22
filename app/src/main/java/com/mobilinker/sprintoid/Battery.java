package com.mobilinker.sprintoid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class Battery extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);

        String[] item={"Present","Status","Plugged","Health","Level","Temperature","Voltage"};

        Context context=getApplicationContext();

        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, ifilter);

        String[] item_detail={Boolean.toString(batteryStatus.getBooleanExtra(BatteryManager.EXTRA_PRESENT, false)),
                Integer.toString(batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1)),
                Integer.toString(batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)),
                Integer.toString(batteryStatus.getIntExtra(BatteryManager.EXTRA_HEALTH, -1)),
                Integer.toString(batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)),
                Integer.toString(batteryStatus.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1))+" F",
                Integer.toString(batteryStatus.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1))+" mV"};

        ListView lv=(ListView) findViewById(R.id.battery);
        lv.setAdapter(new CustomAdapter(this, item, item_detail));

        TextView batt = (TextView) findViewById(R.id.batt);
        batt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Battery.this, Battery.class);
                startActivity(i);
            }
        });

        TextView cam = (TextView) findViewById(R.id.cam);
        cam.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Battery.this, Camera.class);
                startActivity(i);
            }
        });

        TextView dev = (TextView) findViewById(R.id.dev);
        dev.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Battery.this, Device.class);
                startActivity(i);
            }
        });

        TextView mem = (TextView) findViewById(R.id.mem);
        mem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Battery.this, Memory.class);
                startActivity(i);
            }
        });

        TextView sen = (TextView) findViewById(R.id.sen);
        sen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Battery.this, Sensors.class);
                startActivity(i);
            }
        });

        TextView sys = (TextView) findViewById(R.id.sys);
        sys.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Battery.this, System.class);
                startActivity(i);
            }
        });

        TextView ther = (TextView) findViewById(R.id.ther);
        ther.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Battery.this, Thermal.class);
                startActivity(i);
            }
        });




    }
}
