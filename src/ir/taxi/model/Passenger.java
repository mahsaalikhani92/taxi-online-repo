package ir.taxi.model;

import ir.taxi.enumeration.Status;

import java.sql.Date;

/**
 * @author Mahsa Alikhani m-58
 */
public class Passenger extends Person {

    private int balance;
    private Status status;

    public Passenger(String name, String family, String username, String phoneNumber, long nationalCode, Date birthDate, int balance, Status status) {
        super(name, family, username, phoneNumber, nationalCode, birthDate);
        this.balance = balance;
        this.status = status;
    }

    public Passenger() {
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

    @Override
    public String toString() {
        return super.toString() + "," +
                "balance=" + balance +
                ", status=" + status +
                '}';
    }
}
