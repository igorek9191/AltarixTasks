package utils;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import org.testng.annotations.Test;

import static org.bouncycastle.asn1.x509.X509ObjectIdentifiers.countryName;

public class H2DBConnection {

    protected static Connection conn;

    static{
        try {
            Class.forName("org.h2.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:h2:file://f:\\testbase", "sa", "");
            Statement stmt = conn.createStatement();

            String sqlDrop = "DROP TABLE COUNTRIES";
            stmt.executeUpdate(sqlDrop);

            String sql =  "CREATE TABLE IF NOT EXISTS COUNTRIES " +
                    "(Name VARCHAR(255) not NULL, " +
                    " Code VARCHAR(255), " +
                    " Currency VARCHAR(255), " +
                    " Language VARCHAR(255), " +
                    " Capital VARCHAR(255), " +
                    " Calling_Code VARCHAR(255), " +
                    " Regional_Block VARCHAR(255), " +
                    " PRIMARY KEY ( Name ))";
            stmt.executeUpdate(sql);

            stmt.execute("INSERT INTO COUNTRIES VALUES('United Kingdom','GBR', 'GBP', 'eng', 'London', '44', 'EU')");
            stmt.execute("INSERT INTO COUNTRIES VALUES('Germany','DEU', 'EUR', 'deu', 'Berlin', '49', 'EU')");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String executeSelect(String sql) throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        String selectResult = null;
        if (resultSet.first()) {
            selectResult = resultSet.getString(1);
        } else {
            AspectLogger.LOG.warn("No result rows for query: \n" + sql);
        }
        return selectResult;
    }
}
