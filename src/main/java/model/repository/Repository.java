package model.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Repository {
    private static final Logger LOGGER = Logger.getLogger(Repository.class.getName());
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/cheese_chase";
    private static final String USER = "root";
    private final static String PASSWORD = "Pintea1979flo";

    private static Repository singleInstance = new Repository();

    public Repository(){
        try{
            Class.forName(DRIVER);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    private Connection createConnection() throws SQLException {
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(DBURL, USER, PASSWORD);
        } catch(SQLException e){
            LOGGER.log(Level.SEVERE, "Unable to create connection", e);
            throw e;
        }
        return connection;

    }

    public static Connection getConnection() throws SQLException {
        return singleInstance.createConnection();
    }

    public static void close(Connection connection){
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Unable to close connection", e);
            }
        }
    }
    public static void close(Statement statement){
        if(statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Unable to close statement", e);
            }
        }
    }
    public static void close(ResultSet resultSet){
        if(resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Unable to close result set", e);
            }
        }
    }
}