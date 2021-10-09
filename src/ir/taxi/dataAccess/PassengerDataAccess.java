package ir.taxi.dataAccess;

import ir.taxi.enumeration.TripStatus;
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
