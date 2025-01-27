package com.example.myapplication6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class NavigationDrawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public DrawerLayout drawerLayout;
    NavigationView navigation;

    public ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (loadTheme()) {
            setTheme(R.style.Theme_ThemeSwitcher_Dark);

        } else {
            setTheme(R.style.Base_Theme_MyApplication6);
        }
        setContentView(R.layout.activity_navigation_drawer);


        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        drawerLayout = findViewById(R.id.my_drawer_layout);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        // to make the Navigation drawer icon always appear on the action bar

       navigation = findViewById(R.id.navigation);

    navigation.setNavigationItemSelectedListener( this);




        // navigation=findViewById(R.id.navigation);


    }

    // override the onOptionsItemSelected()
    // function to implement
    // the item click listener callback
    // to open and close the navigation
    // drawer when the icon is clicked

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add_task) {
            Intent i = new Intent(getApplicationContext(), Task.class);
            startActivity(i);
            finish();
        } else if (id == R.id.dash_board) {
            Intent i = new Intent(getApplicationContext(), DashBoard.class);
            startActivity(i);
            finish();

        }else if (id == R.id.view_user) {
            Intent i = new Intent(getApplicationContext(), ViewUser.class);
            startActivity(i);
            finish();
        } else if (id==R.id.exit) {
            finish();
            moveTaskToBack(true);


        } else if (id==R.id.nav_logout) {
            Intent i=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
            finish();

        } else if (id==R.id.nav_settings) {
           Intent i= new Intent(getApplicationContext(),Settings.class);
           startActivity(i);
           finish();

        }
        return false;


    }
    @Override
    public void onBackPressed(){
        Intent i=new Intent(getApplicationContext(),Registration.class);
        startActivity(i);
    }
    private boolean loadTheme() {
        SharedPreferences preferences = getSharedPreferences("ThemeSwitcher", MODE_PRIVATE);
        return preferences.getBoolean("isDarkTheme", false);
    }

}
