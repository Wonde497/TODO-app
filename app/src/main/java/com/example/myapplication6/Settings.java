package com.example.myapplication6;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;


public class Settings extends AppCompatActivity {
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch switch_theme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (loadTheme()) {
            setTheme(R.style.Theme_ThemeSwitcher_Dark);

        } else {
            setTheme(R.style.Base_Theme_MyApplication6);
        }

        setContentView(R.layout.activity_settings);
        switch_theme=findViewById(R.id.switch_theme);

        switch_theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("loadtttt",""+loadTheme());
                if (loadTheme()) {
                    setTheme(R.style.Base_Theme_MyApplication6);
                    saveTheme(false);
                } else {
                    setTheme(R.style.Theme_ThemeSwitcher_Dark);
                    saveTheme(true);

                }
                recreate();
            }
        });


    }
    




 private void saveTheme(boolean isDarkTheme) {
        SharedPreferences preferences = getSharedPreferences("ThemeSwitcher", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isDarkTheme", isDarkTheme);
        editor.apply();
    }
   private boolean loadTheme()
   {
        SharedPreferences preferences = getSharedPreferences("ThemeSwitcher", MODE_PRIVATE);
        return preferences.getBoolean("isDarkTheme", false);
    }

    @Override
    public void onBackPressed(){
        Intent i=new Intent(getApplicationContext(),NavigationDrawer.class);
        startActivity(i);
        finish();
    }

    }