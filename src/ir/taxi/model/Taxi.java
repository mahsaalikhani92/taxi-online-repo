package ir.taxi.model;

import ir.taxi.dataAccess.DriverDataAccess;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Mahsa Alikhani m-58
 */
public class Taxi {

    public Driver addDriver(String name, String family, String username,
                            int phoneNumber, int nationalCode, Date birthDate, String plaque) {

            Driver driver = new Driver(name, family, username, phoneNumber, nationalCode, (java.sql.Date) birthDate, plaque);
            return driver;
    }

    private Driver findDriverByNationalCode(int id){
        return null;
    }
}
