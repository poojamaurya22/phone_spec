package com.mobilinker.sprintoid;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Environment;
import android.os.StatFs;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class Memory extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);

        String[] item={"Emulated", "Removable","External Storage State","Total Memory", "Available Memory",
                        "Total External Memory", "Available External Memory", "RAM"};

        //External Memory
        StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
        long extAvailBytes = stat.getBlockSizeLong() * stat.getAvailableBlocksLong();
        long extTotalBytes = stat.getBlockSizeLong() * stat.getBlockCountLong();
        long extAvailMB = extAvailBytes / 1048576;
        long extTotalMB = extTotalBytes / 1048576;

        //Internal Memory
        stat = new StatFs(Environment.getRootDirectory().getPath());
        long intAvailBytes = stat.getBlockSizeLong() * stat.getAvailableBlocksLong();
        long intTotalBytes = stat.getBlockSizeLong() * stat.getBlockCountLong();
        long intAvailMB = intAvailBytes / 1048576;
        long intTotalMB = intTotalBytes / 1048576;

        //RAM
        ActivityManager actManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memInfo = new ActivityManager.MemoryInfo();
        actManager.getMemoryInfo(memInfo);
        long RAM = memInfo.totalMem / (1024 * 1024);

        String[] item_detail={Boolean.toString(Environment.isExternalStorageEmulated()),
                              Boolean.toString( Environment.isExternalStorageRemovable()), Environment.getExternalStorageState(),
                              Long.toString(intTotalMB), Long.toString(intAvailMB), Long.toString(extTotalMB),
                                Long.toString(extAvailMB), Long.toString(RAM)};

        ListView lv=(ListView) findViewById(R.id.memory);
        lv.setAdapter(new CustomAdapter(this, item, item_detail));

        TextView batt = (TextView) findViewById(R.id.batt);
        batt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Memory.this, Battery.class);
                startActivity(i);
            }
        });

        TextView cam = (TextView) findViewById(R.id.cam);
        cam.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Memory.this, Camera.class);
                startActivity(i);
            }
        });

        TextView dev = (TextView) findViewById(R.id.dev);
        dev.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Memory.this, Device.class);
                startActivity(i);
            }
        });

        TextView mem = (TextView) findViewById(R.id.mem);
        mem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Memory.this, Memory.class);
                startActivity(i);
            }
        });

        TextView sen = (TextView) findViewById(R.id.sen);
        sen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Memory.this, Sensors.class);
                startActivity(i);
            }
        });

        TextView sys = (TextView) findViewById(R.id.sys);
        sys.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Memory.this, System.class);
                startActivity(i);
            }
        });

        TextView ther = (TextView) findViewById(R.id.ther);
        ther.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Memory.this, Thermal.class);
                startActivity(i);
            }
        });

    }
}
