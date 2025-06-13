package com.pluralsight.models;

import java.util.ArrayList;

public class Dealership {
        private int dealershipID;
        private String name;
        private String address;
        private String phone;
//        private ArrayList<Vehicle> inventory;

    public Dealership(int dealership_id, String name, String address, String phone) {
        this.dealershipID = dealership_id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public int getDealership_id() {
        return dealershipID;
    }

    public void setDealership_id(int dealership_id) {
        this.dealershipID = dealership_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format(
                "DealershipID: %-4s | Dealership Name: %-20 | Address: %-25 | Phone: %12 " ,
                dealershipID, name, address, phone);
    }
}
