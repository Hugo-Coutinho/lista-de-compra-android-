package com.example.administradorlocal.listadecompras.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.administradorlocal.listadecompras.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void entrar(View view) {
        Intent intent = new Intent(this, shopList.class);
        startActivity(intent);
    }
}
