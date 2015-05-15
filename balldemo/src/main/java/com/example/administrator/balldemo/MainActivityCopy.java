package com.example.administrator.balldemo;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * old version
 */
public class MainActivityCopy extends Activity implements SensorEventListener {
    private BallView ball;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ball = new BallView(this, 50);
        setContentView(ball);


        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    ball.invalidate();
                }
                super.handleMessage(msg);
            }
        };

        SensorManager sensorManager = (SensorManager)
                getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor
                (Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
        new Thread(new FreshBall()).start();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        ball.setDx(-2 * sensorEvent.values[0]);
        ball.setDy(2 * sensorEvent.values[1]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    class FreshBall implements Runnable {

        @Override
        public void run() {
            while (true) {
                ball.move(false);
                Message fresh = new Message();
                fresh.what = 1;
                handler.sendMessage(fresh);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
