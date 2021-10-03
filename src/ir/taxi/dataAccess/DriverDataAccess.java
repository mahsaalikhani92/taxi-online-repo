package ir.taxi.dataAccess;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Mahsa Alikhani m-58
 */
public class DriverDataAccess extends DataBaseConnection{


    public DriverDataAccess() throws ClassNotFoundException, SQLException {
        super();
    }

    public void saveDriver(String name, String family, String username, int phoneNumber, int nationalCode,
                           Date birthDate, String plaque) throws SQLException {
        int result = 0;
        if(getConnection() != null){
            Statement statement = null;
            try {
                statement = getConnection().createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String sqlQuery = "insert into drivers (username, name, family, phone_number, national_code, birth_date, plaque)" +
                    " values( '"+username+"','"+name+"','"+family+"','"+phoneNumber+"','"+nationalCode+"','"+birthDate+"','"+plaque+"')";
            result = statement.executeUpdate(sqlQuery);
            System.out.println("Record " + result + " inserted.");
        }
        return;
    }
}
