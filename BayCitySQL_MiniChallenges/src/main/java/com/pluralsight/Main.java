package com.pluralsight;

import java.sql.*;
import java.util.Scanner;


public class Main {
    private static final String DB_URL = "jdbc:sqlserver://skills4it.database.windows.net:1433;" +
            "database=Courses;" +
            "user=gtareader@skills4it;" +
            "password=StrongPass!2025;" +
            "encrypt=true;" +
            "trustServerCertificate=false;" +
            "loginTimeout=30;";


    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Bay City SQL Mini-Challenges ===");
            System.out.println("Challenge 1: The informant ");
            System.out.println("Challenge 2: Vehicle Registry ");
            System.out.println("Challenge 3: Citizen's Vehicle Fleet ");
            System.out.println("Challenge 4: Average Mission Payout ");
            System.out.println("Challenge 5: Agents Without Missions ");
            System.out.println("Challenge 6: Highest Earning Criminals ");
            System.out.println("❌ 0 to Exit");
            System.out.print("Select your option: ");

            int choice = inputScanner.nextInt();
            switch (choice) {
                case 1 -> runInformantCheck();
                case 2 -> runVehicleRegistry();
                case 3 -> runCitizenFleetReport();
                case 4 -> runAveragePayout();
                case 5 -> runInactiveAgentReport();
                case 6 -> runHighestEarningCriminals();
                case 0 -> System.exit(0);
                default -> System.out.println("Invalid choice.");


            }
        }
    }

    public static void runInformantCheck() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the alias of the citizen to look up: ");
        String alias = scanner.nextLine();

        String query = "SELECT Name, Alias, WantedLevel FROM GTA.Citizens WHERE Alias = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, alias);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("\n--- Citizen Profile ---");
                System.out.printf("Name: %s\nAlias: %s\nWanted Level: %d\n",
                        rs.getString("Name"),
                        rs.getString("Alias"),
                        rs.getInt("WantedLevel"));
            } else {
                System.out.println("\nNo citizen found with that alias.");
            }

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void runVehicleRegistry() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter vehicle brand to search for: ");
        String brand = scanner.nextLine();

        String query = "SELECT c.Name, v.Type, v.Brand " +
                "FROM GTA.Vehicles v " +
                "JOIN GTA.Citizens c ON v.OwnerID = c.CitizenID\n" +
                "WHERE v.Brand = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, brand);
            ResultSet rs = stmt.executeQuery();

            System.out.printf("\n--- Vehicles of brand: %s ---\n", brand);
            if (rs.next()) {
                System.out.printf("Owner: %-20s Type: %-15s Brand: %-15s\n",
                        rs.getString("Name"),
                        rs.getString("Type"),
                        rs.getString("Brand"));
            } else {
                System.out.println("\nNo vehicle of that brand found.");

            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());


        }
    }

    public static void runCitizenFleetReport() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter citizen's name to see their vehicle fleet: ");
        String name = scanner.nextLine();

        String query = "SELECT v.Type, v.Brand, v.IsStolen " +
                "FROM GTA.Vehicles v " +
                "JOIN GTA.Citizens c ON v.OwnerID = c.CitizenID\n" +
                "WHERE c.Name = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            System.out.printf("\n--- Vehicle Fleet for %s ---\n", name);

            boolean found = false;

            while (rs.next()) {
                found = true;
                System.out.printf("Type: %-20s Brand: %-15s Stolen: %b\n",
                        rs.getString("Type"),
                        rs.getString("Brand"),
                        rs.getBoolean("IsStolen"));
            }
            if (!found) {
                System.out.println("\nNo vehicle fleet found for that citizen");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void runAveragePayout() {
        String query = "SELECT AVG(Reward) AS AveragePayout " +
                "FROM GTA.Missions";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n--- Mission Payout Analysis ---");
            if (rs.next()) {
                System.out.printf("The average mission payout is: $%.2f\n",
                        rs.getDouble("AveragePayout"));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }


    public static void runInactiveAgentReport() {
        String query = "SELECT c.Name " +
                "FROM GTA.Citizens AS c " +
                "LEFT JOIN GTA.Assignments AS a ON a.CitizenID = c.CitizenID " +
                "LEFT JOIN GTA.Missions AS m ON a.MissionID = m.MissionID " +
                "WHERE a.CitizenID IS NULL";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n--- Agents With No Recorded Missions ---");
            while (rs.next()) {
                System.out.printf("Name: %s\n", rs.getString("Name"));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void runHighestEarningCriminals() {
        String query = "SELECT TOP 3 c.Name, " +
                "SUM(m.Reward) AS TotalEarnings " +
                "FROM GTA.Citizens c " +
                "LEFT JOIN GTA.Assignments AS a ON a.CitizenID = c.CitizenID " +
                "LEFT JOIN GTA.Missions AS m ON a.MissionID = m.MissionID " +
                "JOIN GTA.Vehicles v ON c.CitizenID = v.OwnerID " +
                "WHERE v.IsStolen = 1 " +
                    "AND m.Reward > 0" +
                "GROUP BY c.Name " +
                "ORDER BY TotalEarnings DESC";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n--- Top 3 Highest Earning Criminals (with stolen cars) ---");
            while (rs.next()) {
                System.out.printf("%-20s Total Earnings: $%.2f\n",
                        rs.getString("Name"),
                        rs.getDouble("TotalEarnings"));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}