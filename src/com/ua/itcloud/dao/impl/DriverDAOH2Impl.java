package com.ua.itcloud.dao.impl;

import com.ua.itcloud.dao.DriverDAO;
import com.ua.itcloud.exception.DriverNotFoundException;
import com.ua.itcloud.exception.DuplicateDriverException;
import com.ua.itcloud.model.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.ua.itcloud.dao.impl.ConnectionFactory.getInstance;

/**
 * Created by student on 11.06.2018.
 */
public class DriverDAOH2Impl implements DriverDAO {

    private static final String CREATE_DRIVER_TABLE = "CREATE TABLE IF NOT EXISTS drivers (" +
            Driver.ID + " INT(11) PRIMARY KEY AUTO_INCREMENT," +
            Driver.FIRST_NAME + " VARCHAR(50)," +
            Driver.LAST_NAME + " VARCHAR(50) UNIQUE," +
            Driver.EXPERIENCE + " INT(4)," +
            Driver.CATEGORY + " VARCHAR(4)" +
            ");";
    private static final String INSERT_DRIVER = String.format("INSERT INTO drivers (%s, %s, %s, %s) VALUES (?, ?, ?, ?);", Driver.FIRST_NAME, Driver.LAST_NAME, Driver.EXPERIENCE, Driver.CATEGORY);
    private static final String DELETE_DRIVER_BY_ID = String.format("DELETE FROM drivers WHERE %s=?;", Driver.ID);
    private static final String GET_DRIVER_BY_ID = String.format("SELECT * FROM drivers WHERE %s=?;", Driver.ID);
    private static final String GET_DRIVER_BY_LAST_NAME = String.format("SELECT * FROM drivers WHERE %s=?;", Driver.LAST_NAME);
    private static final String UPDATE_DRIVER_BY_ID = String.format("UPDATE drivers SET %s=?, %s=?, %s=?, %s=? WHERE %s=?;",
            Driver.FIRST_NAME, Driver.LAST_NAME, Driver.EXPERIENCE, Driver.CATEGORY, Driver.ID);
    private static final String GET_ALL_DRIVERS = "SELECT * FROM drivers";

    private Connection connection;
    private PreparedStatement pst = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    public DriverDAOH2Impl() {
        createTableIfNotExists();
    }

    @Override
    public void addDriver(Driver driver) throws DuplicateDriverException {
        try {
            connection = getInstance().getConnection();
            pst = connection.prepareStatement(INSERT_DRIVER);

            pst.setString(1, driver.getFirstName());
            pst.setString(2, driver.getLastName());
            pst.setInt(3, driver.getExperience());
            pst.setString(4, driver.getCategory());

            pst.execute();

        } catch (SQLException e) {
            throw new DuplicateDriverException();
        } finally {
            getInstance().closePreparedStatement(pst);
            getInstance().closeConnection(connection);
        }
    }

    @Override
    public List<Driver> getAllDrivers() {
        List<Driver> result = new ArrayList<>();
        try {
            connection = getInstance().getConnection();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(GET_ALL_DRIVERS);

            while (rs.next()) {
                Driver driver = new Driver();
                driver.setId(rs.getInt(Driver.ID));
                driver.setFirstName(rs.getString(Driver.FIRST_NAME));
                driver.setLastName(rs.getString(Driver.LAST_NAME));
                driver.setExperience(rs.getInt(Driver.EXPERIENCE));
                driver.setCategory(rs.getString(Driver.CATEGORY));

                result.add(driver);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getInstance().closeResultSet(rs);
            getInstance().closeStatement(stmt);
            getInstance().closeConnection(connection);
        }
        return result;
    }

    @Override
    public Driver getDriverById(int driverId) throws DriverNotFoundException{
        Driver driver = new Driver();
        try {
            connection = getInstance().getConnection();
            pst = connection.prepareStatement(GET_DRIVER_BY_ID);
            pst.setInt(1, driverId);
            rs = pst.executeQuery();
            rs.next();
            driver.setId(rs.getInt(Driver.ID));
            driver.setFirstName(rs.getString(Driver.FIRST_NAME));
            driver.setLastName(rs.getString(Driver.LAST_NAME));
            driver.setExperience(rs.getInt(Driver.EXPERIENCE));
            driver.setCategory(rs.getString(Driver.CATEGORY));

        } catch (SQLException e) {
            throw new DriverNotFoundException();
        } finally {
            getInstance().closeResultSet(rs);
            getInstance().closeStatement(pst);
            getInstance().closeConnection(connection);
        }
        return driver;
    }

    @Override
    public Driver getDriverByLastName(String lastName) throws DriverNotFoundException {
        Driver driver = new Driver();
        try {
            connection = getInstance().getConnection();
            pst = connection.prepareStatement(GET_DRIVER_BY_LAST_NAME);
            pst.setString(1, lastName);
            rs = pst.executeQuery();

            rs.next();
            driver.setId(rs.getInt(Driver.ID));
            driver.setFirstName(rs.getString(Driver.FIRST_NAME));
            driver.setLastName(rs.getString(Driver.LAST_NAME));
            driver.setExperience(rs.getInt(Driver.EXPERIENCE));
            driver.setCategory(rs.getString(Driver.CATEGORY));

        } catch (SQLException e) {
            throw new DriverNotFoundException();
        } finally {
            getInstance().closeResultSet(rs);
            getInstance().closeStatement(pst);
            getInstance().closeConnection(connection);
        }
        return driver;
    }

    @Override
    public void updateDriver(Driver driver) {
        try {
            connection = getInstance().getConnection();
            pst = connection.prepareStatement(UPDATE_DRIVER_BY_ID);

            System.out.println(driver.getId());

            pst.setString(1, driver.getFirstName());
            pst.setString(2, driver.getLastName());
            pst.setInt(3, driver.getExperience());
            pst.setString(4, driver.getCategory());
            pst.setInt(5, driver.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDriver(int driverId) throws DriverNotFoundException {
        try {
            connection = getInstance().getConnection();
            pst = connection.prepareStatement(DELETE_DRIVER_BY_ID);
            pst.setInt(1, driverId);
            int result = pst.executeUpdate();

            if (result == 0) {
                throw new DriverNotFoundException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getInstance().closePreparedStatement(pst);
            getInstance().closeConnection(connection);
        }
    }

    private void createTableIfNotExists() {
        try {
            connection = getInstance().getConnection();
            stmt = connection.createStatement();
            stmt.executeUpdate(CREATE_DRIVER_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getInstance().closeStatement(stmt);
            getInstance().closeConnection(connection);
        }
    }
}
