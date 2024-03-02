package com.example.uottahack6;

public class Vehicle {
    private double batteryPercentage; // Battery percentage (0 to 100)
    private double consumptionKWhPerKm; // Energy consumption in kilowatt-hours per kilometer

    public Vehicle(double batteryPercentage, double consumptionKWhPerKm) {
        this.batteryPercentage = batteryPercentage;
        this.consumptionKWhPerKm = consumptionKWhPerKm;
    }

    // Getter for batteryPercentage
    public double getBatteryPercentage() {
        return batteryPercentage;
    }

    // Setter for batteryPercentage
    public void setBatteryPercentage(double batteryPercentage) {
        this.batteryPercentage = batteryPercentage;
    }

    // Getter for consumptionKWhPerKm
    public double getConsumptionKWhPerKm() {
        return consumptionKWhPerKm;
    }

    // Setter for consumptionKWhPerKm
    public void setConsumptionKWhPerKm(double consumptionKWhPerKm) {
        this.consumptionKWhPerKm = consumptionKWhPerKm;
    }
    public double calculateRemainingKms(double totalKms) {
        // Calculate remaining kilometers based on battery percentage and consumption
        double remainingKms = (batteryPercentage / 100.0) * totalKms;
        return remainingKms;
    }


}

