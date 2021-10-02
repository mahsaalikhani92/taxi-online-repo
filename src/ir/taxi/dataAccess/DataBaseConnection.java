package ir.taxi.dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Mahsa Alikhani m-58
 */
public class DataBaseConnection {

    public Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_company","root","5103583");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
