package com.example.uottahack6;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private EditText batteryLevelInput;
    private EditText consumptionRateInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        batteryLevelInput = findViewById(R.id.batteryLevelInput);
        consumptionRateInput = findViewById(R.id.consumptionRateInput);

        findViewById(R.id.saveSettingsButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveVehicleSettings();
            }
        });
    }

    private void saveVehicleSettings() {
        float batteryLevel = Float.parseFloat(batteryLevelInput.getText().toString());
        float consumptionRate = Float.parseFloat(consumptionRateInput.getText().toString());

        SharedPreferences sharedPref = getSharedPreferences("VehiclePreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putFloat("BatteryPercentage", batteryLevel);
        editor.putFloat("ConsumptionRate", consumptionRate);
        editor.apply();

        finish(); // Close the activity and go back to the HomePageActivity
    }
}