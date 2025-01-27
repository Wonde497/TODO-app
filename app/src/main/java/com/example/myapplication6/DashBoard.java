package com.example.myapplication6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.widget.Toast;

public class DashBoard extends AppCompatActivity {


    CardView cardHome;
    CardView cardHelp;

    CardView cardSettings;
    CardView cardLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (loadTheme()) {
            setTheme(R.style.Theme_ThemeSwitcher_Dark);

        } else {
            setTheme(R.style.Base_Theme_MyApplication6);
        }
        setContentView(R.layout.activity_dash_board);
        cardHome = findViewById(R.id.cardHome);
        cardHelp=findViewById(R.id.cardHelp);
       cardSettings = findViewById(R.id.cardSettings);
        cardLogout = findViewById(R.id.cardLogout);

        cardHome.setOnClickListener(v -> {
           Intent i= new Intent(getApplicationContext(),NavigationDrawer.class);
           startActivity(i);
           finish();
        });
        cardHelp.setOnClickListener(v -> {
           Intent i=new Intent(getApplicationContext(), Help.class);
           startActivity(i);
           finish();

        });
       cardSettings.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i=new Intent(getApplicationContext(),Settings.class);
               startActivity(i);
               finish();
           }
       });
        cardLogout.setOnClickListener(v -> {
            Intent i= new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
            finish();
        });

    }
    @Override
    public void onBackPressed(){
        Intent i=new Intent(getApplicationContext(),NavigationDrawer.class);
        startActivity(i);
        finish();
    }
    private boolean loadTheme() {
        SharedPreferences preferences = getSharedPreferences("ThemeSwitcher", MODE_PRIVATE);
        return preferences.getBoolean("isDarkTheme", false);
    }


}