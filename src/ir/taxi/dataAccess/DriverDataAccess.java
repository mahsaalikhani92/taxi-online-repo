package ir.taxi.dataAccess;

import ir.taxi.model.Driver;

import java.sql.*;
import java.util.List;

/**
 * @author Mahsa Alikhani m-58
 */
public class DriverDataAccess extends DataBaseConnection{


    public DriverDataAccess() throws ClassNotFoundException, SQLException {
        super();
    }

    /*String name, String family, String username, int phoneNumber, int nationalCode,
    Date birthDate, String plaque*/
    public void saveGroupOfDrivers(List<Driver> drivers) throws SQLException {
        if(getConnection() != null){
            String sqlQuery = "insert into drivers (username, name, family, phone_number, national_code, birth_date, plaque)" +
                    "values(?, ?, ?, ?, ?, ?, ?)";
           PreparedStatement stmt = getConnection().prepareStatement(sqlQuery);
            for (Driver item:drivers) {
                stmt.setString(1, item.getUsername());
                stmt.setString(2, item.getName());
                stmt.setString(3, item.getFamily());
                stmt.setInt(4, item.getPhoneNumber());
                stmt.setInt(5, item.getNationalCode());
                stmt.setDate(6, item.getBirthDate());
                stmt.setString(7, item.getPlaque());
            }
        }
        return;
    }
}
 /*String sqlQuery = "insert into drivers (username, name, family, phone_number, national_code, birth_date, plaque)" +
                    " values( '"+username+"','"+name+"','"+family+"','"+phoneNumber+"','"+nationalCode+"','"+birthDate+"','"+plaque+"')";*/