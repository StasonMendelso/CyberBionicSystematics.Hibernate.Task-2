package simple_dao.dao;

import simple_dao.entity.Client;

import java.util.List;

/**
 * Created by Asus on 31.01.2018.
 */
public interface ClientDAO {
    boolean create(Client client);

    Client read(long id);

    boolean update(long id, Client client);

    boolean delete(long id);

    List<Client> readAll();

}
