package com.example.uottahack6;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Switch;
import android.widget.TextView;

public class HomePageActivity extends AppCompatActivity {

    private Vehicle vehicle;
    private TextView kmRemainingTextView;
    private Handler handler = new Handler(); // Handler for periodic updates
    // Runnable task for simulating charging
    private Runnable runnableCode = new Runnable() {
        @Override
        public void run() {
            if (vehicle.isCharging()) {
                // Simulate 1 hour of charging
                vehicle.updateBatteryLevel(1/60); // Assuming 1 hour has passed
                // Repeat this runnable code again every hour
                handler.postDelayed(this, 60000); // 3600000 milliseconds = 1 hour
            }
        }

    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // Assuming you want to dynamically set or update the battery percentage
        BatteryCircleView batteryCircleView = findViewById(R.id.batteryCircleView);

        // Initialize your Vehicle instance (example values)
        vehicle = new Vehicle(80, 0.2); // 80% battery, 0.2 kWh/km consumption

        kmRemainingTextView = findViewById(R.id.kmRemainingTextView);

        findViewById(R.id.button5).setOnClickListener(v -> {
            Intent mainIntent = new Intent(HomePageActivity.this, NewTripActivity.class);
            HomePageActivity.this.startActivity(mainIntent);
            HomePageActivity.this.finish();
        });

        findViewById(R.id.Setting_button).setOnClickListener(v -> {
            Intent intent = new Intent(HomePageActivity.this, SettingsActivity.class);
            startActivity(intent);
        });

        // Initialize the charging switch setup
        setupCharging();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadVehicleSettings();// Call this method to update UI every time the HomePageActivity resumes
        updateUI();
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
        batteryCircleView.setBatteryPercentage(batteryPercentage);

        // Update the TextView for remaining kilometers
        String remainingKmsText = "You have " + String.format("%.2f", remainingKms) + " km of ride remaining";
        kmRemainingTextView.setText(remainingKmsText);
    }

    private void setupCharging() {
        Switch chargingSwitch = findViewById(R.id.chargingSwitch);
        BatteryCircleView batteryCircleView = findViewById(R.id.batteryCircleView);

        vehicle.setBatteryLevelChangeListener(newBatteryLevel -> runOnUiThread(() -> batteryCircleView.setBatteryPercentage((float) newBatteryLevel)));

        chargingSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            vehicle.setCharging(isChecked);
            if (isChecked) {
                // Start the handler when the vehicle starts charging
                handler.post(runnableCode);
            } else {
                // Stop the handler when the vehicle stops charging
                handler.removeCallbacks(runnableCode);
            }
            updateUI();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Remove callbacks to avoid memory leaks
        handler.removeCallbacks(runnableCode);
    }
    private void updateUI() {
        BatteryCircleView batteryCircleView = findViewById(R.id.batteryCircleView);
        Switch chargingSwitch = findViewById(R.id.chargingSwitch);

        if (chargingSwitch.isChecked()) {
            // Vehicle is charging, display remaining charging time
            double remainingTime = vehicle.calculateRemainingChargingTime();
            kmRemainingTextView.setText(String.format("Time to full charge: %.2f hours", remainingTime));
        } else {
            // Vehicle not charging, display remaining kilometers
            double totalKms = 500; // Example total range
            double remainingKms = vehicle.calculateRemainingKms(totalKms);
            kmRemainingTextView.setText(String.format("You have %.2f km of ride remaining", remainingKms));
        }

        // Update the battery circle view percentage
        batteryCircleView.setBatteryPercentage((float) vehicle.getBatteryPercentage());
    }


}