package simple_dao.dao;


import simple_dao.entity.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FakeCarDao implements CarDAO {
    private List<Car> cars = new ArrayList<>();

    public FakeCarDao() {
        cars.add(new Car(1, "Audi", "A5", 50000));
        cars.add(new Car(2, "Porsche", "Panamera", 100000));
        cars.add(new Car(3, "Porsche", "Cayman", 88000));
    }

    @Override
    public void create(Car car) {
        cars.add(car);
    }

    @Override
    public Car read(long id) {
        return cars.stream().filter(c -> c.getId() == id).collect(Collectors.toList()).get(0);
    }

    @Override
    public void updatePrice(int price, int carId) {
        Car targetCar = read(carId);
        targetCar.setPrice(price);
    }

    @Override
    public void deleteByMake(String make) {
        List<Car> carsToDelete = cars.stream().filter(c -> c.getMake() == make).collect(Collectors.toList());
        for(Car car : carsToDelete) {
            cars.remove(car);
        }
    }

    @Override
    public List<Car> getAll() {
        return cars;
    }
}
