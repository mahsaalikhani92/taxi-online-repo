package ir.taxi.dataAccess;

import ir.taxi.model.Passenger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Mahsa Alikhani m-58
 */
public class DataBaseAccess {

    private Connection connection;
    public DataBaseAccess() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_company","root","5103583");
    }

    public Connection getConnection() {
        return connection;
    }

}
