package com.pluralsight.models;

import java.time.LocalDate;

public class SalesContract {
    private int orderID;
    private LocalDate orderDate;
    private String firstName;
    private String lastName;
    private String buyerPhone;
    private String buyerAddress;
    private int total;
    private String VIN;
    private int dealership_ID;

    public SalesContract(int orderID, LocalDate orderDate, String firstName, String lastName, String buyerPhone, String buyerAddress, int total, String VIN, int dealership_ID) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.buyerPhone = buyerPhone;
        this.buyerAddress = buyerAddress;
        this.total = total;
        this.VIN = VIN;
        this.dealership_ID = dealership_ID;
    }
}
