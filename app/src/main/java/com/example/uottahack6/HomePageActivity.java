package com.example.uottahack6;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
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
    }
}