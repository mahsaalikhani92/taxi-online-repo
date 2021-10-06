package ir.taxi.model;

import java.sql.Date;

/**
 * @author Mahsa Alikhani m-58
 */
public class Driver extends Person{

    private String plaque;
    private int carId;

    public Driver(String name, String family, String username, int phoneNumber, int nationalCode, Date birthDate, String plaque, int carId) {
        super(name, family, username, phoneNumber, nationalCode, birthDate);
        this.plaque = plaque;
        this.carId = carId;
    }

    public String getPlaque() {
        return plaque;
    }

    public void setPlaque(String plaque) {
        this.plaque = plaque;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }
}
