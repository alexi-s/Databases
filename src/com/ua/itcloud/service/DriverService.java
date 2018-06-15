package com.ua.itcloud.service;

import com.ua.itcloud.dao.DriverDAO;
import com.ua.itcloud.dao.impl.DriverDAOH2Impl;
import com.ua.itcloud.exception.DriverNotFoundException;
import com.ua.itcloud.exception.DuplicateDriverException;
import com.ua.itcloud.model.Driver;

import java.util.List;

/**
 * Created by student on 15.06.2018.
 */
public class DriverService {

    private DriverDAO driverDAO = new DriverDAOH2Impl();

    public void getAllDrivers() {
        List<Driver> drivers = driverDAO.getAllDrivers();
        System.out.println("Drivers in database:");
        for (Driver driver : drivers) {
            System.out.println(driver);
        }
    }

    public Driver getDriverById(int id) throws DriverNotFoundException {
        return driverDAO.getDriverById(id);
    }

    public Driver getDriverByLastName(String lastName) throws DriverNotFoundException {
        return driverDAO.getDriverByLastName(lastName);
    }

    public void addDriver(Driver driver) throws DuplicateDriverException {
        driverDAO.addDriver(driver);
    }

    public void deleteDriver(int id) throws DriverNotFoundException {
        driverDAO.deleteDriver(id);
    }

    public void updateDriver(Driver driver) throws DriverNotFoundException, DuplicateDriverException {
        Driver upd = driverDAO.getDriverByLastName(driver.getLastName());
        if (driver.getId() != (upd.getId())) {
            throw new DuplicateDriverException();
        } else {
            driverDAO.updateDriver(driver);
        }
    }
}
