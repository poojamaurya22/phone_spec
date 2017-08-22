package com.mobilinker.sprintoid;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.opengl.GLES10;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

public class System extends Activity {

    //CPU Utilization
    private float readUsage() {
        try {
            RandomAccessFile reader = new RandomAccessFile("/proc/stat", "r");
            String load = reader.readLine();

            String[] toks = load.split(" +");  // Split on one or more spaces

            long idle1 = Long.parseLong(toks[4]);
            long cpu1 = Long.parseLong(toks[2]) + Long.parseLong(toks[3]) + Long.parseLong(toks[5])
                    + Long.parseLong(toks[6]) + Long.parseLong(toks[7]) + Long.parseLong(toks[8]);

            try {
                Thread.sleep(360);
            } catch (Exception e) {}

            reader.seek(0);
            load = reader.readLine();
            reader.close();

            toks = load.split(" +");

            long idle2 = Long.parseLong(toks[4]);
            long cpu2 = Long.parseLong(toks[2]) + Long.parseLong(toks[3]) + Long.parseLong(toks[5])
                    + Long.parseLong(toks[6]) + Long.parseLong(toks[7]) + Long.parseLong(toks[8]);

            return (float)(cpu2 - cpu1) / ((cpu2 + idle2) - (cpu1 + idle1));

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system);

        String[] item1={"CPU Architecture", "Board", "Chipset", "Clock Speed", "Cores", "Instruction Sets", "Kernel Version"};

        String arch = java.lang.System.getProperty("os.arch");

        String core = Integer.toString(Runtime.getRuntime().availableProcessors());

        //int minFreq = SystemUtils.readSystemFileAsInt("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq");
        //int maxFreq = SystemUtils.readSystemFileAsInt("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq");

        String[] item_detail1={arch, Build.BOARD.toString(), Build.SUPPORTED_ABIS[0], " - ", core, Build.SUPPORTED_ABIS[1],
                            java.lang.System.getProperty("os.version")};

        ListView lv1=(ListView) findViewById(R.id.processor);
        lv1.setAdapter(new CustomAdapter(this, item1, item_detail1));

        String[] item2={"CPU Utilization", "Processes"};

        String usage = Float.toString(readUsage());

        Context context = getApplicationContext();

        //Processes
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.AppTask> tasks = activityManager.getAppTasks();

        String task = Integer.toString(tasks.size());

        String[] item_detail2={usage, task};

        ListView lv2=(ListView) findViewById(R.id.running);
        lv2.setAdapter(new CustomAdapter(this, item2, item_detail2));

        String[] item3={"Extensions", "Vendor", "OpenGL Version"};

        String[] item_detail3={GLES10.glGetString(GLES10.GL_EXTENSIONS), GLES10.glGetString(GLES10.GL_VENDOR),
                                GLES10.glGetString(GLES10.GL_VERSION)};

        ListView lv3=(ListView) findViewById(R.id.graphics);
        lv3.setAdapter(new CustomAdapter(this, item3, item_detail3));



        TextView batt = (TextView) findViewById(R.id.batt);
        batt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(System.this, Battery.class);
                startActivity(i);
            }
        });

        TextView cam = (TextView) findViewById(R.id.cam);
        cam.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(System.this, Camera.class);
                startActivity(i);
            }
        });

        TextView dev = (TextView) findViewById(R.id.dev);
        dev.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(System.this, Device.class);
                startActivity(i);
            }
        });

        TextView mem = (TextView) findViewById(R.id.mem);
        mem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(System.this, Memory.class);
                startActivity(i);
            }
        });

        TextView sen = (TextView) findViewById(R.id.sen);
        sen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(System.this, Sensors.class);
                startActivity(i);
            }
        });

        TextView sys = (TextView) findViewById(R.id.sys);
        sys.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(System.this, System.class);
                startActivity(i);
            }
        });

        TextView ther = (TextView) findViewById(R.id.ther);
        ther.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(System.this, Thermal.class);
                startActivity(i);
            }
        });
    }
}
