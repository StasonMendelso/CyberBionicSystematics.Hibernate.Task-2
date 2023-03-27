package simple_dao.entity;

/**
 * Created by Asus on 31.01.2018.
 */
public class Car {

    private long id;
    private String make;
    private String model;
    private int price;

    public Car(long id, String make, String model, int price) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                '}';
    }
}
