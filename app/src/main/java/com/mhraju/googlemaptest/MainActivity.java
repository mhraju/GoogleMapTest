package com.mhraju.googlemaptest;

import android.Manifest;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.mock.MockPackageManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button startButton, reachButton, stopButton, mapButton;

    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;

    GPSTracker gps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission)
                    != MockPackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{mPermission},
                        REQUEST_CODE_PERMISSION);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        mapButton = (Button)findViewById(R.id.mapButton);

        startButton = (Button)findViewById(R.id.startButton);
        reachButton = (Button)findViewById(R.id.reachButton);
        stopButton = (Button)findViewById(R.id.stopButton);



        getStartButton();
        getReachButton();
        getStopButton();

        reachButton.setEnabled(false);
        stopButton.setEnabled(false);
    }


    public void onMap(View view) {

        Intent intent = new Intent(MainActivity.this, MapActivity.class);
        startActivity(intent);
    }




    public void  getStartButton() {

        startButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                gps = new GPSTracker(MainActivity.this);
                Calendar calendar = Calendar.getInstance();

                SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String startTime = mdformat.format(calendar.getTime());

                // check if GPS enabled
                if(gps.canGetLocation()){

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    Toast.makeText(getApplicationContext(), "Start Location - \nLat: "
                            + latitude + "\nLong: " + longitude + "\nCurrent Time : " + startTime, Toast.LENGTH_SHORT).show();
                }else{

                    gps.showSettingsAlert();
                }

                reachButton.setEnabled(true);
                stopButton.setEnabled(true);
                startButton.setEnabled(false);

            }
        });

    }


    public void getReachButton() {

        reachButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                gps = new GPSTracker(MainActivity.this);
                Calendar calendar = Calendar.getInstance();

                SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String reachTime = mdformat.format(calendar.getTime());

                // check if GPS enabled
                if(gps.canGetLocation()){

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    Toast.makeText(getApplicationContext(), "Reach Location - \nLat: "
                            + latitude + "\nLong: " + longitude + "\nCurrent Time : " + reachTime, Toast.LENGTH_SHORT).show();
                }else{

                    gps.showSettingsAlert();
                }

                stopButton.setEnabled(true);
                reachButton.setEnabled(false);

            }
        });

    }



    public void  getStopButton() {

        stopButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                gps = new GPSTracker(MainActivity.this);
                Calendar calendar = Calendar.getInstance();

                SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String stopTime = mdformat.format(calendar.getTime());

                // check if GPS enabled
                if(gps.canGetLocation()){

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    Toast.makeText(getApplicationContext(), "Stop Location - \nLat: "
                            + latitude + "\nLong: " + longitude + "\nCurrent Time : " + stopTime, Toast.LENGTH_SHORT).show();
                }else{

                    gps.showSettingsAlert();
                }


                startButton.setEnabled(true);
                reachButton.setEnabled(false);
                stopButton.setEnabled(false);

            }
        });

    }



        /*public void onStart(View view) {

            gps = new GPSTracker(MainActivity.this);
            Calendar calendar = Calendar.getInstance();

            SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String startTime = mdformat.format(calendar.getTime());

            // check if GPS enabled
            if(gps.canGetLocation()){

                double latitude = gps.getLatitude();
                double longitude = gps.getLongitude();

                Toast.makeText(getApplicationContext(), "Start Location - \nLat: "
                        + latitude + "\nLong: " + longitude + "\nCurrent Time : " + startTime, Toast.LENGTH_LONG).show();
            }else{

                gps.showSettingsAlert();
            }

            reachButton.setEnabled(true);
            stopButton.setEnabled(true);
            startButton.setEnabled(false);

        }


        public void onReach(View view) {

            gps = new GPSTracker(MainActivity.this);
            Calendar calendar = Calendar.getInstance();

            SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String reachTime = mdformat.format(calendar.getTime());

            // check if GPS enabled
            if(gps.canGetLocation()){

                double latitude = gps.getLatitude();
                double longitude = gps.getLongitude();

                Toast.makeText(getApplicationContext(), "Reach Location - \nLat: "
                        + latitude + "\nLong: " + longitude + "\nCurrent Time : " + reachTime, Toast.LENGTH_LONG).show();
            }else{

                gps.showSettingsAlert();
            }

            stopButton.setEnabled(true);
            reachButton.setEnabled(false);
        }


        public void onStop(View view) {

            gps = new GPSTracker(MainActivity.this);
            Calendar calendar = Calendar.getInstance();

            SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String stopTime = mdformat.format(calendar.getTime());

            // check if GPS enabled
            if(gps.canGetLocation()){

                double latitude = gps.getLatitude();
                double longitude = gps.getLongitude();

                Toast.makeText(getApplicationContext(), "Stop Location - \nLat: "
                        + latitude + "\nLong: " + longitude + "\nCurrent Time : " + stopTime, Toast.LENGTH_LONG).show();
            }else{

                gps.showSettingsAlert();
            }


            startButton.setEnabled(true);
            reachButton.setEnabled(false);
            stopButton.setEnabled(false);

        }



        package com.mhraju.googlemaptest;

import android.*;
import android.Manifest;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.mock.MockPackageManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button startButton, pauseButton, stopButton, mapButton;

    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    Calendar calendar = Calendar.getInstance();
    GPSTracker gps;
    SimpleDateFormat mdformat;
    String strDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission)
                    != MockPackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{mPermission},
                        REQUEST_CODE_PERMISSION);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        mapButton = (Button)findViewById(R.id.mapButton);

        startButton = (Button)findViewById(R.id.startButton);
        pauseButton = (Button)findViewById(R.id.pauseButton);
        stopButton = (Button)findViewById(R.id.stopButton);

        startButton.setOnClickListener(this);

    }


    public void onMap(View view) {

        Intent intent = new Intent(MainActivity.this, MapActivity.class);
        startActivity(intent);
    }



    @Override
    public void onClick(View view) {

        switch (view.getId()) {


            case R.id.startButton:

                gps = new GPSTracker(MainActivity.this);

                mdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String startTime = mdformat.format(calendar.getTime());

                if(gps.canGetLocation()){

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    Toast.makeText(getApplicationContext(), "Pause Location - \nLat: "
                            + latitude + "\nLong: " + longitude + "\nCurrent Time : " + startTime, Toast.LENGTH_LONG).show();
                }else{

                    gps.showSettingsAlert();
                }

                view.setEnabled(false);

                if(startButton.isEnabled()){

                    Log.d("disable","hvbhd");

                    pauseButton.setOnClickListener(this);

                }


                break;


            case R.id.pauseButton:

                    gps = new GPSTracker(MainActivity.this);

                    mdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String pauseTime = mdformat.format(calendar.getTime());

                    if(gps.canGetLocation()){

                        double latitude = gps.getLatitude();
                        double longitude = gps.getLongitude();

                        Toast.makeText(getApplicationContext(), "Pause Location - \nLat: "
                                + latitude + "\nLong: " + longitude + "\nCurrent Time : " + pauseTime, Toast.LENGTH_LONG).show();
                    }else{

                        gps.showSettingsAlert();
                    }

                    view.setEnabled(false);

                    if(startButton.isEnabled() && pauseButton.isEnabled()){

                        stopButton.setOnClickListener(this);

                    }

                break;


            case R.id.stopButton:

                    gps = new GPSTracker(MainActivity.this);

                    mdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String stopTime = mdformat.format(calendar.getTime());

                    // check if GPS enabled
                    if(gps.canGetLocation()){

                        double latitude = gps.getLatitude();
                        double longitude = gps.getLongitude();

                        Toast.makeText(getApplicationContext(), "Stop Location - \nLat: "
                                + latitude + "\nLong: " + longitude + "\nCurrent Time : " + stopTime, Toast.LENGTH_LONG).show();
                    }else{

                        gps.showSettingsAlert();
                    }

                break;

            default:
                break;
        }

    }


    /*Button btnStart, btnPause, btnStop;
    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission)
                    != MockPackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{mPermission},
                        REQUEST_CODE_PERMISSION);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        btnStart = (Button) findViewById(R.id.startButton);
        btnPause = (Button) findViewById(R.id.pauseButton);
        btnStop = (Button) findViewById(R.id.stopButton);

        btnPause.setOnClickListener(this);


        getButton();

        *//*btnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // create class object
                gps = new GPSTracker(MainActivity.this);

                Calendar calendar = Calendar.getInstance();

                *//**//*
                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = dateFormatter.parse(startTime);
                *//**//*

                SimpleDateFormat mdformat = new SimpleDateFormat("h:mm a");
                String strDate = mdformat.format(calendar.getTime());

                // check if GPS enabled
                if(gps.canGetLocation()){

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    Toast.makeText(getApplicationContext(), "Location - \nLat: "
                            + latitude + "\nLong: " + longitude + "\nCurrent Time : " + strDate, Toast.LENGTH_LONG).show();
                }else{

                    gps.showSettingsAlert();
                }

            }
        });*//*
    }



    public void  getButton() {

        btnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // create class object
                gps = new GPSTracker(MainActivity.this);

                Calendar calendar = Calendar.getInstance();

                SimpleDateFormat mdformat = new SimpleDateFormat("h:mm a");
                String strDate = mdformat.format(calendar.getTime());

                // check if GPS enabled
                if(gps.canGetLocation()){

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    Toast.makeText(getApplicationContext(), "Location - \nLat: "
                            + latitude + "\nLong: " + longitude + "\nCurrent Time : " + strDate, Toast.LENGTH_LONG).show();
                }else{

                    gps.showSettingsAlert();
                }

            }
        });

    }


    public void onClick(View view) {

        gps = new GPSTracker(MainActivity.this);

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat mdformat = new SimpleDateFormat("h:mm a");
        String strDate = mdformat.format(calendar.getTime());

        if(gps.canGetLocation()){

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

            Toast.makeText(getApplicationContext(), "Pause Location - \nLat: "
                    + latitude + "\nLong: " + longitude + "\nCurrent Time : " + strDate, Toast.LENGTH_LONG).show();
        }else{

            gps.showSettingsAlert();
        }
    }



    public void onStop(View view) {

        gps = new GPSTracker(MainActivity.this);

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat mdformat = new SimpleDateFormat("h:mm a");
        String strDate = mdformat.format(calendar.getTime());

        // check if GPS enabled
        if(gps.canGetLocation()){

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

            Toast.makeText(getApplicationContext(), "Stop Location - \nLat: "
                    + latitude + "\nLong: " + longitude + "\nCurrent Time : " + strDate, Toast.LENGTH_LONG).show();
        }else{

            gps.showSettingsAlert();
        }
    }*/
}






