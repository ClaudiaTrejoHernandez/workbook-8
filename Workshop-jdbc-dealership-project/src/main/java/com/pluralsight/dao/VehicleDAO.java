package com.pluralsight.dao;

import com.pluralsight.models.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleDAO {
    private Connection conn;

    public VehicleDAO(Connection conn) {
        this.conn = conn;
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

    public Vehicle getVehicleByVIN(String VIN) throws SQLException {
        String sql = "SELECT * FROM CT.Vehicles WHERE VIN = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, VIN);
            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return new Vehicle(
                            rs.getString("VIN"),
                            rs.getString("make"),
                            rs.getString("model"),
                            rs.getBoolean("sold"));
                }
                return null;

            }
        }
    }
}
