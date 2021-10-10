package ir.taxi.model;

import ir.taxi.enumeration.TripStatus;

import java.sql.Date;

/**
 * @author Mahsa Alikhani m-58
 */
public class Driver extends Person{

    private String plaque;
    private int carId;
    private double currentLocationLat;
    private double currentLocationLong;

    public Driver(String name, String family, String username, String phoneNumber, long nationalCode, Date birthDate, TripStatus status, String plaque, int carId) {
        super(name, family, username, phoneNumber, nationalCode, birthDate, status);
        this.plaque = plaque;
        this.carId = carId;
    }

    public Driver() {
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

    public double getCurrentLocationLat() {
        return currentLocationLat;
    }

    public void setCurrentLocationLat(double currentLocationLat) {
        this.currentLocationLat = currentLocationLat;
    }

    public double getCurrentLocationLong() {
        return currentLocationLong;
    }

    public void setCurrentLocationLong(double currentLocationLong) {
        this.currentLocationLong = currentLocationLong;
    }

    @Override
    public String toString() {
        return super.toString() + "," +
                "plaque='" + plaque + '\'' +
                ", carId=" + carId +
                '}';
    }
}
