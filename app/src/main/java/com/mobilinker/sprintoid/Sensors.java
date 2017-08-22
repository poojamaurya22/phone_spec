package com.mobilinker.sprintoid;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Environment;
import android.os.StatFs;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class Sensors extends Activity {

    float x, y, z;
    List list;
    SensorManager sm;

    SensorEventListener sel = new SensorEventListener(){
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;
            x=values[0];
            y=values[1];
            z=values[2];
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);

        String[] item={"Accelerometer", "Proximity", "Gyroscope", "Magnetometer", "Rotation Vector Sensor",
                        "Gravity Sensor", "Linear Acceleration Sensor"};

        //Accelerometer
        sm = (SensorManager)getSystemService(SENSOR_SERVICE);
        list = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if(list.size()>0){
            sm.registerListener(sel, (Sensor) list.get(0), SensorManager.SENSOR_DELAY_NORMAL);
        }
        String accx = Float.toString(x);
        String accy = Float.toString(y);
        String accz = Float.toString(z);

        //Gyroscope
        sm = (SensorManager)getSystemService(SENSOR_SERVICE);
        list = sm.getSensorList(Sensor.TYPE_GYROSCOPE);
        if(list.size()>0){
            sm.registerListener(sel, (Sensor) list.get(0), SensorManager.SENSOR_DELAY_NORMAL);
        }
        String gyx = Float.toString(x);
        String gyy = Float.toString(y);
        String gyz = Float.toString(z);

        //Gravity
        sm = (SensorManager)getSystemService(SENSOR_SERVICE);
        list = sm.getSensorList(Sensor.TYPE_GRAVITY);
        if(list.size()>0){
            sm.registerListener(sel, (Sensor) list.get(0), SensorManager.SENSOR_DELAY_NORMAL);
        }
        String grx = Float.toString(x);
        String gry = Float.toString(y);
        String grz = Float.toString(z);

        //Linear Acceleration
        sm = (SensorManager)getSystemService(SENSOR_SERVICE);
        list = sm.getSensorList(Sensor.TYPE_LINEAR_ACCELERATION);
        if(list.size()>0){
            sm.registerListener(sel, (Sensor) list.get(0), SensorManager.SENSOR_DELAY_NORMAL);
        }
        String lax = Float.toString(x);
        String lay = Float.toString(y);
        String laz = Float.toString(z);

        //Proximity
        sm = (SensorManager)getSystemService(SENSOR_SERVICE);
        list = sm.getSensorList(Sensor.TYPE_PROXIMITY);
        if(list.size()>0){
            sm.registerListener(sel, (Sensor) list.get(0), SensorManager.SENSOR_DELAY_NORMAL);
        }
        String px = Float.toString(x);

        //Rotation Vector
        sm = (SensorManager)getSystemService(SENSOR_SERVICE);
        list = sm.getSensorList(Sensor.TYPE_ROTATION_VECTOR);
        if(list.size()>0){
            sm.registerListener(sel, (Sensor) list.get(0), SensorManager.SENSOR_DELAY_NORMAL);
        }
        String rvx = Float.toString(x);
        String rvy = Float.toString(y);
        String rvz = Float.toString(z);

        //Magnetometer
        sm = (SensorManager)getSystemService(SENSOR_SERVICE);
        list = sm.getSensorList(Sensor.TYPE_MAGNETIC_FIELD);
        if(list.size()>0){
            sm.registerListener(sel, (Sensor) list.get(0), SensorManager.SENSOR_DELAY_NORMAL);
        }
        String mx = Float.toString(x);
        String my = Float.toString(y);
        String mz = Float.toString(z);

        String[] item_detail={"x : "+accx+"\ny : "+accy+"\nz : "+accz, px, "x : "+gyx+"\ny : "+gyy+"\nz : "+gyz,
                               "x : "+mx+"\ny : "+my+"\nz : "+mz, "x : "+rvx+"\ny : "+rvy+"\nz : "+rvz,
                                "x : "+grx+"\ny : "+gry+"\nz : "+grz, "x : "+lax+"\ny : "+lay+"\nz : "+laz};

        ListView lv=(ListView) findViewById(R.id.sensors);
        lv.setAdapter(new CustomAdapter(this, item, item_detail));

        TextView batt = (TextView) findViewById(R.id.batt);
        batt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Sensors.this, Battery.class);
                startActivity(i);
            }
        });

        TextView cam = (TextView) findViewById(R.id.cam);
        cam.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Sensors.this, Camera.class);
                startActivity(i);
            }
        });

        TextView dev = (TextView) findViewById(R.id.dev);
        dev.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Sensors.this, Device.class);
                startActivity(i);
            }
        });

        TextView mem = (TextView) findViewById(R.id.mem);
        mem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Sensors.this, Memory.class);
                startActivity(i);
            }
        });

        TextView sen = (TextView) findViewById(R.id.sen);
        sen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Sensors.this, Sensors.class);
                startActivity(i);
            }
        });

        TextView sys = (TextView) findViewById(R.id.sys);
        sys.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Sensors.this, System.class);
                startActivity(i);
            }
        });

        TextView ther = (TextView) findViewById(R.id.ther);
        ther.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Sensors.this, Thermal.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onStop() {
        if(list.size()>0){
            sm.unregisterListener(sel);
        }
        super.onStop();
    }
}
