package com.example.administrator.balldemo;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;


public class MainActivity extends Activity implements SensorEventListener {
    private BallView ball;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ball = new BallView(this, 50);
        setContentView(ball);
        SensorManager sensorManager = (SensorManager)
                getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor
                (Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        ball.move(false, -2 * sensorEvent.values[0], 2 * sensorEvent.values[1]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
