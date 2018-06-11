package com.ua.itcloud.exception;

/**
 * Created by student on 11.06.2018.
 */
public class DriverNotFoundException extends Exception {

    @Override
    public String getMessage() {
        return "Driver not found";
    }
}
