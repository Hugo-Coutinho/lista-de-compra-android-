package com.example.administradorlocal.listadecompras.feature.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administradorlocal.listadecompras.R;
import com.example.administradorlocal.listadecompras.feature.main.shopList;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer();
    }

    public void timer() {
        new Timer().schedule(new TimerTask() {
            public void run() {
                MainActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        startActivity(new Intent(MainActivity.this, shopList.class));
                    }
                });
            }
        }, 3000);
    }
}
