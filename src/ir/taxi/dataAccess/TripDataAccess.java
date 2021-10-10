package ir.taxi.dataAccess;

import ir.taxi.enumeration.PayStatus;
import ir.taxi.enumeration.TripStatus;
import ir.taxi.model.Trip;

import java.sql.PreparedStatement;
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

    public void saveTrip(Trip trip) throws SQLException {
        if(getConnection() != null){
            String sqlQuery = "insert into trip (passenger_fk, driver_fk, origin_lat, origin_long, destination_lat, destination_long," +
                    "price, trip_date, pay_status) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = getConnection().prepareStatement(sqlQuery);
            stmt.setInt(1, trip.getPassengerId());
            stmt.setInt(2, trip.getDriverId());
            stmt.setDouble(3, trip.getOriginLat());
            stmt.setDouble(4, trip.getOriginLong());
            stmt.setDouble(5, trip.getDestinationLat());
            stmt.setDouble(6, trip.getDestinationLong());
            stmt.setInt(7, trip.getPrice());
            stmt.setDate(8, trip.getTripDate());
            stmt.setString(9, trip.getPayStatus().name());
        }
    }

}
