package com.example.krishanroy.notification_app_hw_roy_krishan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class SecondActivity extends AppCompatActivity {
    private ImageView mainImageView;
    private TextView mainTextView;
    private SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        String getImageFromMainActivity = sharedPreferences.getString(MainActivity.SHARED_PREF_IMAGE_KEY, "null");

        mainImageView = findViewById(R.id.flower_image_view);
        mainTextView =  findViewById(R.id.user_name_textview);
        mainTextView.setText("User Name : " + getImageFromMainActivity );
//TODO:
        }
}
