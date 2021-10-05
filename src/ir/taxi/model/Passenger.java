package ir.taxi.model;

import ir.taxi.enumeration.Status;

import java.sql.Date;

/**
 * @author Mahsa Alikhani m-58
 */
public class Passenger extends Person {

    private int balance;
    private Status status;

    public Passenger(String name, String family, String username, int phoneNumber, int nationalCode, Date birthDate, int balance, Status status) {
        super(name, family, username, phoneNumber, nationalCode, birthDate);
        this.balance = balance;
        this.status = status;
    }
}
