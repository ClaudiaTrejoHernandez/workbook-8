package com.pluralsight.dealership;

public class Vehicle {

    private String vin;
    private String make;
    private String model;

    private boolean isSold = false;

    public Vehicle(String vin, String make, String model, boolean isSold) {
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.isSold = isSold;
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

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public boolean isSold() {
        return isSold;
    }

    public String getStatus() {
        return isSold ? "Sold" : "Available";
    }

    public String toString() {
        return String.format("Status: %s | VIN: %d | Make: %s | Model: %s | ",
                getStatus(), vin, make, model);    }



}
