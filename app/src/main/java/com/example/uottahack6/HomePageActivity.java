package com.example.uottahack6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HomePageActivity extends AppCompatActivity {

    private Vehicle vehicle;
    private TextView kmRemainingTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        float batteryPercentage;
        batteryPercentage=80;

        // Assuming you want to dynamically set or update the battery percentage
        BatteryCircleView batteryCircleView = findViewById(R.id.batteryCircleView);
        batteryCircleView.setBatteryPercentage(batteryPercentage); // No need if it's already set to 80% by default

        // Initialize your Vehicle instance (example values)
        vehicle = new Vehicle(batteryPercentage, 0.2); // 80% battery, 0.2 kWh/km consumption

        // Example total kilometers that can be traveled on a full charge is 500 km
        double totalKms = 500;
        double remainingKms = vehicle.calculateRemainingKms(totalKms);

        kmRemainingTextView = findViewById(R.id.kmRemainingTextView);
        // Update the TextView to show the remaining kilometers
        String remainingKmsText = "You have " + remainingKms + " km of ride remaining";
        kmRemainingTextView.setText(remainingKmsText);

        findViewById(R.id.Setting_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.Notifications_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this, NotificationsActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        loadVehicleSettings(); // Call this method to update UI every time the HomePageActivity resumes
    }

    private void loadVehicleSettings() {
        SharedPreferences sharedPref = getSharedPreferences("VehiclePreferences", Context.MODE_PRIVATE);
        float batteryPercentage = sharedPref.getFloat("BatteryPercentage", 80); // Default to 80%
        float consumptionRate = sharedPref.getFloat("ConsumptionRate", 0.2f); // Default to 0.2

        vehicle.setBatteryPercentage(batteryPercentage);
        vehicle.setConsumptionKWhPerKm(consumptionRate);

        // Assuming 500 km is the maximum distance on a full charge
        double totalKms = 500;
        double remainingKms = vehicle.calculateRemainingKms(totalKms);

        // Update the battery circle view
        BatteryCircleView batteryCircleView = findViewById(R.id.batteryCircleView);
        batteryCircleView.setBatteryPercentage((float) batteryPercentage);

        // Update the TextView for remaining kilometers
        String remainingKmsText = "You have " + String.format("%.2f", remainingKms) + " km of ride remaining";
        kmRemainingTextView.setText(remainingKmsText);
    }
}