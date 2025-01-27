package com.example.myapplication6;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    Dbhelper dbhelper;



    private EditText editTextEmail, editTextPassword;
    public TextView textView,textView1;
    public CheckBox chkbx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (loadTheme()) {
            setTheme(R.style.Theme_ThemeSwitcher_Dark);

        } else {
            setTheme(R.style.Base_Theme_MyApplication6);
        }


        setContentView(R.layout.activity_main);
        dbhelper=new Dbhelper(this);

        // Initialize UI elements
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        textView=findViewById(R.id.textView);
        textView1=findViewById(R.id.textView1);
        chkbx=findViewById(R.id.chk_passwrd);
        Button buttonLogin = findViewById(R.id.buttonLogin);
        Button createAccount = findViewById(R.id.createAccount);
        chkbx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    //hide password
                    editTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    //show password
                    editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }

            }
        });

        // Set a click listener for the login button
        buttonLogin.setOnClickListener(view -> {
            // Retrieve entered username and password
            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();

            // Implement authentication logic here
            if (email.equals("") || password.equals("")) {
                Toast.makeText(getApplicationContext(),"please enter both fields",Toast.LENGTH_SHORT).show();
            }
            else {

                boolean checkEmail = dbhelper.checkEmail(email);
                boolean checkPassword = dbhelper.checkPassword(email);
                if (!checkEmail && !checkPassword) {
                    Toast.makeText(getApplicationContext(), "Incorrect email or password\n Please try again ! ", Toast.LENGTH_SHORT).show();

                }
                else{
                    Intent intent = new Intent(getApplicationContext(), NavigationDrawer.class);
                    startActivity(intent);
                }
            }
            //finish();
        });


        createAccount.setOnClickListener(view -> {
            // route to registration page
            Intent i=new Intent(getApplicationContext(),Registration.class);
            startActivity(i);
            finish();
        });



    }

    private boolean loadTheme() {
        SharedPreferences preferences = getSharedPreferences("ThemeSwitcher", MODE_PRIVATE);
        return preferences.getBoolean("isDarkTheme", false);
    }

}