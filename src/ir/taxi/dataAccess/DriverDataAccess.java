package ir.taxi.dataAccess;

import ir.taxi.enumeration.Status;
import ir.taxi.model.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mahsa Alikhani m-58
 */
public class DriverDataAccess extends DataBaseAccess {


    public DriverDataAccess() throws ClassNotFoundException, SQLException {
        super();
    }

    public void saveGroupOfDrivers(List<Driver> drivers) throws SQLException {
        if (getConnection() != null) {
            String sqlQuery = "insert into drivers (username, name, family, phone_number, national_code, birth_date, car_fk, plaque, status)" +
                    "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = getConnection().prepareStatement(sqlQuery);
            for (Driver item : drivers) {
                stmt.setString(1, item.getUsername());
                stmt.setString(2, item.getName());
                stmt.setString(3, item.getFamily());
                stmt.setString(4, item.getPhoneNumber());
                stmt.setLong(5, item.getNationalCode());
                stmt.setDate(6, item.getBirthDate());
                stmt.setInt(7, item.getCarId());
                stmt.setString(8, item.getPlaque());
                stmt.setString(8, item.getStatus().name()); //to string
                stmt.executeUpdate();
            }
        }
    }

    public String findDriverByUsername(String username) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("select username from drivers where username = '%s'", username));
            while (resultSet.next()) {
                return resultSet.getString("username");
            }
        }
        return null;
    }

    public void saveNewDriver(Driver driver) throws SQLException {
        if (getConnection() != null) {
            String sqlQuery = "insert into drivers (username, name, family, phone_number, national_code, birth_date, car_fk, plaque, status)" +
                    "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = getConnection().prepareStatement(sqlQuery);
            stmt.setString(1, driver.getUsername());
            stmt.setString(2, driver.getName());
            stmt.setString(3, driver.getFamily());
            stmt.setString(4, driver.getPhoneNumber());
            stmt.setLong(5, driver.getNationalCode());
            stmt.setDate(6, driver.getBirthDate());
            stmt.setInt(7, driver.getCarId());
            stmt.setString(8, driver.getPlaque());
            stmt.setString(8, driver.getStatus().name()); //to string
            stmt.executeUpdate();
        }
        return;
    }

    public List<Driver> getListOfDrivers() throws SQLException {
        if (getConnection() != null) {
            List<Driver> drivers = new ArrayList<>();
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from drivers");
            while (resultSet.next()) {
                Driver driver = new Driver();
                driver.setId(resultSet.getInt("driver_id"));
                driver.setName(resultSet.getString("name"));
                driver.setFamily(resultSet.getString("family"));
                driver.setUsername(resultSet.getString("username"));
                driver.setPhoneNumber(resultSet.getString("phone_number"));
                driver.setNationalCode(resultSet.getLong("national_code"));
                driver.setBirthDate(resultSet.getDate("birth_date"));
                driver.setPlaque(resultSet.getString("plaque"));
                driver.setCarId(resultSet.getInt("car_fk"));
                driver.setStatus(Status.valueOf(resultSet.getString("status"))); //String to enum
                drivers.add(driver);
            }
            return drivers;
        }
        return null;
    }
}