package cadastro.model.util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author JPZanirati
 */

public class SequenceManager {
    public static int getValue(String sequenceName) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConectorBD.getConnection();
            statement = ConectorBD.getPrepared("SELECT NEXT VALUE FOR " + sequenceName);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

            return -1;
        } catch (SQLException e) {
            System.out.println(e);
            return -1;
        } finally {
            ConectorBD.close(resultSet);
            ConectorBD.close(statement);
            ConectorBD.close(connection);
        }
    }
}