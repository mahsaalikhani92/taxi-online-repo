package ir.taxi.dataAccess;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Mahsa Alikhani m-58
 */
public class WalletAccess extends DataBaseAccess{

    public WalletAccess() throws ClassNotFoundException, SQLException {
        super();
    }

    public void updateBalance(int id, int amount) throws SQLException {
        if(getConnection() != null){
            String sqlQuery = "insert into passenger_wallet (wallet_id, balance) values (?, ?)";
            PreparedStatement stmt = getConnection().prepareStatement(sqlQuery);
            stmt.setInt(1, id);
            stmt.setInt(1, amount);
        }
    }
}
