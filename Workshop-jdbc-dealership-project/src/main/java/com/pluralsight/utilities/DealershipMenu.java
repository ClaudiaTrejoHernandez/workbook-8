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


        while (true) {
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
            read.nextLine();

            switch (choice) {
                case 1 -> searchByPrice();
                case 2 -> searchByMakeModel();
                case 3 -> searchByYear();
                case 4 -> searchByColor();
                case 5 -> searchByMileage();
                case 6 -> searchByVehicleType();
                case 7 -> viewAllVehicles();
                case 8 -> addVehicle();
                case 9 -> sellVehicle();
                case 10 -> leaseVehicle();
                case 0 -> System.exit(0);
                default -> System.out.println("Invalid choice.");
            }

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
//Prompt user input:
        System.out.println("Enter the Vehicle Make: ");
        String make = read.nextLine().trim();
        read.nextLine();
        System.out.println("Enter the Vehicle Model: ");
        String model = read.nextLine().trim();

        List<Vehicle> found = vehicleDAO.getByMakeModel(make, model,true);

        if (found.isEmpty()) {
            System.out.println("No vehicles found.");
        } else {
            System.out.println("\nVehicles found: ");
                for (Vehicle v : found) {
                    System.out.println(v);
                }
            }
    }

    public void searchByYear() throws SQLException {
//Prompt user input:
        System.out.println("Enter the Vehicle year: ");
        int year = read.nextInt();
        read.nextLine();

        List<Vehicle> found = vehicleDAO.getByYear(year);

        if (found.isEmpty()) {
            System.out.println("No vehicles found for that year");
        } else {
            System.out.println("\n🚙==========================Vehicles found for the year " + year + "==========================🚗");
            for (Vehicle v : found) {
                System.out.println(v);
            }
        }
    }


    public void searchByColor() throws SQLException {
        //Prompt user input:
        System.out.println("Enter the Vehicle color: ");
        String color = read.nextLine().trim();

        if (color.isEmpty()) {
            System.out.println("❌ Please enter a valid color.");
            return;
        }

        List<Vehicle> found = vehicleDAO.getByColor(color);

        if (found.isEmpty()) {
            System.out.println("No matching vehicles found");
        } else {
            System.out.println("\n🚙==========================Vehicles found for the color " + color + "==========================🚗");
            for (Vehicle v : found) {
                System.out.println(v);
            }
        }
    }

    public void searchByMileage() throws SQLException {
//Prompt user input:
        System.out.println("Enter the minimum mileage: ");
        int min = read.nextInt();
        System.out.println("Enter the maximum mileage");
        int max = read.nextInt();


        List<Vehicle> found = vehicleDAO.getByMileage(min, max);

        System.out.println("\n🚙==========================Vehicles within " + min + " - " + max + " miles==========================🚗");

        if (found.isEmpty()) {
            System.out.println("No vehicles found within that mileage range");
        } else {
            for (Vehicle v : found) {
                System.out.println(v);
            }
        }
    }

    public void searchByVehicleType() throws SQLException {
        //Prompt user input:
        System.out.println("Enter the Vehicle type: ");
        String vehicleType = read.nextLine();

        List<Vehicle> found = vehicleDAO.getByVehicleType(vehicleType);

        System.out.println("\n🚙==========================Vehicles found for the vehicle type " + vehicleType + "==========================🚗");

        if (found.isEmpty()) {
            System.out.println("No" + vehicleType +"s found.");
        } else {
            for (Vehicle v : found) {
                System.out.println(v);
            }
        }
    }

    private void viewAllVehicles() {
    }

    private void addVehicle() {

    }

    private void sellVehicle() {

    }

    private void leaseVehicle() {

    }
}

