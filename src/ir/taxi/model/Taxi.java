package ir.taxi.model;

import java.util.Date;

/**
 * @author Mahsa Alikhani m-58
 */
public class Taxi {

    Driver[] drivers = new Driver[100];
    private int driverIndex = 0;

    public void addGroupOfDrivers(int number, String name, String family, String username,
                                  int phoneNumber, int nationalCode, Date birthDate, String plaque) {
        Driver driver = findDriverByNationalCode(nationalCode);
        if(driver == null){
            driver = new Driver(name, family, username, phoneNumber, nationalCode, (java.sql.Date) birthDate, plaque);
        }

    }

    private Driver findDriverByNationalCode(int id){
        for (int i = 0; i < driverIndex; i++) {
            if(drivers[i])
        }
    }
}
