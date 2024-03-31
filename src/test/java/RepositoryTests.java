import model.repository.Repository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RepositoryTests {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    @Before
    public void setUp() throws SQLException {
        connection = Repository.getConnection();
    }

    @Test
    public void testGetConnection() {
        Assert.assertNotNull(connection);
    }

    @Test
    public void testCloseConnection() throws SQLException {
        Repository.close(connection);
        Assert.assertTrue(connection.isClosed());
    }

    @Test
    public void testCloseStatement() throws SQLException {
        statement = connection.createStatement();
        Repository.close(statement);
        Assert.assertTrue(statement.isClosed());
    }

    @Test
    public void testCloseResultSet() throws SQLException {
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM users");
        Repository.close(resultSet);
        Assert.assertTrue(resultSet.isClosed());
    }

    @After
    public void tearDown() {
        try {
            if (resultSet != null && !resultSet.isClosed()) {
                resultSet.close();
            }
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
