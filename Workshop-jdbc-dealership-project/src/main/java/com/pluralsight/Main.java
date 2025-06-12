package com.pluralsight;

import com.pluralsight.utilities.DealershipMenu;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {


        DealershipMenu dealershipMenu = new DealershipMenu();
        dealershipMenu.displayDealershipMenu();


    }
}