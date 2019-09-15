package com.example.dilshan.accelerometerproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private static final String TAG = "MainActivity";

    private SensorManager sensorManager;
    private Sensor mSensor;
    private Button changeButtonText;
    TextView xValue, yValue, zValue;

    @SuppressLint("ServiceCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("ACCELEROMETER SENSOR READER");
        setContentView(R.layout.activity_main);

        xValue = (TextView) findViewById(R.id.xValue);
        yValue = (TextView) findViewById(R.id.yValue);
        zValue = (TextView) findViewById(R.id.zValue);

        Log.d(TAG, "onCreate: Initializing Sensor Services");
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        mSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        changeButtonText = (Button)findViewById(R.id.button);
        Log.d(TAG,"butoon text is : "+ String.valueOf(changeButtonText.getText()));

        changeButtonText.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                if (changeButtonText.getText().equals("Start Readings")){
                    changeButtonText.setText("Stop");
                    changeButtonText.setBackgroundColor(Color.RED);
                    sensorManager.registerListener(MainActivity.this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);

                }

                else if (changeButtonText.getText().equals("Stop")){
                    changeButtonText.setText("Start");
                    sensorManager.unregisterListener(MainActivity.this);
                    changeButtonText.setBackgroundColor(Color.BLUE);

                }
                else if (changeButtonText.getText().equals("Start")){
                    changeButtonText.setText("Stop");
                    changeButtonText.setBackgroundColor(Color.RED);
                    sensorManager.registerListener(MainActivity.this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);

                }
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Log.d(TAG, "onSensorChanged: X: "+ sensorEvent.values[0] + "onSensorChanged: Y: "+ sensorEvent.values[1] +
                "onSensorChanged: Z: "+ sensorEvent.values[2]);
        xValue.setBackgroundColor(Color.GREEN);
        yValue.setBackgroundColor(Color.GREEN);
        zValue.setBackgroundColor(Color.GREEN);

        xValue.setText(" xValue :   " + sensorEvent.values[0]);
        yValue.setText(" yValue :   " + sensorEvent.values[1]);
        zValue.setText(" zValue :   " + sensorEvent.values[2]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
