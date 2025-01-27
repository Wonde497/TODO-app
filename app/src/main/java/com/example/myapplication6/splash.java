package com.example.myapplication6;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

public class splash extends AppCompatActivity {
    private  final String text="TODO List Application";
    private int currentIndex=0;
    TextView tV;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        tV=findViewById(R.id.tV);
        displayNxtLetter();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), ChooseLanguage.class));
                finish();
            }
        }, 2500);

    }
    private void displayNxtLetter(){
        if (currentIndex < text.length()) {
            char nextLetter =text.charAt(currentIndex);
            tV.append(String.valueOf(nextLetter));
            currentIndex++;

            int delayMillis = 40; // 40 milliseconds
            new Handler(Looper.getMainLooper()).postDelayed(this::displayNxtLetter, delayMillis);


        }
    }
}