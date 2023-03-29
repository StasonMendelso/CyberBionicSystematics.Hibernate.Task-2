package simple_dao.service;

import simple_dao.dao.ClientDAO;
import simple_dao.entity.Client;

import java.util.List;

/**
 * @author Stanislav Hlova
 */
public class ClientService {
    private final ClientDAO clientDAO;

    public ClientService(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    public List<Client> getAllClients() {
        return clientDAO.readAll();
    }

    public boolean createNewClient(Client client) {
        return clientDAO.create(client);
    }

    public Client getClientById(long id) {
        return clientDAO.read(id);
    }

    public boolean updateClient(long id, Client client) {
        return clientDAO.update(id, client);
    }

    public boolean removeClient(long id) {
        return clientDAO.delete(id);
    }
}
