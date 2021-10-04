package ir.taxi;

import ir.taxi.dataAccess.DriverDataAccess;
import ir.taxi.dataAccess.PassengerDataAccess;
import ir.taxi.enumeration.MainMenu;
import ir.taxi.enumeration.PassengerLoginMenu;
import ir.taxi.enumeration.SignupMenu;
import ir.taxi.model.Driver;
import ir.taxi.model.Passenger;
import ir.taxi.model.Taxi;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * @author Mahsa Alikhani m-58
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Taxi taxi = new Taxi();

    public static void main(String[] args) throws SQLException {
        outer :
        while (true){
            MainMenu.showMainMenu();
            String choice = getChoiceNumber();

            int choiceNumber = Integer.parseInt(choice);

            switch (choiceNumber) {
                case 1:
                    addGroupOfDriversByAdmin();
                    break;
                case 2:
                    addGroupOfPassengersByAdmin();
                    break;
                case 3:
                    DriverSignUpOrLogin();
                    break;
                case 4:
                    passengerSignUpOrLogin();
                    break;

            }
        }
    }

    private static void addGroupOfDriversByAdmin() throws SQLException {
        String numberOfDrivers;
        do {
            System.out.println("Enter number of drivers:");
            numberOfDrivers = scanner.next();
        } while (ValidationUtil.isNumeric(numberOfDrivers));
        int driverNumbers = Integer.parseInt(numberOfDrivers);
        List<Driver> drivers = new ArrayList<Driver>();
        for (int i = 0; i < driverNumbers; i++) {
            String driverName = getName();
            String driverFamily = getFamily();
            String username = getUsername();
            int phoneNumber = getPhoneNumber();
            int nationalCode = getNationalCode();
            Date birthDate = getDate();
            String plaque = getCarPlateNumber();
            Driver driver = taxi.addDriver(driverName, driverFamily, username, phoneNumber, nationalCode, birthDate, plaque);
            drivers.add(driver);
        }
        if(drivers.size() == driverNumbers){
            DriverDataAccess driverDao = null;
            driverDao.saveGroupOfDrivers(drivers);
            System.out.println("New drivers saved successfully.");
        }
    }

    private static void addGroupOfPassengersByAdmin() throws SQLException {
        String numberOfPassengers;
        do {
            System.out.println("Enter number of passengers:");
            numberOfPassengers = scanner.next();
        } while (ValidationUtil.isNumeric(numberOfPassengers));
        int passengerNumbers = Integer.parseInt(numberOfPassengers);
        List<Passenger> passengers = new ArrayList<Passenger>();
        for (int i = 0; i < passengerNumbers; i++) {
            String driverName = getName();
            String driverFamily = getFamily();
            String username = getUsername();
            int phoneNumber = getPhoneNumber();
            int nationalCode = getNationalCode();
            Date birthDate = getDate();
            Passenger passenger = taxi.addPassenger(driverName, driverFamily, username, phoneNumber, nationalCode, birthDate);
            passengers.add(passenger);
        }
        if(passengers.size() == passengerNumbers){
            PassengerDataAccess passengerDao = null;
            passengerDao.saveGroupOfPassengers(passengers);
            System.out.println("New passengers saved successfully.");
        }
    }

    private static void DriverSignUpOrLogin() throws SQLException {
        System.out.println("Username:");
        String username = getUsername();
        DriverDataAccess driverDao = null; //DataBaseAccess DBDao;
        if(driverDao.findDriverByUsername(username) != null){
            System.out.println("Successful login");
        }else{
            int choiceNumber;
            do{
                SignupMenu.showSignupMenu();
                String choice = getChoiceNumber();
                choiceNumber = Integer.parseInt(choice);
                switch (choiceNumber){
                    case 1:
                        driverRegister(driverDao);
                        break;
                    case 2:
                        break;
                    default:
                        System.out.println("Invalid number!");
                }
            }while (choiceNumber != 2);
        }
    }

    private static void driverRegister(DriverDataAccess driverDao) throws SQLException {
        String username;
        String name = getName();
        String family = getFamily();
        do{
            username = getUsername();
        }while (driverDao.findDriverByUsername(username) != null);
        int phoneNumber = getPhoneNumber();
        int nationalCode = getNationalCode();
        Date birthDate = getDate();
        String plaque = getCarPlateNumber();
        driverDao.saveNewDriver(name, family, username, phoneNumber, nationalCode, (java.sql.Date) birthDate, plaque);
        System.out.println("Your information was successfully registered.");
    }

    private static void passengerSignUpOrLogin() throws SQLException {
        System.out.println("Username:");
        String username = getUsername();
        PassengerDataAccess passengerDao = null;
        if(passengerDao.findPassengerByUsername(username) != null){
            System.out.println(username);
            int choiceNumber;
            do {
                PassengerLoginMenu.showPassengerLoginMenu();
                String choice = getChoiceNumber();
                choiceNumber = Integer.parseInt(choice);
                switch (choiceNumber){
                    case 1:
                        String amount;
                        do{
                            System.out.println("Enter amount in RIAL:");
                            amount = scanner.next();
                        }while(ValidationUtil.isNumeric(amount));
                        int amountNumber = Integer.parseInt(amount);

                        break;
                    case 2:
                        break;
                    default:
                        System.out.println("Invalid number!");
                }
            }while (choiceNumber != 2);

        }else{
            int choiceNumber;
            do{
                SignupMenu.showSignupMenu();
                String choice = getChoiceNumber();
                choiceNumber = Integer.parseInt(choice);
                switch (choiceNumber){
                    case 1:
                        passengerRegister(passengerDao);
                        break;
                    case 2:
                        break;
                    default:
                        System.out.println("Invalid number!");
                }
            }while (choiceNumber != 2);
        }
    }

    private static String getChoiceNumber() {
        String choice;
        do {
            choice = scanner.next();
        } while (ValidationUtil.isNumeric(choice));
        return choice;
    }

    private static void passengerRegister(PassengerDataAccess passengerDao) throws SQLException {
        String username;
        String name = getName();
        String family = getFamily();
        do{
            username = getUsername();
        }while (passengerDao.findPassengerByUsername(username) != null);
        int phoneNumber = getPhoneNumber();
        int nationalCode = getNationalCode();
        Date birthDate = getDate();
        passengerDao.saveNewPassenger(name, family, username, phoneNumber, nationalCode, (java.sql.Date) birthDate);
        System.out.println("Your information was successfully registered.");
    }


    private static String getCarPlateNumber() {
        String plaque;
        do {
            System.out.println("Enter driver car plate number:");
            plaque = scanner.next();
        } while (ValidationUtil.isIranianCarPlateNumber(plaque));
        return plaque;
    }

    private static Date getDate() {
        String date;
        do {
            System.out.println("Enter driver birth date:");
            date = scanner.next();
        } while (ValidationUtil.isPersianDate(date));
        DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
        Date birthDate = null;
        try {
            birthDate = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return birthDate;
    }

    private static int getNationalCode() {
        String nationalCode;
        do {
            System.out.println("Enter national code:");
            nationalCode = scanner.next();
        } while (ValidationUtil.isIranianNationalCode(nationalCode));
        return Integer.parseInt(nationalCode);
    }

    private static int getPhoneNumber() {
        String phoneNumber;
        do {
            System.out.println("Enter phone number:");
            phoneNumber = scanner.next();
        } while (ValidationUtil.isValidPhoneNumber(phoneNumber));
        return Integer.parseInt(phoneNumber);
    }

    private static String getUsername() {
        String username;
        do {
            System.out.println("Enter username:");
            username = scanner.next();
        } while (ValidationUtil.isValidUsername(username));
        return username;
    }

    private static String getFamily() {
        String family;
        do {
            System.out.println("Enter family:");
            family = scanner.next();
        } while (ValidationUtil.isAlphabetic(family));
        return family;
    }

    private static String getName() {
        String name;
        do {
            System.out.println("Enter name:");
            name = scanner.next();
        } while (ValidationUtil.isAlphabetic(name));
        return name;
    }

}
