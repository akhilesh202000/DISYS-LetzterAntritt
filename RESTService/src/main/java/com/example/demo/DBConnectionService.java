package com.example.demo;

import com.example.demo.dto.Letter;
import com.example.demo.dto.Package;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class DBConnectionService {

    //can't connect to DB solution: multiple postgres instances running in parallel
    private static String connectionString = "jdbc:postgresql://localhost:5432/psdb?user=psuser&password=pspw";

    public static Connection connect() {
        try {
            return DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void insertLetter(Connection conn, Letter letter) {
        try {
            String query = "INSERT INTO letters(name, country, uuid) VALUES (?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, letter.getName());
            ps.setString(2, letter.getCountry());
            ps.setObject(3, UUID.randomUUID());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertPackage(Connection conn, Package p) {
        try {
            String query = "INSERT INTO packages(name, weight, uuid) VALUES (?, ?, ?);";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, p.getName());
            ps.setFloat(2, p.getWeight());
            ps.setObject(3, UUID.randomUUID());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
