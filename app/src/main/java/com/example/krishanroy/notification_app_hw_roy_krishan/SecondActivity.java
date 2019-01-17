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
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class SecondActivity extends AppCompatActivity {
    private ImageView mainImageView;
    private TextView mainTextView;
    private Button notifyButton;
    private SharedPreferences sharedPreferences;
    public static final String CHANNEL_1_ID = "channel1";
    private NotificationManager notifyManager;
    private static final int NOTIFICATION_ID = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //String getImageFromMainActivity = sharedPreferences.getString(MainActivity.SHARED_PREF_IMAGE_KEY, "null");

        mainImageView = findViewById(R.id.flower_image_view);
        mainTextView = findViewById(R.id.user_name_textview);
        //mainTextView.setText("User Name : " + getImageFromMainActivity );
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
        notifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());

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
            notificationChannel.setDescription("Go back to the Main Page");
            notifyManager.createNotificationChannel(notificationChannel);

        }

    }
    private NotificationCompat.Builder getNotificationBuilder(){
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity(this,
                NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setContentTitle("You've been notified!")
                .setContentText("Click here to go back to the Main Page")
                .setSmallIcon(R.drawable.ic_android)
                .setContentIntent(notificationPendingIntent)
                .setAutoCancel(true);
        return notifyBuilder;
    }
}
