package com.example.krishanroy.notification_app_hw_roy_krishan.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.krishanroy.notification_app_hw_roy_krishan.R;
import com.example.krishanroy.notification_app_hw_roy_krishan.SecondActivity;
import com.example.krishanroy.notification_app_hw_roy_krishan.model.Hit;
import com.squareup.picasso.Picasso;

public class ImageViewHolder extends RecyclerView.ViewHolder {
    private ImageView mainImageView;
    private TextView mainTextView;
    private Intent intent;
    public SharedPreferences sharedPreferences;
    public static final String SHARED_PREF_KEY = "import com.squareup.picasso.Picasso";
    public static final String SHARED_PREF_IMAGE_KEY = "image key";
    public static final String SHARED_PREF_USER_NAME_KEY = "user name key";
    public ImageViewHolder(@NonNull View itemView) {
        super(itemView);
        mainImageView = itemView.findViewById(R.id.flower_image_view);
        mainTextView = itemView.findViewById(R.id.user_name_textview);
        sharedPreferences = itemView.getContext().getApplicationContext().getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE);

    }

    public void onBind(final Hit hits) {
        mainTextView.setText("User Name : " + hits.getUser());
        Picasso.get()
                .load(hits.getLargeImageURL())
                .into(mainImageView);
        //TODO: if (sharedPreferences != null then create a new retrofit call inside onBind.
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(itemView.getContext(), SecondActivity.class);
                Bundle bundle = new Bundle();
                sharedPreferences.edit()
                        .putString(SHARED_PREF_IMAGE_KEY,hits.getLargeImageURL())
                        .putString(SHARED_PREF_USER_NAME_KEY,hits.getUser())
                        .apply();
                itemView.getContext().startActivity(intent);
            }
        });


    }
}
