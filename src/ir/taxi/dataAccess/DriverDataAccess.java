package ir.taxi.dataAccess;

import java.sql.Connection;
import java.sql.Statement;

/**
 * @author Mahsa Alikhani m-58
 */
public class DriverDataAccess extends DataBaseConnection{

    Connection connection;

    public DriverDataAccess() {
        this.connection = getConnection();
    }

    public void addGroupOfDrivers(int number){
        if(connection != null){
            Statement statement = connection.createStatement();
            String sql = "insert into drivers values()";
            int result = statement.executeUpdate()
        }
    }
}
