package simple_dao;


import java.util.List;

/**
 * Created by Asus on 31.01.2018.
 */
public class Main {

    public static void main(String[] args) {

        CarShopService carShopService = new CarShopService();

        // Use case 1. Test CRUD's 'Create' operation
        // carShopService.createNewCar();

        // Use case 2. Test CRUD's 'Read' operation
        // carShopService.readCar();

        // Use case 3. Test CRUD's 'Update' operation
        // carShopService.updateCar();

        // Use case 4. Test CRUD's 'Delete' operation
        carShopService.deleteCar();
    }
}
