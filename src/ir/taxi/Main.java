package ir.taxi;

import ir.taxi.dataAccess.CarDataAccess;
import ir.taxi.dataAccess.DriverDataAccess;
import ir.taxi.dataAccess.PassengerDataAccess;
import ir.taxi.enumeration.*;
import ir.taxi.model.Car;
import ir.taxi.model.Driver;
import ir.taxi.model.Passenger;
import ir.taxi.model.Taxi;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

/**
 * @author Mahsa Alikhani m-58
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Taxi taxi = new Taxi();

    public static void main(String[] args) throws SQLException, ClassNotFoundException{
        while (true) {
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
                case 5:
                    showListOfDrivers();
                    break;
                case 6:
                    showListOfPassengers();
                    break;
                default:
                    System.out.println("Invalid input!");

            }
        }
    }

    private static void showListOfDrivers() throws SQLException, ClassNotFoundException {
        DriverDataAccess driverDao = new DriverDataAccess();
        List<Driver> drivers = driverDao.getListOfDrivers();
        for (Driver item : drivers) {
            System.out.println(item.toString());
        }
    }

    private static void showListOfPassengers() throws SQLException, ClassNotFoundException {
        PassengerDataAccess passengerDao = new PassengerDataAccess();
        List<Passenger> passengers = passengerDao.getListOfPassengers();
        for (Passenger item : passengers) {
            System.out.println(item.toString());
        }
    }

    private static void addGroupOfDriversByAdmin() throws SQLException, ClassNotFoundException{
        String numberOfDrivers;
        do {
            System.out.println("Enter number of drivers");
            numberOfDrivers = scanner.next();
        } while (!ValidationUtil.isNumeric(numberOfDrivers));
        int driverNumbers = Integer.parseInt(numberOfDrivers);
        List<Integer> autoIds = addGroupOfCarByAdmin(driverNumbers);
        System.out.println("Enter drivers information");
        List<Driver> drivers = new ArrayList<Driver>();
        for (int i = 0; i < driverNumbers; i++) {
            String driverName = getNameFromInput();
            String driverFamily = getFamilyFromInput();
            String username = getUsernameFromInput();
            String phoneNumber = getPhoneNumberFromInput();
            long nationalCode = getNationalCodeFromInput();
            Date birthDate = getDateFromInput();
            String plaque = getCarPlaqueFromInput();
            int carId = autoIds.get(i);
            Driver driver = new Driver(driverName, driverFamily, username, phoneNumber, nationalCode, birthDate, TripStatus.STOP, plaque, carId);
            drivers.add(driver);
        }
        if (drivers.size() == driverNumbers) {
            DriverDataAccess driverDao = new DriverDataAccess();
            driverDao.saveGroupOfDrivers(drivers);
            System.out.println("New drivers saved successfully.");
        }
    }

    public static List<Integer> addGroupOfCarByAdmin(int number) throws SQLException, ClassNotFoundException {
        System.out.println("Enter cars information");
        List<Car> cars = new ArrayList<Car>();
        for (int i = 0; i < number; i++) {
            String model = getCarModelFromInput();
            String carColor = getCarColorFromInput();
            Car car = new Car(model, carColor);
            cars.add(car);
        }
        if (cars.size() == number) {
            CarDataAccess carDao = new CarDataAccess();
            List<Integer> autoIds = carDao.saveGroupOfCar(cars);
            System.out.println("Cars are saved successfully.");
            return autoIds;
        }
        return null;
    }

    private static void addGroupOfPassengersByAdmin() throws SQLException, ClassNotFoundException {
        String numberOfPassengers;
        do {
            System.out.println("Enter number of passengers:");
            numberOfPassengers = scanner.next();
        } while (!ValidationUtil.isNumeric(numberOfPassengers));
        int passengerNumbers = Integer.parseInt(numberOfPassengers);
        List<Passenger> passengers = new ArrayList<Passenger>();
        for (int i = 0; i < passengerNumbers; i++) {
            String passengerName = getNameFromInput();
            String passengerFamily = getFamilyFromInput();
            String username = getUsernameFromInput();
            String phoneNumber = getPhoneNumberFromInput();
            long nationalCode = getNationalCodeFromInput();
            Date birthDate = getDateFromInput();
            Passenger passenger = new Passenger(passengerName, passengerFamily, username, phoneNumber, nationalCode, birthDate, TripStatus.STOP, 0);
            passengers.add(passenger);
        }
        if (passengers.size() == passengerNumbers) {
            PassengerDataAccess passengerDao = new PassengerDataAccess();
            passengerDao.saveGroupOfPassengers(passengers);
            System.out.println("New passengers saved successfully.");
        }
    }

    private static void DriverSignUpOrLogin() throws SQLException, ClassNotFoundException {
        System.out.println("Username:");
        String username = getUsernameFromInput();
        DriverDataAccess driverDao = new DriverDataAccess();
        if (driverDao.findDriverByUsername(username) != null) {
            if(driverDao.findStatusByUsername(username) == TripStatus.WAIT){
                int choiceNumber;
                do{
                    System.out.println("You are waiting for a trip request.");
                    System.out.println("1. Exit");
                    String choice = getChoiceNumber();
                    choiceNumber = Integer.parseInt(choice);
                }while (choiceNumber != 1);
            }else if(driverDao.findStatusByUsername(username) == TripStatus.ONGOING){
                System.out.println(driverDao.getDriverInformationByUsername(username));
                int choiceNumber;
                do{
                    DriverLoginMenu.showDriverLoginMenu();
                    String choice = getChoiceNumber();
                    choiceNumber = Integer.parseInt(choice);
                    switch (choiceNumber){
                        case 1:
                            confirmCashReceiptByDriver();
                            break;
                        case 2:
                            //TODO
                            break;
                        case 3:
                            break;
                        default:
                            System.out.println("Invalid number!");
                    }
                }while (choiceNumber != 3);
            }
        } else {
            int choiceNumber;
            do {
                SignupMenu.showSignupMenu();
                String choice = getChoiceNumber();
                choiceNumber = Integer.parseInt(choice);
                switch (choiceNumber) {
                    case 1:
                        driverRegister(driverDao);
                        break;
                    case 2:
                        break;
                    default:
                        System.out.println("Invalid number!");
                }
            } while (choiceNumber != 2);
        }
    }

    private static Integer addNewCar() throws SQLException, ClassNotFoundException {
        System.out.println("Enter car information");
        String model = getCarModelFromInput();
        String carColor = getCarColorFromInput();
        Car car = new Car(model, carColor);
        CarDataAccess carDao = new CarDataAccess();
        int carId = carDao.saveNewCar(car);
        System.out.println("New car is saved successfully.");
        return carId;
    }

    private static String getCarColorFromInput() {
        String carColor;
        do {
            System.out.println("Enter color of car:");
            carColor = scanner.next();
        } while (!ValidationUtil.isLetter(carColor));
        return carColor;
    }

    private static String getCarModelFromInput() {
        String model;
        do {
            System.out.println("Enter model of car:");
            model = scanner.next();
        } while (!ValidationUtil.isAlphabetic(model));
        return model;
    }

    private static void driverRegister(DriverDataAccess driverDao) throws SQLException, ClassNotFoundException {
        int carId = addNewCar();
        System.out.println("Enter your information");
        String username;
        String name = getNameFromInput();
        String family = getFamilyFromInput();
        do {
            username = getUsernameFromInput();
        } while (driverDao.findDriverByUsername(username) != null);
        String phoneNumber = getPhoneNumberFromInput();
        long nationalCode = getNationalCodeFromInput();
        Date birthDate = getDateFromInput();
        String plaque = getCarPlaqueFromInput();
        Driver driver = new Driver(name, family, username, phoneNumber, nationalCode, birthDate, TripStatus.STOP, plaque, carId);
        driverDao.saveNewDriver(driver);
        System.out.println("Your information was successfully registered.");
    }

    private static void passengerSignUpOrLogin() throws SQLException, ClassNotFoundException {
        System.out.println("Username:");
        String username = getUsernameFromInput();
        PassengerDataAccess passengerDao = new PassengerDataAccess();
        if (passengerDao.findPassengerByUsername(username) != null) {
            System.out.println(username);
            int choiceNumber;
            do {
                PassengerLoginMenu.showPassengerLoginMenu();
                String choice = getChoiceNumber();
                choiceNumber = Integer.parseInt(choice);
                switch (choiceNumber) {
                    case 1:
                        increasePassengerBalance(username, passengerDao);
                        break;
                    case 2:
                        chooseVehicleByPassenger();
                        break;
                    case 3:
                        break;
                    default:
                        System.out.println("Invalid number!");
                }
            } while (choiceNumber != 2);
        } else {
            System.out.println("Username " + username + " doesn't exist, Register or Exit");
            int choiceNumber;
            do {
                SignupMenu.showSignupMenu();
                String choice = getChoiceNumber();
                choiceNumber = Integer.parseInt(choice);
                switch (choiceNumber) {
                    case 1:
                        passengerRegister(passengerDao);
                        break;
                    case 2:
                        break;
                    default:
                        System.out.println("Invalid number!");
                }
            } while (choiceNumber != 2);
        }
    }

    private static void increasePassengerBalance(String username, PassengerDataAccess passengerDao) throws SQLException {
        String amount;
        do {
            System.out.println("Enter amount in RIAL:");
            amount = scanner.next();
        } while (!ValidationUtil.isNumeric(amount));
        double amountNumber = Double.parseDouble(amount);
        passengerDao.updateBalance(username, amountNumber);
    }

    private static void passengerRegister(PassengerDataAccess passengerDao) throws SQLException{
        String name = getNameFromInput();
        String family = getFamilyFromInput();
        String username;
        do {
            username = getUsernameFromInput();
        } while (passengerDao.findPassengerByUsername(username) != null);
        String phoneNumber = getPhoneNumberFromInput();
        long nationalCode = getNationalCodeFromInput();
        Date birthDate = getDateFromInput();
        Passenger passenger = new Passenger(name, family, username, phoneNumber, nationalCode, birthDate, TripStatus.STOP, 0);
        passengerDao.saveNewPassenger(passenger);
        System.out.println("Your information was successfully registered.");
    }

    private static String getChoiceNumber() {
        String choice;
        do {
            choice = scanner.next();
        } while (!ValidationUtil.isNumeric(choice));
        return choice;
    }

    private static String getCarPlaqueFromInput() {
        String plaque;
        do {
            System.out.println("Enter driver car plate number:");
            plaque = scanner.next();
        } while (!ValidationUtil.isAlphabetic(plaque));
        return plaque;
    }

    private static Date getDateFromInput(){
        String date;
        do {
            System.out.println("Enter birth date like 1370-02-12:");
            date = scanner.next();
        } while (!ValidationUtil.isValidFormatDate(date));
        Date birthDate = java.sql.Date.valueOf(date);//converting string into sql date
        return birthDate;
    }

    private static long getNationalCodeFromInput() {
        String nationalCode;
        do {
            System.out.println("Enter national code:");
            nationalCode = scanner.next();
        } while (!ValidationUtil.isNumeric(nationalCode) && !ValidationUtil.isIranianNationalCode(nationalCode));
        return Long.parseLong(nationalCode);
    }

    private static String getPhoneNumberFromInput() {
        String phoneNumber;
        do {
            System.out.println("Enter phone number:");
            phoneNumber = scanner.next();
        } while (!ValidationUtil.isValidPhoneNumber(phoneNumber));
        return phoneNumber;
    }

    private static String getUsernameFromInput() {
        String username;
        do {
            System.out.println("Enter username:\nUsername must be longer than 4 character.");
            username = scanner.next();
        } while (!ValidationUtil.isValidUsername(username));
        return username;
    }

    private static String getFamilyFromInput() {
        String family;
        do {
            System.out.println("Enter family:");
            family = scanner.next();
        } while (!ValidationUtil.isLetter(family));
        return family;
    }

    private static String getNameFromInput() {
        String name;
        do {
            System.out.println("Enter name:");
            name = scanner.next();
        } while (!ValidationUtil.isLetter(name));
        return name;
    }

    private static void chooseVehicleByPassenger(){
        Vehicle.showVehicleMenu();
        String choice = getChoiceNumber();
        int choiceNumber = Integer.parseInt(choice);
        switch (choiceNumber){
            case 1:
                System.out.println("Car");
                //TODO
                break;
            case 2:
                System.out.println("Motor cycle");
                //TODO
                break;
            case 3:
                System.out.println("Van");
                //TODO
                break;
            case 4:
                System.out.println("RV");
                //TODO
                break;
            default:
                System.out.println("Invalid value!");
        }
    }

}
