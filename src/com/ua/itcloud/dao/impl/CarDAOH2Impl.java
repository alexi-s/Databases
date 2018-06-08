package com.ua.itcloud.dao.impl;

import com.ua.itcloud.dao.CarDAO;
import com.ua.itcloud.model.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.ua.itcloud.dao.impl.ConnectionFactory.getInstance;

/**
 * Created by student on 08.06.2018.
 */
public class CarDAOH2Impl implements CarDAO {

    private static final String CREATE_CAR_TABLE = "CREATE TABLE IF NOT EXISTS cars (" +
            Car.ID + " INT(11) PRIMARY KEY AUTO_INCREMENT," +
            Car.MAX_SPEED + " DOUBLE(11)," +
            Car.MODEL + " VARCHAR(255)," +
            Car.YEAR + " INT(4)" +
            ");";
    private static final String INSERT_CAR = String.format("INSERT INTO cars (%s, %s, %s) VALUES (?, ?, ?);", Car.MAX_SPEED, Car.MODEL, Car.YEAR);
    public static final String GET_ALL_CARS = "SELECT * FROM cars";

    private Connection connection;
    private PreparedStatement pst = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    public CarDAOH2Impl() {
        createTableIfNotExists();
    }

    @Override
    public void addCar(Car car) {
        try {
            connection = getInstance().getConnection();
            pst = connection.prepareStatement(INSERT_CAR);

            pst.setDouble(1, car.getMaxSpeed());
            pst.setString(2, car.getModel());
            pst.setInt(3, car.getYear());

            pst.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getInstance().closePreparedStatement(pst);
            getInstance().closeConnection(connection);
        }
    }

    @Override
    public List<Car> getAllCars() {
        List<Car> result = new ArrayList<>();
        try {
            connection = getInstance().getConnection();
            stmt = connection.createStatement();
            rs = stmt.executeQuery(GET_ALL_CARS);

            while (rs.next()){
                Car car = new Car();
                car.setId(rs.getInt(Car.ID));
                car.setMaxSpeed(rs.getDouble(Car.MAX_SPEED));
                car.setModel(rs.getString(Car.MODEL));
                car.setYear(rs.getInt(Car.YEAR));

                result.add(car);
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
    public Car getCarById(int carId) {
        return null;
    }

    @Override
    public void updateCar(Car car) {

    }

    @Override
    public void deleteCar(int carId) {

    }

    private void createTableIfNotExists() {
        try {
            connection = getInstance().getConnection();
            stmt = connection.createStatement();
            stmt.executeUpdate(CREATE_CAR_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            getInstance().closeStatement(stmt);
            getInstance().closeConnection(connection);
        }

    }
}
