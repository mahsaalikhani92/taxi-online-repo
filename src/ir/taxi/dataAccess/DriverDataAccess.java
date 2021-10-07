package ir.taxi.dataAccess;

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
            String sqlQuery = "insert into drivers (username, name, family, phone_number, national_code, birth_date, plaque, car_id)" +
                    "values(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = getConnection().prepareStatement(sqlQuery);
            for (Driver item : drivers) {
                stmt.setString(1, item.getUsername());
                stmt.setString(2, item.getName());
                stmt.setString(3, item.getFamily());
                stmt.setString(4, item.getPhoneNumber());
                stmt.setLong(5, item.getNationalCode());
                stmt.setDate(6, item.getBirthDate());
                stmt.setString(7, item.getPlaque());
                stmt.setInt(8, item.getCarId());
                stmt.executeUpdate();
            }
        }
    }

    public String findDriverByUsername(String username) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("select username from drivers where username = %s", username));
            while (resultSet.next()) {
                return resultSet.getString("username");
            }
        }
        return null;
    }

    public void saveNewDriver(Driver driver) throws SQLException {
        if (getConnection() != null) {
            String sqlQuery = "insert into drivers (username, name, family, phone_number, national_code, birth_date, plaque, car_id)" +
                    "values(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = getConnection().prepareStatement(sqlQuery);
            stmt.setString(1, driver.getUsername());
            stmt.setString(2, driver.getName());
            stmt.setString(3, driver.getFamily());
            stmt.setString(4, driver.getPhoneNumber());
            stmt.setLong(5, driver.getNationalCode());
            stmt.setDate(6, driver.getBirthDate());
            stmt.setString(7, driver.getPlaque());
            stmt.setInt(8, driver.getCarId());
            stmt.executeUpdate();
        }
        return;
    }

    public List<Driver> getListOfDrivers() throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("selest * from drivers");
            List<Driver> drivers = new ArrayList<>();
            while (resultSet.next()) {
                Driver driver = new Driver(resultSet.getString("name"),
                        resultSet.getString("family"),
                        resultSet.getString("username"),
                        resultSet.getString("phone_number"),
                        resultSet.getInt("national_code"),
                        resultSet.getDate("birth_date"),
                        resultSet.getString("plaque"),
                        resultSet.getInt("car_fk"));
                drivers.add(driver);
            }
            return drivers;
        }
        return null;
    }
}