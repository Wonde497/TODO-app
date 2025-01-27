package com.example.myapplication6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class Help extends AppCompatActivity {
 TextView tv,tvamharic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (loadTheme()) {
            setTheme(R.style.Theme_ThemeSwitcher_Dark);

        } else {
            setTheme(R.style.Base_Theme_MyApplication6);
        }
        setContentView(R.layout.activity_help);
        tv=findViewById(R.id.tv);
        tvamharic=findViewById(R.id.tvamharic);

    }
    @Override
    public void onBackPressed(){
        Intent i=new Intent(getApplicationContext(),DashBoard.class);
        startActivity(i);
        finish();
    }
    private boolean loadTheme() {
        SharedPreferences preferences = getSharedPreferences("ThemeSwitcher", MODE_PRIVATE);
        return preferences.getBoolean("isDarkTheme", false);
    }

}