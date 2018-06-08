package com;

import javax.jws.soap.SOAPBinding;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:./test");
        Statement stmt = conn.createStatement();

        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users (" +
                "id INT(11) PRIMARY KEY AUTO_INCREMENT," +
                "username VARCHAR(50)," +
                "password VARCHAR(50)" +
                ");");

        stmt.executeUpdate("TRUNCATE TABLE users");
        stmt.executeUpdate("INSERT INTO users (username, password) VALUES ('bob', 'pass1')");
        stmt.executeUpdate("INSERT INTO users (username, password) VALUES ('mike', 'pass2')");
        stmt.executeUpdate("INSERT INTO users (username, password) VALUES ('john', 'pass3')");
        ResultSet rs = stmt.executeQuery("SELECT * FROM users");

        List<User> users = new ArrayList<>();
        while (rs.next()){
            User user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
            users.add(user);
        }

        System.out.println(users);
        rs.close();
        stmt.close();
        conn.close();
    }
}
