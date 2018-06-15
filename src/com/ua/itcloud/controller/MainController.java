package com.ua.itcloud.controller;

import com.ua.itcloud.dao.DriverDAO;
import com.ua.itcloud.dao.impl.DriverDAOH2Impl;
import com.ua.itcloud.exception.DriverNotFoundException;
import com.ua.itcloud.exception.DuplicateDriverException;
import com.ua.itcloud.model.Driver;
import com.ua.itcloud.service.DriverService;

import java.util.Scanner;

/**
 * Created by student on 15.06.2018.
 */
public class MainController {

    private DriverService driverService = new DriverService();

    public void doWork() {
        while(true) {
            showMenu();
            try {
                makeChoice();
            } catch (DuplicateDriverException e) {
                System.out.println(e.getMessage());
            } catch (DriverNotFoundException d) {
                System.out.println(d.getMessage());
            }
        }
    }

    private void showMenu(){
        System.out.println("Make your choice:");
        System.out.println("1. Show list of drivers");
        System.out.println("2. Add new driver");
        System.out.println("3. Get driver by ID");
        System.out.println("4. Remove driver by ID");
        System.out.println("5. Update driver by ID");
        System.out.println("\n0. Exit");
    }

    private void makeChoice() throws DuplicateDriverException, DriverNotFoundException {
        Scanner s = new Scanner(System.in);
        int choice = s.nextInt();

        switch (choice) {
            case 1:{ // show all
                driverService.getAllDrivers();
                break;
            }
            case 2:{ // add
                driverService.addDriver(addDriverMenu());
                break;
            }
            case 3:{ // get by ID
                driverService.getDriverById(getId());
                break;
            }
            case 4:{ // remove
                driverService.deleteDriver(getId());
                break;
            }
            case 5:{ // update
                driverService.updateDriver(updateDriver(driverService.getDriverById(getId())));
                break;
            }
            case 0:{
                System.out.println("Goodbye!");
                System.exit(1);
            }
            default: {
                System.out.println("Wrong choice");
            }
        }
        System.out.println();
    }

    private Driver addDriverMenu() {
        Scanner scanner = new Scanner(System.in);
        String fn;
        String ln;
        String cat;
        int exp;

        System.out.print("Enter first name: ");
        fn = scanner.next();
        System.out.print("Enter last name: ");
        ln = scanner.next();
        System.out.print("Enter experience: ");
        exp = scanner.nextInt();
        System.out.print("Enter category of drive licence: ");
        cat = scanner.next();
        Driver driver =  new Driver(fn, ln, exp, cat);

        System.out.println(driver);
        return driver;
    }

    private int getId() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter ID: ");
        return scanner.nextInt(); // переделать с контролем вводимых данных
    }

    private Driver updateDriver(Driver driver) {
        System.out.println(driver);
        Scanner scanner = new Scanner(System.in);


        System.out.print("Enter first name: ");
        driver.setFirstName(scanner.next());
        System.out.print("Enter last name: ");
        driver.setLastName(scanner.next());
        System.out.print("Enter experience: ");
        driver.setExperience(scanner.nextInt());
        System.out.print("Enter category of drive licence: ");
        driver.setCategory(scanner.next());
        System.out.println(driver);
        return driver;
    }
}
