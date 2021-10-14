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

    public PayStatus findPayStatusByDriverId(int id) throws SQLException {
        PayStatus payStatus = null;
        if(getConnection() != null){
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("select pay_status from trip where driver_fk = %d", id));
            while (resultSet.next()){
                payStatus = PayStatus.valueOf(resultSet.getString("pay_status"));
            }
        }
        return payStatus;
    }
    public int findPassengerIdByDriverId(int id) throws SQLException {
        int foundId = 0;
        if(getConnection() != null){
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("select passenger_fk from trip where driver_fk = %d", id));
            while (resultSet.next()){
                foundId = resultSet.getInt("passenger_fk");
            }
        }
        return foundId;
    }
    public void updatePayStatusAfterPayingCash(int id) throws SQLException {
        if(getConnection() != null){
            String sqlQuery = "update trip set pay_status = '"+PayStatus.PAYED.name() +"' " +
                    "where driver_fk = '"+id+"'";
            Statement statement = getConnection().createStatement();
            statement.executeUpdate(sqlQuery);
        }
    }
    public List<Double> findDestinationCoordinationByUsername(String username) throws SQLException {
        List<Double> coordination = new ArrayList<Double>();
        if(getConnection() != null){
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("select destination_lat, destination_long" +
                    " from trip where username = '%s'", username));
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
            stmt.executeUpdate();
        }
    }

    public List<Trip> getOngoingTravels() throws SQLException {
        if(getConnection() != null){
            List<Trip>ongoingTrips = new ArrayList<>();
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select passenger_fk, driver_fk, origin_lat, origin_long, destination_lat, destination_long from trip" +
                    " where pay_status = CASH or pay_status = ACCOUNT");
            while (resultSet.next()){
                Trip trip = new Trip();
                trip.setPassengerId(resultSet.getInt("passenger_fk"));
                trip.setDriverId(resultSet.getInt("driver_fk"));
                trip.setOriginLat(resultSet.getInt("origin_lat"));
                trip.setOriginLong(resultSet.getInt("origin_long"));
                trip.setDestinationLat(resultSet.getInt("destination_lat"));
                trip.setDestinationLong(resultSet.getInt("destination_long"));
                ongoingTrips.add(trip);
            }
            return ongoingTrips;
        }
        return null;
    }
}
