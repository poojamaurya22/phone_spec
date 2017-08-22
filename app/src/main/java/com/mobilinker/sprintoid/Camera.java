package com.mobilinker.sprintoid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.ImageFormat;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.support.annotation.Size;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


public class Camera extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);

        String[] item1 = {"Resolution","Flash","Settings"};

        CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        //Front Camera

        try {
            CameraCharacteristics cameraCharacteristics = manager.getCameraCharacteristics("0");

            android.util.Size[] jpeg = null;
            if (cameraCharacteristics != null) {
                jpeg = cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP).getOutputSizes(ImageFormat.JPEG);
            }
            int width = 0;
            int height = 0;
            if (jpeg != null && 0 < jpeg.length) {
                width = jpeg[0].getWidth();
                height = jpeg[0].getHeight();
            }
            int res = (width * height) / 1024000;

            int[] modes = cameraCharacteristics.get(CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES);
            float[] focal = cameraCharacteristics.get(CameraCharacteristics.LENS_INFO_AVAILABLE_APERTURES);

            String flash;
            boolean flashAvailable = cameraCharacteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
            if (flashAvailable) {
                flash = "Yes";
            } else {
                flash = "No";
            }

        String[] item_detail1 = {Integer.toString(res),flash,"Quality\n100%\nThumbnail Quality\n100%"};

        ListView lv1 = (ListView) findViewById(R.id.primary);
        lv1.setAdapter(new CustomAdapter(this, item1, item_detail1));

        //Back Camera

            String[] item2 = {"Resolution","Flash","Settings"};

        cameraCharacteristics = manager.getCameraCharacteristics("1");
        jpeg = null;
        if (cameraCharacteristics != null) {
            jpeg = cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP).getOutputSizes(ImageFormat.JPEG);
        }

        if (jpeg != null && 0 < jpeg.length) {
            width = jpeg[0].getWidth();
            height = jpeg[0].getHeight();
        }
        res = (width * height) / 1024000;

        modes = cameraCharacteristics.get(CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES);
        focal = cameraCharacteristics.get(CameraCharacteristics.LENS_INFO_AVAILABLE_APERTURES);

        flashAvailable = cameraCharacteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
        if (flashAvailable) {
            flash = "Yes";
        } else {
            flash = "No";
        }

        String[] item_detail2 = {Integer.toString(res),flash,"Quality\n100%\nThumbnail Quality\n100%"};

        ListView lv2 = (ListView) findViewById(R.id.secondary);
        lv2.setAdapter(new CustomAdapter(this, item2, item_detail2));

        }
        catch (CameraAccessException e){

        }


        TextView batt = (TextView) findViewById(R.id.batt);
        batt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Camera.this, Battery.class);
                startActivity(i);
            }
        });

        TextView cam = (TextView) findViewById(R.id.cam);
        cam.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Camera.this, Camera.class);
                startActivity(i);
            }
        });

        TextView dev = (TextView) findViewById(R.id.dev);
        dev.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Camera.this, Device.class);
                startActivity(i);
            }
        });

        TextView mem = (TextView) findViewById(R.id.mem);
        mem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Camera.this, Memory.class);
                startActivity(i);
            }
        });

        TextView sen = (TextView) findViewById(R.id.sen);
        sen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Camera.this, Sensors.class);
                startActivity(i);
            }
        });

        TextView sys = (TextView) findViewById(R.id.sys);
        sys.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Camera.this, System.class);
                startActivity(i);
            }
        });

        TextView ther = (TextView) findViewById(R.id.ther);
        ther.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Camera.this, Thermal.class);
                startActivity(i);
            }
        });


    }
}
