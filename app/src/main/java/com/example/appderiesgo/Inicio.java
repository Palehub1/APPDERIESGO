package com.example.appderiesgo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;


public class Inicio extends AppCompatActivity {
        ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent (Inicio.this, InicioSesion.class);
                startActivity(i);
                finish();

            }
        },5000);



    }
}