package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBmethods {

    static H2DBConnection dbConnection = new H2DBConnection();

    public static String getColumnValueFromCountries(String columnName, String countryName) throws SQLException {
        String sqlSelect = "SELECT " + columnName + " FROM COUNTRIES WHERE NAME = '"+ countryName + "'";
        return dbConnection.executeSelect(sqlSelect);
    }

    public static String getCountryByCallingCode(int collingCode) throws SQLException {
        String sqlSelect = "SELECT Name FROM COUNTRIES WHERE Calling_Code = '"+collingCode + "'";
        return dbConnection.executeSelect(sqlSelect);
    }




}
