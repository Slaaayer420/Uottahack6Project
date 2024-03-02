package com.example.uottahack6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 1300; // Splash screen duration in milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Make sure this is the name of your splash screen layout file

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Create an Intent that will start the next Activity.
                Intent mainIntent = new Intent(MainActivity.this, HomePageActivity.class);
                MainActivity.this.startActivity(mainIntent);
                MainActivity.this.finish(); // Close the splash screen activity
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}