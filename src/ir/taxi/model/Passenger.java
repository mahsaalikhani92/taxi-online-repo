package ir.taxi.model;

import java.sql.Date;

/**
 * @author Mahsa Alikhani m-58
 */
public class Passenger extends Person {

    public Passenger(String name, String family, String username, int phoneNumber, int nationalCode, Date birthDate) {
        super(name, family, username, phoneNumber, nationalCode, birthDate);
    }
}
