package ir.taxi.dataAccess;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Mahsa Alikhani m-58
 */
public class DriverDataAccess extends DataBaseConnection{

    Connection connection;

    public DriverDataAccess() {
        this.connection = getConnection();
    }

    public void addDriver(int id, String username, String name, String family, int phoneNumber, int nationalCode,
                          Date birthDate, int carId, String plaque, int colorId){
        if(connection != null){
            Statement statement = null;
            try {
                statement = connection.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String sql = "insert into drivers values()";
            int result = statement.executeUpdate()
        }
    }
}
