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

//Making a query in Java
        String sql = "SELECT * FROM CT.vehicles WHERE price BETWEEN ? AND ?";
        List<Vehicle> results = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, min);
            stmt.setDouble(2, max);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    results.add(new Vehicle(
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
        String sql = "INSERT INTO CT.Vehicles (VIN, make, model, sold) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, vehicle.getVin());
            stmt.setString(2, vehicle.getMake());
            stmt.setString(3, vehicle.getModel());
            stmt.setString(4, vehicle.getStatus());
            stmt.executeUpdate();
        }
    }

    public void getByMakeModel() {
    }

    public void getByYear() {
    }

    public void getByColor() {
    }

    public void getByMileage() {
    }

    public void getByVehicleType() {
    }

//    public Vehicle getVehicleByVIN(String VIN) throws SQLException {
//        String sql = "SELECT * FROM CT.Vehicles WHERE VIN = ?";
//        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, VIN);
//            try (ResultSet rs = stmt.executeQuery()) {
//
//                if (rs.next()) {
//                    return new Vehicle(
//                            rs.getString("VIN"),
//                            rs.getString("make"),
//                            rs.getString("model"),
//                            rs.getBoolean("sold"));
//                }
//                return null;
//
//            }
//        }
//    }
}
