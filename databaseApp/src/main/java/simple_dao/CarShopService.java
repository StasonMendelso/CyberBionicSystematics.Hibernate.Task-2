package simple_dao;



import java.util.Collections;
import java.util.List;

import static java.util.Comparator.comparing;
import simple_dao.dao.CarDAO;
import simple_dao.dao.DAOFactory;
import simple_dao.dao.IDAOFactory;
import simple_dao.entity.Car;

public class CarShopService {
    private IDAOFactory factory;
    private CarDAO carDAO;

    public CarShopService() {
        factory = DAOFactory.getInstance();
        carDAO = factory.getCarDAO();
        // carDAO = factory.getFakeCarDAO();
    }

    public void createNewCar() {

        System.out.println("All cars BEFORE insert:");
        printAllCars();

        // TODO: print also car makes before and after new car(s) addition

        // A car with existing in db make
        Car car = new Car(0L, "Audi", "Q7", 77000);
        carDAO.create(car);

        // A car with non-existing in db make
        car = new Car(0L, "Chevrolet", "Corvette", 75000);
        carDAO.create(car);

        System.out.println("\nAll cars AFTER insert:");
        printAllCars();
    }

    public void readCar() {
        Car car = carDAO.read(1);
        System.out.println(car);
    }

    public void updateCar() {
        System.out.println("All cars BEFORE update:");
        printAllCars();

        carDAO.updatePrice(55000, 1);
        carDAO.updatePrice(90000, 2);

        System.out.println("\nAll cars AFTER update:");
        printAllCars();
    }

    public void deleteCar() {
        System.out.println("All cars BEFORE delete:");
        printAllCars();

        // FK should be 'ON UPDATE CASCADE ON DELETE CASCADE'
        carDAO.deleteByMake("Porsche");

        System.out.println("\nAll cars AFTER delete:");
        printAllCars();
    }

    private void printAllCars() {
        List<Car> cars = carDAO.getAll();
        Collections.sort(cars, comparing(Car::getId));
        for(Car car : cars) {
            System.out.println(car);
        }
    }


}
