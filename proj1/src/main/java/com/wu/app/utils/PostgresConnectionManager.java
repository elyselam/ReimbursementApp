package com.wu.app.utils;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PostgresConnectionManager  implements ConnectionManager {

    // jdbc:postgresql://host:port/db
    private static final String CONNECTION_STRING_PREFIX = "jdbc:postgresql://";

    //used in the properties to help store and access the values
    //for connecting to the databse
    public static final String SQL_DAO_USERNAME = "DB_USERNAME";
    public static final String SQL_DAO_PASSWORD = "DB_PASSWORD";
    public static final String SQL_DAO_URL = "DB_URL";

    private Properties propz;

    static {
        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PostgresConnectionManager() {}
    public PostgresConnectionManager(String username, String password, String url) {
        propz = new Properties();
        propz.setProperty("SQL_DAO_USERNAME", username);
        propz.setProperty("SQL_DAO_PASSWORD", password);
        propz.setProperty("SQL_DAO_URL", url);
    }

    public PostgresConnectionManager(Properties propzi) {
        this.propz = propzi;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return this.getConnection(
                propz.getProperty("SQL_DAO_USERNAME"),
                propz.getProperty("SQL_DAO_PASSWORD"),
                propz.getProperty("SQL_DAO_URL"));
    }

    @Override
    public Connection getConnection(String username, String password, String url) throws SQLException {
        if(!assertUrlFormat(url)) {
            throw new IllegalStateException(String.format("Url is not in the proper format for PostgreSQL database %s", url));
        }
        return DriverManager.getConnection(
                url,
                username,
                password);
    }

    private boolean assertUrlFormat(String url) {
        String sub = url.substring(0, CONNECTION_STRING_PREFIX.length());
        return sub.equals(CONNECTION_STRING_PREFIX);
    }
}