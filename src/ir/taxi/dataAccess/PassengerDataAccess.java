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
public class PassengerDataAccess extends DataBaseAccess {

    public PassengerDataAccess() throws ClassNotFoundException, SQLException {
        super();
    }
    public void saveGroupOfPassengers(List<Passenger> passengers) throws SQLException {
        if(getConnection() != null){
            String sqlQuery = "insert into passengers (username, name, family, phone_number, national_code, birth_date," +
                    " balance, status)" +
                    "values(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = getConnection().prepareStatement(sqlQuery);
            for (Passenger item:passengers) {
                stmt.setString(1, item.getUsername());
                stmt.setString(2, item.getName());
                stmt.setString(3, item.getFamily());
                stmt.setString(4, item.getPhoneNumber());
                stmt.setLong(5, item.getNationalCode());
                stmt.setDate(6, item.getBirthDate());
                stmt.setInt(7, item.getBalance());
                stmt.setString(8, item.getStatus().name()); //to string
                stmt.executeUpdate();
            }
        }
        return;
    }

    public String findPassengerByUsername(String username) throws SQLException {
        if(getConnection() != null){
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("select username from passengers where username = '%s'", username));
            while (resultSet.next()){
                return resultSet.getString("username");
            }
        }
        return null;
    }
    public void saveNewPassenger(Passenger passenger) throws SQLException {
        if(getConnection() != null){
            String sqlQuery = "insert into passengers (username, name, family, phone_number, national_code, birth_date, balance, status)" +
                    "values(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = getConnection().prepareStatement(sqlQuery);
            stmt.setString(1, passenger.getUsername());
            stmt.setString(2, passenger.getName());
            stmt.setString(3, passenger.getFamily());
            stmt.setString(4, passenger.getPhoneNumber());
            stmt.setLong(5, passenger.getNationalCode());
            stmt.setDate(6, passenger.getBirthDate());
            stmt.setInt(7, passenger.getBalance());
            stmt.setString(8, passenger.getStatus().name()); //to string
            stmt.executeUpdate();
        }
        return;
    }

    public void updateBalance(String username, double amount) throws SQLException {
        double increasedBalance = findBalanceByUserName(username) + amount;
        if(getConnection() != null){
            Statement statement = getConnection().createStatement();
            String sqlQuery = ("update passengers set balance = '"+increasedBalance+"' where username ='"+ username+"'");
            statement.executeUpdate(sqlQuery);
            System.out.println("Your account balance has been updated.");
        }
    }

    public double findBalanceByUserName(String username) throws SQLException {
        if(getConnection() != null){
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("select balance from passengers where username = '%s'", username);
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()){
                return resultSet.getDouble("balance");
            }
        }
        return 0;
    }
    public Passenger getPassengerInformationByUsername(String username) throws SQLException {
        if(getConnection() != null){
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from passengers where username = '"+ username+"'");
            Passenger passenger = new Passenger();
            while (resultSet.next()){
                passenger.setId(resultSet.getInt("driver_id"));
                passenger.setName(resultSet.getString("name"));
                passenger.setFamily(resultSet.getString("family"));
                passenger.setUsername(resultSet.getString("username"));
                passenger.setPhoneNumber(resultSet.getString("phone_number"));
                passenger.setNationalCode(resultSet.getLong("national_code"));
                passenger.setBirthDate(resultSet.getDate("birth_date"));
                passenger.setBalance(resultSet.getInt("balance"));
                passenger.setStatus(TripStatus.valueOf(resultSet.getString("status"))); //String to enum
            }
            return passenger;
        }
        return null;
    }

    public int findPassengerIdByUsername(String username) throws SQLException {
        if(getConnection() != null){
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("select passenger_id from passengers where username = %s", username);
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()){
                return resultSet.getInt("passenger_id");
            }
        }
        return 0;
    }

    public List<Passenger> findPassengerById(int id) throws SQLException {
        if(getConnection() != null){
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("select name, family, phone_number, balance, status from passengers" +
                    " where passenger_id = %d", id);
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            List<Passenger> passengerInfo = new ArrayList<>();
            while (resultSet.next()){
                Passenger passenger = new Passenger();
                passenger.setName(resultSet.getString("name"));
                passenger.setFamily(resultSet.getString("family"));
                passenger.setPhoneNumber(resultSet.getString("phone_number"));
                passenger.setBalance(resultSet.getInt("balance"));
                passenger.setStatus(TripStatus.valueOf(resultSet.getString("status")));
                passengerInfo.add(passenger);
            }
            return passengerInfo;
        }
        return null;
    }

    public TripStatus findStatusByUsername(String username) throws SQLException {
        TripStatus status = TripStatus.STOP;
        if(getConnection() != null){
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("select status from passengers where username = '%s'", username));
            while (resultSet.next()) {
                status = TripStatus.valueOf(resultSet.getString("status"));
            }
        }
        return status;
    }
    public void updateStatusToONGOINGByUsername(String username) throws SQLException {
        if(getConnection() != null){
            Statement statement = getConnection().createStatement();
            String sqlQuery = "update passengers set status = '"+ TripStatus.ONGOING +"' " +
                    "where username = '"+username+"'";
            statement.executeUpdate(sqlQuery);
        }
    }
    public void updateStatusToSTOPByUsername(String username) throws SQLException {
        if(getConnection() != null){
            Statement statement = getConnection().createStatement();
            String sqlQuery = "update passengers set status = '"+ TripStatus.STOP +"' " +
                    "where username = '"+username+"'";
            statement.executeUpdate(sqlQuery);
        }
    }
    public void updateStatusToSTOPById(int id) throws SQLException {
        if(getConnection() != null){
            Statement statement = getConnection().createStatement();
            String sqlQuery = "update passengers set status = '"+ TripStatus.STOP +"' " +
                    "where passenger_id = '"+id+"'";
            statement.executeUpdate(sqlQuery);
        }
    }

    public List<ir.taxi.model.Passenger> getListOfPassengers() throws SQLException {
        if (getConnection() != null) {
            List<ir.taxi.model.Passenger> passengers = new ArrayList<>();
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from passengers");
            while (resultSet.next()) {
                Passenger passenger = new Passenger();
                passenger.setId(resultSet.getInt("passenger_id"));
                passenger.setName(resultSet.getString("name"));
                passenger.setFamily(resultSet.getString("family"));
                passenger.setUsername(resultSet.getString("username"));
                passenger.setPhoneNumber(resultSet.getString("phone_number"));
                passenger.setNationalCode(resultSet.getLong("national_code"));
                passenger.setBirthDate(resultSet.getDate("birth_date"));
                passenger.setBalance(resultSet.getInt("balance"));
                passenger.setStatus(TripStatus.valueOf(resultSet.getString("status"))); //String to enum
                passengers.add(passenger);
            }
            return passengers;
        }
        return null;
    }
}
