package ir.taxi.model;

import java.sql.Date;

/**
 * @author Mahsa Alikhani m-58
 */
public class Driver extends Person{

    private int carId;
    private String plaque;
    private int colorId;

    public Driver(String name, String family, String username, int phoneNumber, int nationalCode, Date birthDate, String plaque) {
        super(name, family, username, phoneNumber, nationalCode, birthDate);
        this.plaque = plaque;
    }

    public String getPlaque() {
        return plaque;
    }

    public void setPlaque(String plaque) {
        this.plaque = plaque;
    }
}
