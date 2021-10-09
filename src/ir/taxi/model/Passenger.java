package ir.taxi.model;

import ir.taxi.enumeration.Status;

import java.sql.Date;

/**
 * @author Mahsa Alikhani m-58
 */
public class Passenger extends Person {

    private int balance;

    public Passenger(int id, String name, String family, String username, String phoneNumber, long nationalCode, Date birthDate, String status, int balance) {
        super(id, name, family, username, phoneNumber, nationalCode, birthDate, status);
        this.balance = balance;
    }

    public Passenger() {
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return super.toString() + "," +
                "balance=" + balance;
    }
}
