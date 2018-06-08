package com.ua.itcloud.model;

/**
 * Created by student on 08.06.2018.
 */
public class Car {

    public static final String ID = "id";
    public static final String MODEL = "model";
    public static final String YEAR = "year";
    public static final String MAX_SPEED = "max_speed";

    private int id;
    private String model;
    private int year;
    private double maxSpeed;

    public Car() {
    }

    public Car(String model, int year, double maxSpeed) {
        this.model = model;
        this.year = year;
        this.maxSpeed = maxSpeed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", maxSpeed=" + maxSpeed +
                '}';
    }
}
