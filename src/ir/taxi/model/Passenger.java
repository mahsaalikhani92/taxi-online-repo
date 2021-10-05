package ir.taxi.model;

import ir.taxi.enumeration.Status;

import java.sql.Date;

/**
 * @author Mahsa Alikhani m-58
 */
public class Passenger extends Person {

    private int balance;
    private Status status;

    public Passenger(String name, String family, String username, int phoneNumber, int nationalCode, Date birthDate) {
        super(name, family, username, phoneNumber, nationalCode, birthDate);
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void updateBalanceByPassenger(int amount){

    }
}
