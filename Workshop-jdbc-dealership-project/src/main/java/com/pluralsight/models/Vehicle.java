package com.pluralsight.models;

public class Vehicle {

    private String vin;
    private String make;
    private String model;
    private int year;
    private String color;
    private int odometer;
    private double price;
    private String vehicleType;

    private boolean sold = false;

    public Vehicle(String vin, String make, String model, int year, String color, int odometer, double price, String vehicleType, boolean isSold) {
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.odometer = odometer;
        this.price = price;
        this.vehicleType = vehicleType;
        this.sold = isSold;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public String getStatus() {
        return sold ? "Sold" : "Available";
    }

    @Override
    public String toString() {
        return String.format(
                "Status: %-9s | VIN: %-10s | Make: %-10s | Model: %-10s | Year: %-4d | Color: %-10s | Odometer: %,8d mi | Price: $%,10.2f | Type: %-10s",
                getStatus(), vin, make, model, year, color, odometer, price, vehicleType);
    }

}
