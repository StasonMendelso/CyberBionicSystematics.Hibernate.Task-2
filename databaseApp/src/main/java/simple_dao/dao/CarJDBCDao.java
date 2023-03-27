package simple_dao.dao;


import simple_dao.entity.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 31.01.2018.
 */
public class CarJDBCDao extends AbstractJdbcDao implements CarDAO {

    @Override
    public void create(Car car) {
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {

            int makeId = getMakeId(car.getMake(), connection);

            /* if make is not in DB */
            if (makeId == -1) {
                statement = connection.prepareStatement("INSERT INTO makes(make) VALUES (?)");
                statement.setString(1, car.getMake());
                statement.execute();
                statement = connection.prepareStatement("SELECT MAX(id) FROM makes");
                ResultSet rs = statement.executeQuery();
                rs.next();
                makeId = rs.getInt(1);
            }

            statement = connection.prepareStatement("INSERT INTO cars(make_id, model, price) VALUES (?, ?, ?)");

            statement.setInt(1, makeId);
            statement.setString(2, car.getModel());
            statement.setInt(3, car.getPrice());

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disposeResources(connection, statement);
        }

    }

    private int getMakeId(String makeName, Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM makes WHERE make = ? ");
            preparedStatement.setString(1, makeName);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public Car read(long id) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT m.make, c.model, c.price FROM cars as c " +
                    "INNER JOIN makes as m ON m.id = c.make_id WHERE c.id = ? ");

            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String make = rs.getString(1);
                String model = rs.getString(2);
                int price = rs.getInt(3);
                Car car = new Car(id, make, model, price);
                return car;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disposeResources(connection, preparedStatement);
        }

        return null;
    }

    @Override
    public void updatePrice(int price, int carId) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("UPDATE cars SET price = ? WHERE id = ?");

            preparedStatement.setInt(1, price);
            preparedStatement.setInt(2, carId);

            int updatedValues = preparedStatement.executeUpdate();
            System.out.println("Values updated: " + updatedValues);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disposeResources(connection, preparedStatement);
        }

    }

    @Override
    public void deleteByMake(String make) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;

        try {

            int makeId = getMakeId(make, connection);
            preparedStatement = connection.prepareStatement("DELETE FROM cars WHERE make_id = ? ");

            preparedStatement.setInt(1, makeId);

            int deletedValues = preparedStatement.executeUpdate();

            System.out.println("Values deleted: " + deletedValues);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disposeResources(connection, preparedStatement);
        }

    }

    @Override
    public List<Car> getAll() {
        List<Car> allCars = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT c.id, m.make, c.model, c.price FROM cars as c " +
                    "INNER JOIN makes as m ON m.id = c.make_id ");

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                long id = rs.getLong(1);
                String make = rs.getString(2);
                String model = rs.getString(3);
                int price = rs.getInt(4);
                Car car = new Car(id, make, model, price);
                allCars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disposeResources(connection, preparedStatement);
        }
        return allCars;
    }

}
