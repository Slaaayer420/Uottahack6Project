package com.example.uottahack6;

public class Vehicle {
    private double batteryPercentage; // Battery percentage (0 to 100)
    private double consumptionKWhPerKm; // Energy consumption in kilowatt-hours per kilometer
    private boolean isCharging; // Indicates if the vehicle is currently charging
    private final double chargingSpeedKmPerHour = 6.0; // Fixed charging speed (km per hour)
    private BatteryLevelChangeListener batteryLevelChangeListener;
    private double batteryCapacityKWh;
    private double chargingSpeedPercentPerHour = 20;

    // Interface for notifying changes in battery level
    public interface BatteryLevelChangeListener {
        void onBatteryLevelChanged(double newBatteryLevel);
    }

    public Vehicle(double batteryPercentage, double consumptionKWhPerKm) {
        this.batteryPercentage = batteryPercentage;
        this.consumptionKWhPerKm = consumptionKWhPerKm;
        this.isCharging = false; // Initially not charging
    }

    // Getters and setters
    public double getBatteryPercentage() {
        return batteryPercentage;
    }

    public void setBatteryPercentage(double batteryPercentage) {
        this.batteryPercentage = batteryPercentage;
        // Notify about the change
        notifyBatteryLevelChanged();
    }

    public double getConsumptionKWhPerKm() {
        return consumptionKWhPerKm;
    }

    public void setConsumptionKWhPerKm(double consumptionKWhPerKm) {
        this.consumptionKWhPerKm = consumptionKWhPerKm;
    }

    public boolean isCharging() {
        return isCharging;
    }

    public void setCharging(boolean charging) {
        isCharging = charging;
    }

    // Method for updating battery level based on charging
    public void updateBatteryLevel(double hoursCharging) {
        if (isCharging) {
            double addedPercentage = chargingSpeedKmPerHour * hoursCharging; // Simplified for example
            batteryPercentage += addedPercentage;
            if (batteryPercentage > 100) batteryPercentage = 100; // Cap at 100%
            notifyBatteryLevelChanged();
        }
    }

    // Method to convert kilometers to battery percentage (needs implementation)
    private double convertKmsToBatteryPercentage(double kms) {
        // Placeholder implementation
        // Calculate and return the battery percentage equivalent of the kms based on vehicle specs
        return kms;
    }

    // Method to set the listener for battery level changes
    public void setBatteryLevelChangeListener(BatteryLevelChangeListener listener) {
        this.batteryLevelChangeListener = listener;
    }

    // Helper method to notify the listener about a battery level change
    private void notifyBatteryLevelChanged() {
        if (batteryLevelChangeListener != null) {
            batteryLevelChangeListener.onBatteryLevelChanged(batteryPercentage);
        }
    }
    public double calculateRemainingKms(double totalKms) {
        // If you know the total range directly:
         // Example value, replace with actual total range if known

        // If you need to calculate total range from battery capacity and consumption rate:
        // double totalRangeKm = batteryCapacityKWh / consumptionKWhPerKm;

        return (batteryPercentage / 100.0) * totalKms;
    }
    public double calculateRemainingChargingTime() {
        return (100 - batteryPercentage) / chargingSpeedPercentPerHour;
    }
}