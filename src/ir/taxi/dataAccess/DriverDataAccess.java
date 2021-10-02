package ir.taxi.dataAccess;

import java.sql.Connection;

/**
 * @author Mahsa Alikhani m-58
 */
public class DriverDataAccess extends DataBaseConnection{

    Connection connection;

    public DriverDataAccess() {
        this.connection = getConnection();
    }

    public void addGroupOfDrivers(int number){

    }
}
