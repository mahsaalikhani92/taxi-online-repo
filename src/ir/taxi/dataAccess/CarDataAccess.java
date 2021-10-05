package ir.taxi.dataAccess;

import ir.taxi.model.Car;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Mahsa Alikhani m-58
 */
public class CarDataAccess extends DataBaseAccess{

    public CarDataAccess() throws ClassNotFoundException, SQLException {
        super();
    }

    public void saveNewCar(Car car) throws SQLException {
        if(getConnection() != null){
            String sqlQuery = "insert into cars (model , color) values (?, ?)";
            PreparedStatement stmt = getConnection().prepareStatement(sqlQuery);
            stmt.setString(1, car.getModel());
            stmt.setString(2, car.getCarColor());
        }
    }
}
