package com.pluralsight.dao;

import com.pluralsight.dealership.Vehicle;

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
        String sql = "INSERT INTO vehicles (VIN, make, model, sold) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,vehicle.getVin());
        stmt.setString(2, vehicle.getMake());
        stmt.setString(3, vehicle.getModel());
        stmt.setString(4, vehicle.getStatus());
        stmt.executeUpdate();
    }

    public void getVehicleByVIN (String VIN, boolean isSold) throws SQLException {
        String sql = "SELECT * FROM CT.Vehicles WHERE VIN = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, VIN);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new Vehicle(rs.getString("VIN"), rs.getString("make"), rs.getString("model"), rs.getBoolean());
        }

    }
}
