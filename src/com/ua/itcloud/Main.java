package com.ua.itcloud;

import com.ua.itcloud.dao.CarDAO;
import com.ua.itcloud.dao.DriverDAO;
import com.ua.itcloud.dao.impl.CarDAOH2Impl;
import com.ua.itcloud.dao.impl.DriverDAOH2Impl;
import com.ua.itcloud.exception.CarNotFoundException;
import com.ua.itcloud.exception.DriverNotFoundException;
import com.ua.itcloud.exception.DuplicateDriverException;
import com.ua.itcloud.model.Car;
import com.ua.itcloud.model.Driver;

/**
 * Created by student on 08.06.2018.
 */
public class Main {
    public static void main(String[] args) {
        CarDAO carDAO = new CarDAOH2Impl();

//        carDAO.addCar(new Car("bmw", 2007, 220.0));
//        try {
//            carDAO.deleteCar(2);
//        } catch (CarNotFoundException e) {
//            System.err.println(e.getMessage());
//        }
//        System.out.println(carDAO.getAllCars());

        DriverDAO driverDAO = new DriverDAOH2Impl();
//        try {
//            driverDAO.addDriver(new Driver("William", "Clinton", 23, "B"));
//            driverDAO.addDriver(new Driver("George", "Bush", 32, "B"));
//        } catch (DuplicateDriverException e) {
//            System.out.println(e.getMessage());
//        }

        System.out.println("All drivers:\n" + driverDAO.getAllDrivers() + "\n");

        try {
            System.out.println(driverDAO.getDriverByLastName("Clinton"));
        } catch (DriverNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println(driverDAO.getDriverByLastName("Clintons"));
        } catch (DriverNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(driverDAO.getDriverById(8));
        } catch (DriverNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
