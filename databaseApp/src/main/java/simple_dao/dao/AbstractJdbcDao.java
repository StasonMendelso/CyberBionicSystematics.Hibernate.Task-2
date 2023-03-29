package simple_dao.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractJdbcDao {

    private static final String URL = "jdbc:mysql://localhost:3306/carshop";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";


    protected Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    protected void disposeResources(Connection connection, Statement statement) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

}
