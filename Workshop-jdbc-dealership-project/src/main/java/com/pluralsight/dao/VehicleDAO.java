package com.pluralsight.dao;

import com.pluralsight.models.Vehicle;
import com.pluralsight.utilities.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class VehicleDAO {
    private final Connection conn;

    public VehicleDAO() throws SQLException {
        this.conn = DatabaseConnection.getConnection();
    }

    public List<Vehicle> getByPriceRange (double min, double max) throws SQLException {

//Making the query:
        String sql = "SELECT * FROM CT.vehicles WHERE price BETWEEN ? AND ?";
        List<Vehicle> results = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, min);
            stmt.setDouble(2, max);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    results.add(new Vehicle(
                            rs.getInt("vehicle_id"),
                            rs.getString("VIN"),
                            rs.getString("make"),
                            rs.getString("model"),
                            rs.getInt("year"),
                            rs.getString("color"),
                            rs.getInt("odometer"),
                            rs.getDouble("price"),
                            rs.getString("vehicle_Type"),
                            rs.getBoolean("sold")
                    ));
                }
            }
        }
        return results;

    }


    public List<Vehicle> getByMakeModel(String make, String model, boolean matchEither) throws SQLException {

//Making the query:
        String joinWith = matchEither ? "OR" : "AND";
        String sql = "SELECT * FROM CT.vehicles WHERE make LIKE ?" + joinWith + " model LIKE ?";
        List<Vehicle> results = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + make + "%");
            stmt.setString(2, "%" + model + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    results.add(new Vehicle(
                            rs.getInt("vehicle_id"),
                            rs.getString("VIN"),
                            rs.getString("make"),
                            rs.getString("model"),
                            rs.getInt("year"),
                            rs.getString("color"),
                            rs.getInt("odometer"),
                            rs.getDouble("price"),
                            rs.getString("vehicle_Type"),
                            rs.getBoolean("sold")
                    ));

                }
            }
        }

        return results;
    }

    public List<Vehicle> getByYear(int year) throws SQLException {

        //Making the query:
        String sql = "SELECT * FROM CT.vehicles WHERE year = ?";
        List<Vehicle> results = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, year);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    results.add(new Vehicle(
                            rs.getInt("vehicle_id"),
                            rs.getString("VIN"),
                            rs.getString("make"),
                            rs.getString("model"),
                            rs.getInt("year"),
                            rs.getString("color"),
                            rs.getInt("odometer"),
                            rs.getDouble("price"),
                            rs.getString("vehicle_Type"),
                            rs.getBoolean("sold")
                    ));
                }
            }
        }
        return results;
    }

    public List<Vehicle> getByColor(String color) throws SQLException {

//Making the query:
        String sql = "SELECT * FROM CT.vehicles WHERE color LIKE ?";
        List<Vehicle> results = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + color + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    results.add(new Vehicle(
                            rs.getInt("vehicle_id"),
                            rs.getString("VIN"),
                            rs.getString("make"),
                            rs.getString("model"),
                            rs.getInt("year"),
                            rs.getString("color"),
                            rs.getInt("odometer"),
                            rs.getDouble("price"),
                            rs.getString("vehicle_Type"),
                            rs.getBoolean("sold")
                    ));
                }
            }

        }
        return results;
    }

    public List<Vehicle> getByMileage(int min, int max) throws SQLException {

        //Making the query:
        String sql = "SELECT * FROM CT.vehicles WHERE odometer BETWEEN ? AND ?";
        List<Vehicle> results = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, min);
            stmt.setDouble(2, max);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    results.add(new Vehicle(
                            rs.getInt("vehicle_id"),
                            rs.getString("VIN"),
                            rs.getString("make"),
                            rs.getString("model"),
                            rs.getInt("year"),
                            rs.getString("color"),
                            rs.getInt("odometer"),
                            rs.getDouble("price"),
                            rs.getString("vehicle_Type"),
                            rs.getBoolean("sold")
                    ));
                }
            }
        }
        return results;
    }

    public List<Vehicle> getByVehicleType(String vehicleType) throws SQLException {

//Making the query:
        String sql = "SELECT * FROM CT.vehicles WHERE vehicle_type LIKE ?";
        List<Vehicle> results = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + vehicleType  + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    results.add(new Vehicle(
                            rs.getInt("vehicle_id"),
                            rs.getString("VIN"),
                            rs.getString("make"),
                            rs.getString("model"),
                            rs.getInt("year"),
                            rs.getString("color"),
                            rs.getInt("odometer"),
                            rs.getDouble("price"),
                            rs.getString("vehicle_Type"),
                            rs.getBoolean("sold")
                    ));
                }
            }

        }
        return results;
    }


    public void saveVehicle(Vehicle vehicle) throws SQLException {
        vehicle.setSold(false);

        String sql = "INSERT INTO CT.vehicles (VIN, make, model, year, color, odometer, price, vehicle_type, sold) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, vehicle.getVin());
            stmt.setString(2, vehicle.getMake());
            stmt.setString(3, vehicle.getModel());
            stmt.setInt(4, vehicle.getYear());
            stmt.setString(5, vehicle.getColor());
            stmt.setInt(6, vehicle.getOdometer());
            stmt.setDouble(7, vehicle.getPrice());
            stmt.setString(8, vehicle.getVehicleType());
            stmt.setBoolean(9, vehicle.isSold());
            stmt.executeUpdate();
        }

    }

    public void deleteVehicle(int vehicle_id) throws SQLException {
        String sql = "DELETE FROM CT.vehicles WHERE vehicle_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, vehicle_id);
            stmt.executeUpdate();

        }

    }

}
