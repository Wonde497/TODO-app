package com.example.myapplication6;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import org.w3c.dom.Text;

import java.util.Locale;

public class ChooseLanguage extends AppCompatActivity {
    Button getStarted;
    Spinner chooseLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Locale.setDefault(Locale.US);
        setContentView(R.layout.activity_choose_language);
        getStarted = findViewById(R.id.getStarted);
        chooseLanguage = findViewById(R.id.choiceSpinner);

        /*Intent intent = new Intent(getApplicationContext(),ChooseLanguage.class);
        startActivity(intent);
        overridePendingTransition(0,0);
        finish();*/

        chooseLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String selectedLanguage = chooseLanguage.getSelectedItem().toString();
                Log.d("choose","Choosen language is:"+selectedLanguage);
                Locale locale;

                switch (selectedLanguage) {
                    case "English":
                        locale = new Locale("en");
                        break;
                    case "አማርኛ":
                        locale = new Locale("am");
                        break;
                    case "Afan Oromo":
                        locale = new Locale("om");
                        break;
                    case "italian":
                        locale = new Locale("it");
                        break;
                    default:
                        locale = new Locale("en");
                        break;
                }
                setLocale(ChooseLanguage.this,locale.getLanguage());
                updateUI();


            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
               // Handle when nothing is selected
            }

        });


        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }

    private void updateUI() {
        getStarted.setText(R.string.get_Started);
    }

    public static void setLocale(Activity activity, String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Resources resources = activity.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }

}