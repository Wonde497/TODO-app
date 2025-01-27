package com.example.myapplication6;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.IOException;
import java.util.List;

public class ViewUser extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1001;
    Dbhelper dbhelper;
    ListView listView;



    // constant to compare
    // the activity result code
    //int SELECT_PICTURE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);
        listView=findViewById(R.id.list);


        dbhelper=new Dbhelper(this);
        List<UserModel> allCourses=dbhelper.getAllUsers();
        UserAdapter userAdapter=new UserAdapter(this,allCourses);
        listView.setAdapter(userAdapter);


    }

}