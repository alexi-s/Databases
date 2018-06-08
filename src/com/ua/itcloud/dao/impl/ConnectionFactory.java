package com.ua.itcloud.dao.impl;

import java.sql.*;

/**
 * Created by student on 08.06.2018.
 */
public class ConnectionFactory {

    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:./main";

    private static ConnectionFactory instance;

    private ConnectionFactory() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public void closeConnection (Connection connection) {
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void closePreparedStatement (PreparedStatement preparedStatement) {
        if (preparedStatement != null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeStatement (Statement statement) {
        if (statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public synchronized static ConnectionFactory getInstance() {
        if(instance == null){
            instance = new ConnectionFactory();
        }
        return instance;
    }
}
