package com.example.krishanroy.notification_app_hw_roy_krishan;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.krishanroy.notification_app_hw_roy_krishan.view.ImageViewHolder;
import com.squareup.picasso.Picasso;

public class SecondActivity extends AppCompatActivity {
    private ImageView mainImageView;
    private TextView mainTextView;
    private Button notifyButton;
    private SharedPreferences sharedPreferences;
    public static final String CHANNEL_1_ID = "channel1";
    private NotificationManager notifyManager;
    private static final int[] NOTIFICATION_ID = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        sharedPreferences = getSharedPreferences(ImageViewHolder.SHARED_PREF_KEY, MODE_PRIVATE);
        String getTextFromMainActivity = sharedPreferences.getString(ImageViewHolder.SHARED_PREF_NAME_KEY, "");
        String getImageUrlFromMainActivity = sharedPreferences.getString(ImageViewHolder.SHARED_PREF_IMAGE_KEY, "");

        mainImageView = findViewById(R.id.second_activity_imageview);
        mainTextView = findViewById(R.id.second_activity_textView);

        mainTextView.setText("Captured By : " + getTextFromMainActivity);
        Picasso.get().load(getImageUrlFromMainActivity).into(mainImageView);

        notifyButton = findViewById(R.id.second_activity_button);
        notifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification();
            }
        });
        createNotificationChannel();
    }

    private void sendNotification() {
        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
        notifyManager.notify(NOTIFICATION_ID[0], notifyBuilder.build());
    }

    private void createNotificationChannel() {
        notifyManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_1_ID,
                    "Activity Change Notification", NotificationManager
                    .IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Click here to Go back to the Main Page");
            notifyManager.createNotificationChannel(notificationChannel);
        }
    }

    private NotificationCompat.Builder getNotificationBuilder() {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity(this,
                NOTIFICATION_ID[0], notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setContentTitle("You've been notified!")
                .setContentText("Click here to go back to the Main Page")
                .setSmallIcon(R.drawable.ic_android)
                .setContentIntent(notificationPendingIntent)
                .setAutoCancel(true);
        return notifyBuilder;
    }
}
