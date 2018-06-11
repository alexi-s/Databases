package com.ua.itcloud.exception;

/**
 * Created by student on 11.06.2018.
 */
public class DuplicateDriverException extends Exception {

    @Override
    public String getMessage() {
        return "Duplicate driver";
    }
}
