package ir.taxi.dataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Mahsa Alikhani m-58
 */
public class WalletAccess extends DataBaseAccess{

    public WalletAccess() throws ClassNotFoundException, SQLException {
        super();
    }

    public void saveBalance(int id, int amount) throws SQLException {
        if(getConnection() != null){
            String sqlQuery = "insert into passenger_wallet (wallet_id, balance) values (?, ?)";
            PreparedStatement stmt = getConnection().prepareStatement(sqlQuery);
            stmt.setInt(1, id);
            stmt.setInt(1, amount);
        }
    }

    public void updateBalance(String username, int amount) throws SQLException {
        int increasedBalance = findBalanceByUserName(username) + amount;
        if(getConnection() != null){
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format( "update passenger_wallet set balance = %d " +
                    "where wallet_id = (select national_code from passengers where username = %s)", increasedBalance, username);
            statement.executeUpdate(sqlQuery);
            System.out.println("Your account balance has been updated.");
        }
    }

    public int findBalanceByUserName(String username) throws SQLException {
        if(getConnection() != null){
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("select balance from passenger_wallet" +
                    "where wallet_id = (select national_code from passengers where username = %s", username);
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            return resultSet.getInt("balance");
        }
        return 0;
    }
}
