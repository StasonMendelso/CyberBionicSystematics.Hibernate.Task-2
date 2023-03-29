package simple_dao;


import simple_dao.dao.ClientDAO;
import simple_dao.dao.DAOFactory;
import simple_dao.dao.IDAOFactory;
import simple_dao.entity.Client;
import simple_dao.service.ClientService;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Asus on 31.01.2018.
 */
public class Main {

    public static void main(String[] args) {

        IDAOFactory idaoFactory = DAOFactory.getInstance();
        ClientDAO clientDAO = idaoFactory.getClientDAO();
        ClientService clientService = new ClientService(clientDAO);
        try (Scanner scanner = new Scanner(System.in)) {
            printAllClients(clientService);
            boolean status;
            // Use case 1. Test CRUD's 'Create' operation
            System.out.println("Write a data for new user:");
            System.out.print("Write a name:");
            String name = scanner.nextLine();
            System.out.print("Write an age:");
            int age = Integer.parseInt(scanner.nextLine());
            System.out.print("Write a phone:");
            String phone = scanner.nextLine();
            Client client = new Client(name, age, phone);
            status = clientService.createNewClient(client);
            if (status) {
                System.out.println("New user was created successfully!");
            } else {
                System.out.println("New user wasn't created!");
            }
            printAllClients(clientService);
            // Use case 2. Test CRUD's 'Read' operation
            System.out.print("Write an id of client, which should be viewed, updating and deleting:");
            int id = scanner.nextInt();
            Client clientFromDatabase = clientService.getClientById(id);
            if (clientFromDatabase == null) {
                System.out.println("No client was found. Closing the program.");
                return;
            }
            System.out.println("Client was found");
            printClient(clientFromDatabase);
            // Use case 3. Test CRUD's 'Update' operation
            System.out.println("Write a data for updating a user:");
            System.out.print("Write a new name:");
            scanner.nextLine();
            name = scanner.nextLine();
            System.out.print("Write an new age:");
            age = Integer.parseInt(scanner.nextLine());
            System.out.print("Write a new phone:");
            phone = scanner.nextLine();
            client = new Client(name, age, phone);
            status = clientService.updateClient(clientFromDatabase.getId(), client);
            if (status) {
                System.out.println("Client was updated!");
            } else {
                System.out.println("Client wasn't updated!");
            }
            printAllClients(clientService);
            // Use case 4. Test CRUD's 'Delete' operation
            System.out.println("Enter any symbol and press enter for deleting the selected client.");
            scanner.next();
            status = clientService.removeClient(clientFromDatabase.getId());
            if (status) {
                System.out.println("Client was deleted!");
            } else {
                System.out.println("Client wasn't deleted!");
            }
            printAllClients(clientService);
        }

    }

    private static void printClient(Client client) {
        final String pattern = "|%10s|%40s|%10s|%20s|";
        System.out.printf(pattern + "\n", "id", "name", "age", "phone");
        System.out.printf(pattern + "\n", client.getId(), client.getName(), client.getAge(), client.getPhone());
    }

    private static void printAllClients(ClientService clientService) {
        final String pattern = "|%10s|%40s|%10s|%20s|";
        System.out.println("All clients:");
        List<Client> clientList = clientService.getAllClients();
        System.out.printf(pattern + "\n", "id", "name", "age", "phone");
        for (Client client : clientList) {
            System.out.printf(pattern + "\n", client.getId(), client.getName(), client.getAge(), client.getPhone());
        }
    }
}
