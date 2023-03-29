package simple_dao.dao;

/**
 * Created by Asus on 31.01.2018.
 */
public class DAOFactory implements IDAOFactory {

    private static IDAOFactory factory;
    private DAOFactory() {
        registerDriver();
    }

    private static void registerDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver is loaded successfully!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static synchronized IDAOFactory getInstance() {
        if (factory == null) {
            factory = new DAOFactory();
        }
        return factory;
    }

    @Override
    public CarDAO getCarDAO() {
        return new CarJDBCDao();
    }

    @Override
    public CarDAO getFakeCarDAO() {
        return new FakeCarDao();
    }

    @Override
    public ClientDAO getClientDAO() {
        return new ClientJDBCDao();
    }

}
