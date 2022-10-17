package server.DataAccess;


import java.sql.*;

public class DataConnection {

    static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    static final String USER = "postgres";
    static final String PASSWORD = "1234";
    static final String SCHEMA = "newOhmFour";

    private static DataConnection instance = new DataConnection();

    public synchronized static DataConnection getInstance()
    {
        return instance;
    }

    public synchronized Connection getConnection()
    {
        try
        {
            DriverManager.registerDriver(new org.postgresql.Driver());
            return DriverManager.getConnection(DB_URL,USER,PASSWORD);
        }
        catch (SQLException e)
        {
            throw new RuntimeException("DATABASE CONNECTION FAILED!");
        }
    }

}
