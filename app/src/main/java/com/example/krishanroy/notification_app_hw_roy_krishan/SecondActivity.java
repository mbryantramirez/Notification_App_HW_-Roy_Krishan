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
    private String getTextFromMainActivity;
    private SharedPreferences sharedPreferences;
    public static final String CHANNEL_1_ID = "channel1";
    private NotificationManager notifyManager;
    private static final int NOTIFICATION_ID = 0;
    /**
     * remove unused variables
     */
    public static final String
        SHARED_PREF_NOTIFY_KEY = "Notification key";
    private String notificationTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        /**
         * This intent is never used
         */
        Intent intent = getIntent();
        sharedPreferences = getSharedPreferences(ImageViewHolder.SHARED_PREF_KEY, MODE_PRIVATE);
        getTextFromMainActivity = sharedPreferences.getString(ImageViewHolder.SHARED_PREF_NAME_KEY, "");
        String getImageUrlFromMainActivity = sharedPreferences.getString(ImageViewHolder.SHARED_PREF_IMAGE_KEY, "");
        notificationTitle = "Thanks for clicking " + getTextFromMainActivity + "'s picture";
        mainImageView = findViewById(R.id.second_activity_imageview);
        mainTextView = findViewById(R.id.second_activity_textView);
        /**
         * Avoid concatenating when setting text in a textview
         */
        mainTextView.setText("Captured By : " + getTextFromMainActivity);
        Picasso.get().load(getImageUrlFromMainActivity).into(mainImageView);

        notifyButton = findViewById(R.id.second_activity_button);
        notifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 *  This should be checking if true send a notification else send a toast right now youre doing both
                 */
                if (notificationAlreadySent(notificationTitle)) {
                    sendNotification();
                }
                Toast.makeText(getApplicationContext(), "A notification was sent previously, and won't be sent again", Toast.LENGTH_LONG).show();
            }
        });
        createNotificationChannel();
    }

    private void sendNotification() {
        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
        notifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());
    }

    private boolean notificationAlreadySent(String message) {
        return sharedPreferences.getBoolean(message, false);
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
                NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setContentTitle(notificationTitle)
                .setContentText("Click here to go back to the Main Page")
                .setSmallIcon(R.drawable.ic_android)
                .setContentIntent(notificationPendingIntent)
                .setAutoCancel(true);
        /**
         * both your default value and the value your putting here for the notificationTitle key in shared prefrences are false
         * The logic would work if your default was false and was set to true when the notification was built here
         */
        sharedPreferences.edit()
                .putBoolean(notificationTitle, false)
                .apply();

        return notifyBuilder;
    }
}
