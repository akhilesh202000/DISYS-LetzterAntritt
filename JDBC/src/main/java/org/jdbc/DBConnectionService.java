package org.jdbc;

import org.jdbc.dto.*;
import org.jdbc.dto.Package;

import java.sql.*;
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

    public static TableData getTableData(Connection conn) {
        TableData td = new TableData();

        try {
            ResultSet rsLetters = conn.prepareStatement("SELECT * FROM letters;").executeQuery();
            while(rsLetters.next()) {
                td.letters.add(new Letter(rsLetters.getString("name"), rsLetters.getString("country")));
            }
            ResultSet rsPackages = conn.prepareStatement("SELECT * FROM packages;").executeQuery();
            while(rsPackages.next()) {
                td.packages.add(new Package(rsPackages.getString("name"), rsPackages.getFloat("weight")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return td;
    }
}
