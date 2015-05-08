package com.example.administrator.logindemo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;


public class MainActivity extends Activity implements Runnable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        SharedPreferences s = getPreferences(MODE_PRIVATE);
        SharedPreferences s = getSharedPreferences("mysharep.xml", MODE_PRIVATE);
        if (s.getBoolean("isfirst", true)) {
            new android.os.Handler().postDelayed(this, 4000);
            SharedPreferences.Editor e = s.edit();
            e.putBoolean("isfirst", false);
            //e.commit();
            e.apply();
        } else {
            Toast.makeText(this, "This is not first", Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public void run() {
        startActivity(new Intent(this, FirstActivity.class));
        finish();
    }
}
