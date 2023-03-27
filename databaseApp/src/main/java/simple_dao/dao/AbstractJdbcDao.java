package simple_dao.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class AbstractJdbcDao<E> {

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

    protected void disposeResources(Connection connection, PreparedStatement preparedStatement) {
        if (connection != null && preparedStatement != null) {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
