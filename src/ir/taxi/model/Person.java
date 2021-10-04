package ir.taxi.model;

import java.sql.Date;

/**
 * @author Mahsa Alikhani m-58
 */
public class Person {
    private String name;
    private String family;
    private String username;
    private int phoneNumber;
    private int nationalCode;
    private Date birthDate;

    public Person(String name, String family, String username, int phoneNumber, int nationalCode, Date birthDate) {
        this.name = name;
        this.family = family;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.nationalCode = nationalCode;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(int nationalCode) {
        this.nationalCode = nationalCode;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}