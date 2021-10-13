package ir.taxi;

import ir.taxi.dataAccess.CarDataAccess;
import ir.taxi.dataAccess.DriverDataAccess;
import ir.taxi.dataAccess.PassengerDataAccess;
import ir.taxi.dataAccess.TripDataAccess;
import ir.taxi.enumeration.*;
import ir.taxi.model.*;

import java.sql.SQLException;
import java.util.*;
import java.sql.Date;

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
                    showOngoingTravels();
                    break;
                case 6:
                    showListOfDrivers();
                    break;
                case 7:
                    showListOfPassengers();
                    break;
                default:
                    System.out.println("Invalid input!");

            }
        }
    }

    private static void showOngoingTravels() throws SQLException, ClassNotFoundException {
        TripDataAccess tripDao = new TripDataAccess();
        PassengerDataAccess passengerDao = new PassengerDataAccess();
        DriverDataAccess driverDao = new DriverDataAccess();
        List<Trip> ongoingTrips =  tripDao.getOngoingTravels();
        for (Trip item:ongoingTrips) {
            System.out.println(item.toString());
            List<Passenger> passengerCustomizedInfo = passengerDao.findPassengerById(item.getPassengerId());
            System.out.println(passengerCustomizedInfo);
            List<Driver> driverCustomizedInfo = driverDao.findDriverById(item.getDriverId());
            System.out.println(driverCustomizedInfo);
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
            Date birthDate = getBirthDateFromInput();
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
            Date birthDate = getBirthDateFromInput();
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
            try {
                if(driverDao.findStatusByUsername(username) == TripStatus.WAIT){ /////if not
                    Double[] point = getDriverLocation();
                    driverDao.UpdateDriverLocationByUsername(username, point);
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
                                confirmCashReceiptByDriver(username);
                                break;
                            case 2:
                                TravelFinishByDriver(username);
                                break;
                            case 3:
                                break;
                            default:
                                System.out.println("Invalid number!");
                        }
                    }while (choiceNumber != 3);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
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

    private static void confirmCashReceiptByDriver(String username) throws SQLException, ClassNotFoundException {
        TripDataAccess tripDao = new TripDataAccess();
        if(tripDao.findPayStatusByDriverUsername(username) == PayStatus.CASH){
            tripDao.updatePayStatusAfterPayingCash(username);
        }
    }

    private static void TravelFinishByDriver(String username) throws SQLException, ClassNotFoundException {
        TripDataAccess tripDao = new TripDataAccess();
        if(tripDao.findPayStatusByDriverUsername(username) == PayStatus.PAYED){
            DriverDataAccess driverDao = new DriverDataAccess();
            driverDao.updateDriverLocation(username);
            driverDao.updateDriverStatusToWaitByUsername(username);
            PassengerDataAccess passengerDao = new PassengerDataAccess();
            int passengerId = tripDao.findPassengerIdByDriverUsername(username);
            passengerDao.updateStatusToSTOPById(passengerId);
            System.out.println("Driver location is updated.");
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
        Date birthDate = getBirthDateFromInput();
        String plaque = getCarPlaqueFromInput();
        Driver driver = new Driver(name, family, username, phoneNumber, nationalCode, birthDate, TripStatus.WAIT, plaque, carId);
        driverDao.saveNewDriver(driver);
        System.out.println("Your information was successfully registered.");
    }

    private static void passengerSignUpOrLogin() throws SQLException, ClassNotFoundException {
        System.out.println("Username:");
        String username = getUsernameFromInput();
        PassengerDataAccess passengerDao = new PassengerDataAccess();
        if (passengerDao.findPassengerByUsername(username) != null) {
            System.out.println(passengerDao.getPassengerInformationByUsername(username));
            int choiceNumber;
            do {
                PassengerLoginMenu.showPassengerLoginMenu();
                String choice = getChoiceNumber();
                choiceNumber = Integer.parseInt(choice);
                switch (choiceNumber) {
                    case 1:
                        if(passengerDao.findStatusByUsername(username) == TripStatus.STOP){
                            Double[] point = getOriginDestination();
                            double originLat = point[0];
                            double originLong = point[1];
                            double destinationLat = point[2];
                            double destinationLong = point[3];
                            findAvailableDriver(username, originLat, originLong, destinationLat, destinationLong, PayStatus.CASH);
                        }
                        break;
                    case 2:
                        if(passengerDao.findStatusByUsername(username) == TripStatus.STOP){
                            Double[] point = getOriginDestination();
                            double originLat = point[0];
                            double originLong = point[1];
                            double destinationLat = point[2];
                            double destinationLong = point[3];
                            Trip trip = new Trip();
                            int tripPrice = trip.calculateTripPrice(originLat, originLong, destinationLat, destinationLong);
                            if(passengerDao.findBalanceByUserName(username) < tripPrice){
                                System.out.println("Your balance is not enough!");
                                do{
                                    System.out.println("1. Increase account balance");
                                    System.out.println("2. Exit");
                                    choice = getChoiceNumber();
                                    choiceNumber = Integer.parseInt(choice);
                                }while (choiceNumber == 2);
                                switch (choiceNumber){
                                    case 1:
                                        increasePassengerBalance(username, passengerDao);
                                        break;
                                    case 2:
                                        break;
                                    default:
                                        System.out.println("Invalid value");
                                }
                            }else{
                                findAvailableDriver(username, originLat, originLong, destinationLat, destinationLong, PayStatus.ACCOUNT);
                            }
                        }
                        break;
                    case 3:
                        increasePassengerBalance(username, passengerDao);
                        break;
                    case 4:
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

    private static Double[] getDriverLocation(){
        Double[] point = new Double[2];
        String latitude;
        do{
            System.out.println("Enter your location latitude:");
            latitude = scanner.next();
        }while (!ValidationUtil.isDouble(latitude));
        point[0] = Double.parseDouble(latitude);
        String longitude;
        do{
            System.out.println("Enter your location longitude:");
            longitude = scanner.next();
        }while (!ValidationUtil.isDouble(longitude));
        point[1] = Double.parseDouble(longitude);
        return point;
    }

    private static Double[] getOriginDestination() throws SQLException, ClassNotFoundException {
        System.out.println("Enter the origin and destination of your travel:");
        Double[] point = new Double[4];
        String originLat;
        do{
            System.out.println("Origin latitude:");
            originLat = scanner.next();
        }while (!ValidationUtil.isDouble(originLat));
        point[0] = Double.parseDouble(originLat);
        String originLong;
        do{
            System.out.println("Origin longitude:");
            originLong = scanner.next();
        }while (!ValidationUtil.isDouble(originLong));
        point[1] = Double.parseDouble(originLong);
        String destinationLat;
        do{
            System.out.println("Destination latitude:");
            destinationLat = scanner.next();
        }while (!ValidationUtil.isDouble(destinationLat));
        point[2] = Double.parseDouble(destinationLat);
        String destinationLong;
        do{
            System.out.println("Destination longitude:");
            destinationLong = scanner.next();
        }while (!ValidationUtil.isDouble(destinationLong));
        point[3] = Double.parseDouble(destinationLong);
        return point;
    }
    private static void findAvailableDriver(String username, double originLat, double originLong, double destinationLat, double destinationLong, PayStatus payStatus) throws SQLException, ClassNotFoundException {
        DriverDataAccess driverDao = new DriverDataAccess();
        List<Driver>foundDrivers = driverDao.findDriverByWaitStatus();
        List<Double>distances = new ArrayList<>();
        for (Driver item:foundDrivers) {
            double locLat = item.getCurrentLocationLat();
            double locLong = item.getCurrentLocationLong();
            double distance = Math.sqrt((Math.exp(locLat) - Math.exp(originLat)) + ((Math.exp(locLong)) - Math.exp(originLong)));
            distances.add(distance);
        }
        double minDistance = Collections.min(distances);
        int index = distances.indexOf(minDistance);
        int availableDriverId = foundDrivers.get(index).getId();
        PassengerDataAccess passengerDao = new PassengerDataAccess();
        int passengerID = passengerDao.findPassengerIdByUsername(username);
        Date tripDate = getBirthDateFromInput();
        Trip trip = new Trip(passengerID, availableDriverId, originLat, originLong, destinationLat, destinationLong, tripDate, payStatus);
        TripDataAccess tripDao = new TripDataAccess();
        tripDao.saveTrip(trip);
        passengerDao.updateStatusToONGOINGByUsername(username);
        driverDao.updateDriverStatusToONGOINGByUsername(foundDrivers.get(index).getUsername());
        System.out.println("Your request accepted by " + foundDrivers.get(index).getName() + ", "+
                foundDrivers.get(index).getFamily() + ", " + foundDrivers.get(index).getPlaque());
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
        Date birthDate = getBirthDateFromInput();
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

    private static Date getBirthDateFromInput(){
        String date;
        do {
            System.out.println("Enter birth date like 1370-02-12:");
            date = scanner.next();
        } while (!ValidationUtil.isValidFormatDate(date));
        Date birthDate = java.sql.Date.valueOf(date);//converting string into sql date
        return birthDate;
    }
    private static Date getTripDateFromInput(){
        String date;
        do {
            System.out.println("Enter trip date like 1400-07-01:");
            date = scanner.next();
        } while (!ValidationUtil.isValidFormatDate(date));
        Date TripDate = java.sql.Date.valueOf(date);//converting string into sql date
        return TripDate;
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
