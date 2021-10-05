package ir.taxi.dataAccess;

import ir.taxi.enumeration.Status;
import ir.taxi.model.Passenger;

import java.sql.*;
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
                stmt.setInt(7, 0);
                stmt.setString(8, "STOP");
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
    public void saveNewPassenger(String name, String family, String username, int phoneNumber, int nationalCode, Date birthDate) throws SQLException {
        if(getConnection() != null){
            String sqlQuery = "insert into drivers (username, name, family, phone_number, national_code, birth_date, balance, status)" +
                    "values(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = getConnection().prepareStatement(sqlQuery);
            stmt.setString(1, username);
            stmt.setString(2, name);
            stmt.setString(3, family);
            stmt.setInt(4, phoneNumber);
            stmt.setInt(5, nationalCode);
            stmt.setDate(6, birthDate);
            stmt.setInt(7, 0);
            stmt.setString(8, "STOP");
        }
        return;
    }

}
