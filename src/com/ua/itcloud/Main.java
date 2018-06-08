package com.ua.itcloud;

import com.ua.itcloud.dao.CarDAO;
import com.ua.itcloud.dao.impl.CarDAOH2Impl;
import com.ua.itcloud.model.Car;

/**
 * Created by student on 08.06.2018.
 */
public class Main {
    public static void main(String[] args) {
        CarDAO carDAO = new CarDAOH2Impl();

//        carDAO.addCar(new Car("bmw", 2007, 220.0));
        System.out.println(carDAO.getAllCars());
    }
}
