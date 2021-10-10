package ir.taxi.model;

import ir.taxi.enumeration.PayStatus;

import java.sql.Date;
import java.util.Objects;

/**
 * @author Mahsa Alikhani m-58
 */
public class Trip {
    private int id;
    private int passengerId;
    private int driverId;
    private double originLat;
    private double originLong;
    private double destinationLat;
    private double destinationLong;
    private int price;
    private Date tripDate;
    private PayStatus payStatus;

    public Trip(int passengerId, int driverId, double originLat, double originLong, double destinationLat, double destinationLong, Date tripDate, PayStatus payStatus) {
        this.passengerId = passengerId;
        this.driverId = driverId;
        this.originLat = originLat;
        this.originLong = originLong;
        this.destinationLat = destinationLat;
        this.destinationLong = destinationLong;
        this.price = calculateTripPrice(originLat, originLong, destinationLat, destinationLong);
        this.tripDate = tripDate;
        this.payStatus = payStatus;
    }

    public int calculateTripPrice(double origLat, double origLong, double destLat, double destLong){
        double distance = Math.sqrt((Math.exp(origLat) - Math.exp(destLat)) + ((Math.exp(origLong)) - Math.exp(destLong)));
        return (int) (1000 * distance);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public double getOriginLat() {
        return originLat;
    }

    public void setOriginLat(double originLat) {
        this.originLat = originLat;
    }

    public double getOriginLong() {
        return originLong;
    }

    public void setOriginLong(double originLong) {
        this.originLong = originLong;
    }

    public double getDestinationLat() {
        return destinationLat;
    }

    public void setDestinationLat(double destinationLat) {
        this.destinationLat = destinationLat;
    }

    public double getDestinationLong() {
        return destinationLong;
    }

    public void setDestinationLong(double destinationLong) {
        this.destinationLong = destinationLong;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getTripDate() {
        return tripDate;
    }

    public void setTripDate(Date tripDate) {
        this.tripDate = tripDate;
    }

    public PayStatus getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(PayStatus payStatus) {
        this.payStatus = payStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return Double.compare(trip.originLat, originLat) == 0 && Double.compare(trip.originLong, originLong) == 0 && Double.compare(trip.destinationLat, destinationLat) == 0 && Double.compare(trip.destinationLong, destinationLong) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(originLat, originLong, destinationLat, destinationLong);
    }
}
