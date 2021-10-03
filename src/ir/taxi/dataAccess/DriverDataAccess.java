package ir.taxi.dataAccess;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Mahsa Alikhani m-58
 */
public class DriverDataAccess extends DataBaseConnection{

    public DriverDataAccess() {
        super();
    }

    public void addDriver(String name, String family, String username, int phoneNumber, int nationalCode,
                          Date birthDate, String plaque){
        int result = 0;
        if(connection != null){
            Statement statement = null;
            try {
                statement = connection.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String sql = "insert into drivers (username, name, family, phone_number, national_code, birth_date, plaque)" +
                    " values( "+username+","+name+","+family+","+phoneNumber+","+nationalCode+","+birthDate+","+plaque+")";
            try {
                result = statement.executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Record " + result + " inserted.");
        }
        return;
    }
}
