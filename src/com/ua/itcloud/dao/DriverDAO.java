package com.ua.itcloud.dao;

import com.ua.itcloud.exception.DriverNotFoundException;
import com.ua.itcloud.exception.DuplicateDriverException;
import com.ua.itcloud.model.Driver;

import java.util.List;

/**
 * Created by student on 11.06.2018.
 */
public interface DriverDAO {

    void addDriver(Driver driver) throws DuplicateDriverException;

    List<Driver> getAllDrivers();

    Driver getDriverById(int driverId) throws DriverNotFoundException;

    Driver getDriverByLastName(String lastName) throws DriverNotFoundException;

    void updateDriver(Driver driver);

    void deleteDriver(int driverId) throws DriverNotFoundException;
}
