package com.example.myapplication6;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class Registration extends AppCompatActivity {
    private EditText name, editTextPhone, editTextEmail, editTextPassword,confirm_password;
    Dbhelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (loadTheme()) {
            setTheme(R.style.Theme_ThemeSwitcher_Dark);

        } else {
            setTheme(R.style.Base_Theme_MyApplication6);
        }
        setContentView(R.layout.activity_registration);

        //initializing edit text and button
        name = findViewById(R.id.name);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        confirm_password=findViewById(R.id.confirm_Password);
        Button buttonRegister = findViewById(R.id.buttonRegister);
        dbhelper = new Dbhelper(this);

        //on click listener for button
        buttonRegister.setOnClickListener(view -> {


            String Name = name.getText().toString();
            String phoneNo = editTextPhone.getText().toString();
            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();
            String confrm_password = confirm_password.getText().toString();
            if (Name.equals("") || phoneNo.equals("") || email.equals("") || password.equals("")||confrm_password.equals("")) {
                Toast.makeText(getApplicationContext(), "please complete all the fields", Toast.LENGTH_SHORT).show();

                 }else
            if (!isEmail(email)) {

                    Toast.makeText(getApplicationContext()," Please enter valid email",Toast.LENGTH_SHORT).show();
                }else
             if(password.length()<7){
                    Toast.makeText(getApplicationContext(),"Password should not be less than 7 characters",Toast.LENGTH_SHORT).show();
                                       }else
             if(!confrm_password.equals(password)){
                    Toast.makeText(getApplicationContext(),"confirm password is not correct",Toast.LENGTH_SHORT).show();

             }
            else {

                boolean checkEmail = dbhelper.checkEmail(email);
                boolean checkPassword = dbhelper.checkPassword(email);

                if (!checkEmail && !checkPassword) {
                    boolean res = dbhelper.insertUserData(Name, email, password, confrm_password, phoneNo);
                    if (res) {
                        Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(), NavigationDrawer.class);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Failed to register", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "User already exists.\n Please Login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);

                }
            }
//finish();

        });


    }
    public boolean isEmail(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    @Override
    public void onBackPressed(){
        Intent i=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
        finish();
    }
    private boolean loadTheme() {
        SharedPreferences preferences = getSharedPreferences("ThemeSwitcher", MODE_PRIVATE);
        return preferences.getBoolean("isDarkTheme", false);
    }

}
