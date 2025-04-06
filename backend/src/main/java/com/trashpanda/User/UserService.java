package com.trashpanda.User;

import com.trashpanda.DatabaseConfig;

import java.sql.*;

public class UserService {
    public boolean checkIfProfileExists(String username) {
        String sql = "SELECT COUNT(*) FROM profiles WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(DatabaseConfig.URL, DatabaseConfig.USER, DatabaseConfig.PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean verifyUsernameAndPassword(String username, String password) {
        String sql = "SELECT password FROM profiles WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(DatabaseConfig.URL, DatabaseConfig.USER, DatabaseConfig.PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                return storedPassword.equals(password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean createNewProfile(String username, String firstname, String lastname, String contact,
                                    String password, int radius, double latitude, double longitude) {
        String sql = "INSERT INTO profiles (username, firstname, lastname, longitude, latitude, password, radius, contact) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DatabaseConfig.URL, DatabaseConfig.USER, DatabaseConfig.PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, firstname);
            stmt.setString(3, lastname);
            stmt.setDouble(4, longitude);
            stmt.setDouble(5, latitude);
            stmt.setString(6, password);
            stmt.setInt(7, radius);
            stmt.setString(8, contact);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    }
