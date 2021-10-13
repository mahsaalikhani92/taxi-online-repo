package ir.taxi.dataAccess;

import ir.taxi.enumeration.PayStatus;
import ir.taxi.enumeration.TripStatus;
import ir.taxi.model.Driver;
import ir.taxi.model.Passenger;

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
                stmt.setString(9, item.getStatus().name()); //to string
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

    public List<Driver> findDriverById(int id) throws SQLException {
        if(getConnection() != null){
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("select name, family, phone_number, plaque, status from drivers" +
                    " where driver_id = %d", id);
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            List<Driver> driverInfo = new ArrayList<>();
            while (resultSet.next()){
                Driver driver = new Driver();
                driver.setName(resultSet.getString("name"));
                driver.setFamily(resultSet.getString("family"));
                driver.setPhoneNumber(resultSet.getString("phone_number"));
                driver.setPlaque(resultSet.getString("plaque"));
                driver.setStatus(TripStatus.valueOf(resultSet.getString("status")));
                driverInfo.add(driver);
            }
            return driverInfo;
        }
        return null;
    }

    public TripStatus findStatusByUsername(String username) throws Exception {
        TripStatus status = TripStatus.WAIT;
        if(getConnection() != null){
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("select status from drivers where username = '%s'", username));
            while (resultSet.next()) {
                status = TripStatus.valueOf(resultSet.getString("status"));
                if(status == null)
                    throw new Exception("driver status in null.");
            }
        }
        return status;
    }
    public void UpdateDriverLocationByUsername(String username, Double[] point) throws SQLException {
        if(getConnection() != null){
            Statement statement = getConnection().createStatement();
            String sqlQuery = "update drivers set current_lat = '"+point[0]+"' " +
                    ", current_long = '"+point[1]+"' where username = '"+username+"'";
            statement.executeUpdate(sqlQuery);
        }
    }
    public void updateDriverStatusToWaitByUsername(String username) throws SQLException {
        if(getConnection() != null){
            Statement statement = getConnection().createStatement();
            String sqlQuery = "update drivers set status = '"+ TripStatus.WAIT +"' " +
                    "where username = '"+username+"'";
            statement.executeUpdate(sqlQuery);
        }
    }
    public void updateDriverStatusToONGOINGByUsername(String username) throws SQLException {
        if(getConnection() != null){
            Statement statement = getConnection().createStatement();
            String sqlQuery = "update drivers set status = '"+ TripStatus.ONGOING +"' " +
                    "where username = '"+username+"'";
            statement.executeUpdate(sqlQuery);
        }
    }
    public List<Driver>findDriverByWaitStatus() throws SQLException {
        if(getConnection() != null){
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select driver_id, username, name, family, plaque, current_lat, current_long" +
                    " from drivers where status = 'WAIT'");
            List<Driver>drivers = new ArrayList<>();
            while (resultSet.next()){
                Driver driver = new Driver();
                driver.setId(resultSet.getInt("driver_id"));
                driver.setUsername(resultSet.getString("username"));
                driver.setName(resultSet.getString("name"));
                driver.setFamily(resultSet.getString("family"));
                driver.setPlaque(resultSet.getString("plaque"));
                driver.setCurrentLocationLat(resultSet.getDouble("current_lat"));
                driver.setCurrentLocationLong(resultSet.getDouble("current_long"));
                drivers.add(driver);
            }
            return drivers;
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
            stmt.setString(9, driver.getStatus().name()); //to string
            stmt.executeUpdate();
        }
        return;
    }

    public Driver getDriverInformationByUsername(String username) throws SQLException {
        if(getConnection() != null){
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from drivers where username = '"+ username+"'");
            Driver driver = new Driver();
            while (resultSet.next()){
                driver.setId(resultSet.getInt("driver_id"));
                driver.setName(resultSet.getString("name"));
                driver.setFamily(resultSet.getString("family"));
                driver.setUsername(resultSet.getString("username"));
                driver.setPhoneNumber(resultSet.getString("phone_number"));
                driver.setNationalCode(resultSet.getLong("national_code"));
                driver.setBirthDate(resultSet.getDate("birth_date"));
                driver.setPlaque(resultSet.getString("plaque"));
                driver.setCarId(resultSet.getInt("car_fk"));
                driver.setStatus(TripStatus.valueOf(resultSet.getString("status"))); //String to enum
            }
            return driver;
        }
        return null;
    }

    public void updateDriverLocation(String username) throws SQLException, ClassNotFoundException {
        TripDataAccess tripDao = new TripDataAccess();
        List<Double>destinationCoordinate = tripDao.findDestinationCoordinationByUsername(username);
        if(getConnection() != null){
            Statement statement = getConnection().createStatement();
            String sqlQuery = "update drivers set current_lat = '"+ destinationCoordinate.get(0) +"', current_long = '"+ destinationCoordinate.get(1) +"' " +
                    "where username = '"+username+"'";
            statement.executeUpdate(sqlQuery);
        }
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
                driver.setStatus(TripStatus.valueOf(resultSet.getString("status"))); //String to enum
                drivers.add(driver);
            }
            return drivers;
        }
        return null;
    }
}