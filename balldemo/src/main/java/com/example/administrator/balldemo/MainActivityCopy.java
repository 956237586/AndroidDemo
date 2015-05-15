package com.example.administrator.balldemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * old version
 */
public class MainActivityCopy extends Activity {
    private BallView ball;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ball = new BallView(this, 50);
        ball.setDx(8);
        ball.setDy(8);
        ball.setLocation(200, 200);
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

        new Thread(new FreshBall()).start();
    }

    class FreshBall implements Runnable {

        @Override
        public void run() {
            while (true) {
                ball.move(true);
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
