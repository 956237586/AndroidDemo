package com.example.administrator.balldemo;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;


public class MainActivity2Activity extends Activity implements SensorEventListener {
    private Ball ball;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ball = new Ball(this, 50);
        //setContentView(ball);
        setContentView(R.layout.activity_main);
        ball = (Ball) findViewById(R.id.thisIsAId);
        ball.setRadius(50);
        SensorManager manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        manager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        ball.setDx(-2 * sensorEvent.values[0]);
        ball.setDy(2 * sensorEvent.values[1]);
        ball.move();

        //System.out.println("x = " + sensorEvent.values[0]);
        // System.out.println("y = " + sensorEvent.values[1]);
        // System.out.println("z = " + sensorEvent.values[2]);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
