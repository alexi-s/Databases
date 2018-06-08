package com.ua.itcloud.dao;

import com.ua.itcloud.model.Car;

import java.util.List;

/**
 * Created by student on 08.06.2018.
 */
public interface CarDAO {

    void addCar(Car car);

    List<Car> getAllCars();

    Car getCarById(int carId);

    void updateCar(Car car);

    void deleteCar(int carId);
}
