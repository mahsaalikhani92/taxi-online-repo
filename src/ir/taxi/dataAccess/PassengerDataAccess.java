package ir.taxi.dataAccess;

import ir.taxi.model.Passenger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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
            String sqlQuery = "insert into passengers (username, name, family, phone_number, national_code, birth_date)" +
                    "values(?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = getConnection().prepareStatement(sqlQuery);
            for (Passenger item:passengers) {
                stmt.setString(1, item.getUsername());
                stmt.setString(2, item.getName());
                stmt.setString(3, item.getFamily());
                stmt.setInt(4, item.getPhoneNumber());
                stmt.setInt(5, item.getNationalCode());
                stmt.setDate(6, item.getBirthDate());
            }
        }
        return;
    }
}
