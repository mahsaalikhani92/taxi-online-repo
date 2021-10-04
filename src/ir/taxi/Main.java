package ir.taxi;

import ir.taxi.dataAccess.DriverDataAccess;
import ir.taxi.enumeration.MainMenu;
import ir.taxi.model.Driver;
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

    public static void main(String[] args) {
        MainMenu.showMainMenu();
        String choice;
        do {
            choice = scanner.next();
        } while (ValidationUtil.isNumeric(choice));

        int choiceNumber = Integer.parseInt(choice);

        switch (choiceNumber) {
            case 1:
                addGroupOfDriversByAdmin();
                break;
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
            String driverName;
            do {
                System.out.println("Enter driver name:");
                driverName = scanner.next();
            } while (ValidationUtil.isAlphabetic(driverName));
            String driverFamily;
            do {
                System.out.println("Enter driver family:");
                driverFamily = scanner.next();
            } while (ValidationUtil.isAlphabetic(driverFamily));
            String username;
            do {
                System.out.println("Enter driver username:");
                username = scanner.next();
            } while (ValidationUtil.isValidUsername(username));
            String phoneNumber;
            do {
                System.out.println("Enter driver phone number:");
                phoneNumber = scanner.next();
            } while (ValidationUtil.isValidPhoneNumber(phoneNumber));
            String nationalCode;
            do {
                System.out.println("Enter driver national code:");
                nationalCode = scanner.next();
            } while (ValidationUtil.isIranianNationalCode(nationalCode));
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
            String plaque;
            do {
                System.out.println("Enter driver car plate number:");
                plaque = scanner.next();
            } while (ValidationUtil.isIranianCarPlateNumber(plaque));

            Driver driver = taxi.addDriver(driverName, driverFamily, username, Integer.parseInt(phoneNumber),
                    Integer.parseInt(nationalCode), birthDate, plaque);
            drivers.add(driver);
        }
        if(drivers.size() == driverNumbers){
            DriverDataAccess driverDao = null;
            driverDao.saveGroupOfDrivers(drivers);
        }
    }

}
