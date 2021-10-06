package ir.taxi.dataAccess;

import ir.taxi.enumeration.Status;
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
                stmt.setInt(4, item.getPhoneNumber());
                stmt.setInt(5, item.getNationalCode());
                stmt.setDate(6, item.getBirthDate());
                stmt.setInt(7, item.getBalance());
                stmt.setString(8, item.getStatus().name()); //to string
            }
        }
        return;
    }

    public String findPassengerByUsername(String username) throws SQLException {
        if(getConnection() != null){
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("select username from passengers where username = %s", username));
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
            stmt.setInt(4, passenger.getPhoneNumber());
            stmt.setInt(5, passenger.getNationalCode());
            stmt.setDate(6, passenger.getBirthDate());
            stmt.setInt(7, passenger.getBalance());
            stmt.setString(8, passenger.getStatus().name()); //to string
        }
        return;
    }

    public void updateBalance(String username, int amount) throws SQLException {
        int increasedBalance = findBalanceByUserName(username) + amount;
        if(getConnection() != null){
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format( "update passengers set balance = %d where username = %s)", increasedBalance, username);
            statement.executeUpdate(sqlQuery);
            System.out.println("Your account balance has been updated.");
        }
    }

    public int findBalanceByUserName(String username) throws SQLException {
        if(getConnection() != null){
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("select balance from passengers where username = %s", username);
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            return resultSet.getInt("balance");
        }
        return 0;
    }

    public List<ir.taxi.model.Passenger> getListOfPassengers() throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("selest * from passengers");
            List<ir.taxi.model.Passenger> passengers = new ArrayList<>();
            while (resultSet.next()) {
                ir.taxi.model.Passenger passenger = new Passenger(resultSet.getString("name"),
                        resultSet.getString("family"),
                        resultSet.getString("username"),
                        resultSet.getInt("phone_number"),
                        resultSet.getInt("national_code"),
                        resultSet.getDate("birth_date"),
                        resultSet.getInt("balance"),
                        Status.valueOf(resultSet.getString("status")));
                passengers.add(passenger);
            }
            return passengers;
        }
        return null;
    }
}
