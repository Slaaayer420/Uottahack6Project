package com.example.uottahack6;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import android.widget.Button;
import android.widget.ImageView;



public class NotificationsActivity extends AppCompatActivity {
    private TextView numOfNotificationsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        double notifications = 2;

        numOfNotificationsTextView = findViewById(R.id.numOfNotificationsTextView);
        // Update the TextView to show the number of notifications
        String numberOfNotifications = "You have " + notifications + " notifications";
        numOfNotificationsTextView.setText(numberOfNotifications);

        findViewById(R.id.Home_Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotificationsActivity.this, HomePageActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.buttonNots).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double notifications = 0;

                numOfNotificationsTextView = findViewById(R.id.numOfNotificationsTextView);
                // Update the TextView to show the number of notifications
                String numberOfNotifications = "You have " + notifications + " notifications";
                numOfNotificationsTextView.setText(numberOfNotifications);
            }
        });
    }
}
