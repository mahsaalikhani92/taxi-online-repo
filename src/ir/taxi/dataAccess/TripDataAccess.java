package ir.taxi.dataAccess;

import ir.taxi.enumeration.PayStatus;
import ir.taxi.enumeration.TripStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mahsa Alikhani m-58
 */
public class TripDataAccess extends DataBaseAccess{

    public TripDataAccess() throws ClassNotFoundException, SQLException {
        super();
    }

    public PayStatus findPayStatusByDriverUsername(String username) throws SQLException {
        PayStatus payStatus = null;
        if(getConnection() != null){
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("select pay_status from trip inner join drivers" +
                    "on trip.driver_fk = drivers.driver_id where drivers.username = %s", username));
            while (resultSet.next()){
                payStatus = PayStatus.valueOf(resultSet.getString("pay_status"));
            }
        }
        return payStatus;
    }
    public void updatePayStatusAfterPayingCash(String username) throws SQLException {
        if(getConnection() != null){
            Statement statement = getConnection().createStatement();
            String sqlQuery = "update trip set pay_status = '"+PayStatus.PAYED.name() +"' " +
                    "where driver_fk = (select driver_fk from trip inner join drivers" +
                    "on trip.driver_fk = drivers.driver_id where drivers.username = '"+username+"'";
            statement.executeUpdate(sqlQuery);
        }
    }
    public List<Double> findDestinationCoordinationByUsername(String username) throws SQLException {
        List<Double> coordination = new ArrayList<Double>();
        if(getConnection() != null){
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("select destination_lat, destination_long" +
                    " from trip where username = %s", username));
            while (resultSet.next()){
                coordination.add(resultSet.getDouble("destination_lat"));
                coordination.add(resultSet.getDouble("destination_long"));
            }
        }
        return coordination;
    }

}
