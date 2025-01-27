package com.example.myapplication6;

import static android.app.Activity.RESULT_OK;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.List;

public class UserAdapter extends ArrayAdapter<UserModel> {
    private static final int PICK_IMAGE_REQUEST = 1001;
    ImageButton imageButton;
    //int SELECT_PICTURE = 200;
    public UserAdapter(Context context, @NonNull List<UserModel> arrayList) {
        super( context, 0, arrayList);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {




        // of the recyclable view is null then inflate the custom layout for the same
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

        }
        UserModel currentPosition = getItem(position);

        // then according to the position of the view assign the desired image for the same
        TextView crsename = convertView.findViewById(R.id.email);
        assert currentPosition != null;
        crsename.setText(currentPosition.getEmail());

        // then according to the position of the view assign the desired TextView 1 for the same
        TextView crsduration = convertView.findViewById(R.id.name);
        crsduration.setText(currentPosition.getName());
        TextView crstracks = convertView.findViewById(R.id.Phone);
        // then according to the position of the view assign the desired TextView 2 for the same
        crstracks.setText(currentPosition.getNumber());


        // then return the recyclable view
        return convertView;
    }


}
