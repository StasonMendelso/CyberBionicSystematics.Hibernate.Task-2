package simple_dao.dao;

import simple_dao.entity.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Stanislav Hlova
 */
public class ClientJDBCDao extends AbstractJdbcDao implements ClientDAO {
    protected static class Query {
        public static final String CREATE_CLIENT = "INSERT INTO clients(`name`, age, phone) VALUES(?,?,?)";
        public static final String READ_CLIENT = "SELECT * FROM clients WHERE id = ?";
        public static final String UPDATE_CLIENT = "UPDATE clients SET `name`=?,age=?,phone=? WHERE id=?";
        public static final String DELETE_CLIENT = "DELETE FROM clients WHERE id = ?";
        public static final String READ_ALL_CLIENTS = "SELECT * FROM clients";
    }

    @Override
    public boolean create(Client client) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(Query.CREATE_CLIENT);
            mapToPreparedStatement(preparedStatement, client);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            disposeResources(connection, preparedStatement);
        }
        return false;
    }



    @Override
    public Client read(long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(Query.READ_CLIENT);
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapToClient(resultSet);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            disposeResources(connection, preparedStatement);
        }
        return null;
    }

    @Override
    public boolean update(long id, Client client) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(Query.UPDATE_CLIENT);
            mapToPreparedStatement(preparedStatement, client);
            preparedStatement.setLong(4, id);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            disposeResources(connection, preparedStatement);
        }
        return false;
    }

    @Override
    public boolean delete(long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(Query.DELETE_CLIENT);
            preparedStatement.setLong(1, id);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            disposeResources(connection, preparedStatement);
        }
        return false;
    }

    @Override
    public List<Client> readAll() {
        List<Client> clients = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            try (ResultSet resultSet = statement.executeQuery(Query.READ_ALL_CLIENTS)) {
                while (resultSet.next()) {
                    Client client = mapToClient(resultSet);
                    clients.add(client);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            disposeResources(connection, statement);
        }
        return clients;
    }
    private void mapToPreparedStatement(PreparedStatement preparedStatement, Client client) throws SQLException {
        int index = 1;
        preparedStatement.setString(index++, client.getName());
        preparedStatement.setInt(index++, client.getAge());
        preparedStatement.setString(index, client.getPhone());
    }
    private Client mapToClient(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        int age = resultSet.getInt("age");
        String phone = resultSet.getString("phone");
        return new Client(id, name, age, phone);
    }
}
