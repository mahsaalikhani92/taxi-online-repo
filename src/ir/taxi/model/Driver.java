package ir.taxi.model;

import java.sql.Date;

/**
 * @author Mahsa Alikhani m-58
 */
public class Driver {
    private String name;
    private String family;
    private String username;
    private int phoneNumber;
    private int nationalCode;
    private Date birthDate;
    private int carId;
    private String plaque;
    private int colorId;

    public Driver(String name, String family, String username, int phoneNumber, int nationalCode, Date birthDate, String plaque) {
        this.name = name;
        this.family = family;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.nationalCode = nationalCode;
        this.birthDate = birthDate;
        this.plaque = plaque;
    }


}
