package com.pluralsight.utilities;

import com.pluralsight.dao.VehicleDAO;
import com.pluralsight.models.Vehicle;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class DealershipMenu {
    VehicleDAO vehicleDAO = new VehicleDAO();
    public static Scanner read = new Scanner(System.in);

    public DealershipMenu() throws SQLException {
    }

    public void displayDealershipMenu() throws SQLException {

        System.out.println("\n☁️☁️☁️ === 🚗 Car Dealership Menu 🚗 === ☁️☁️☁️");
        System.out.println("1️⃣  Search by Price 💰");
        System.out.println("2️⃣  Search by Make & Model 🏷️🚙");
        System.out.println("3️⃣  Search by Year 📅");
        System.out.println("4️⃣  Search by Color 🎨");
        System.out.println("5️⃣  Search by Mileage ⏱️");
        System.out.println("6️⃣  Search by Vehicle Type 🚐");
        System.out.println("7️⃣  View All Vehicles 🚘");
        System.out.println("8️⃣  Add a Vehicle ➕🚗");
        System.out.println("9️⃣  Remove a Vehicle ➖🚗");
        System.out.println("🔟  Sell a Vehicle 💵🚗");
        System.out.println("⓫  Lease a Vehicle 📄🔑");
        System.out.println("0️⃣  Exit ❌");
        System.out.print("Please select an option: \n");


        int choice = read.nextInt();
        switch (choice) {
            case 1 -> searchByPrice();
            case 2 -> searchByMakeModel();
            case 3 -> searchByYear();
            case 4 -> searchByColor();
            case 5 -> searchByMileage();
            case 6 -> searchByVehicleType();
            case 0 -> System.exit(0);
            default -> System.out.println("Invalid choice.");
        }

    }

    public void searchByPrice() throws SQLException {
//Prompt user input:
        System.out.println("Enter the minimum price: ");
        double min = read.nextDouble();
        System.out.println("Enter the maximum price");
        double max = read.nextDouble();


            List<Vehicle> found = vehicleDAO.getByPriceRange(min, max);

        System.out.println("\n🚙==========================Vehicles within $" + min + " - $" + max + "==========================🚗");

        if (found.isEmpty()) {
                System.out.println("No vehicles found within that price range");
            } else {
                for (Vehicle v : found) {
                    System.out.println(v);
                }
            }
        }


    public void searchByMakeModel() throws SQLException {
        VehicleDAO vehicleDAO = new VehicleDAO();
        vehicleDAO.getByMakeModel();
    }

    public void searchByYear() throws SQLException {
        VehicleDAO vehicleDAO = new VehicleDAO();
        vehicleDAO.getByYear();
    }

    public void searchByColor() throws SQLException {
        VehicleDAO vehicleDAO = new VehicleDAO();
        vehicleDAO.getByColor();
    }

    public void searchByMileage() throws SQLException {
        VehicleDAO vehicleDAO = new VehicleDAO();
        vehicleDAO.getByMileage();
    }

    public void searchByVehicleType() throws SQLException {
        VehicleDAO vehicleDAO = new VehicleDAO();
        vehicleDAO.getByVehicleType();
    }
}

