package entity;

import java.util.Objects;

/**
 * @author Stanislav Hlova
 */
public class Vegetable {
    private String name;
    private double caloric;
    private double weight;

    public Vegetable(String name, double caloric, double weight) {
        this.name = name;
        this.caloric = caloric;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCaloric() {
        return caloric;
    }

    public void setCaloric(double caloric) {
        this.caloric = caloric;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vegetable vegetable = (Vegetable) o;

        if (Double.compare(vegetable.caloric, caloric) != 0) return false;
        if (Double.compare(vegetable.weight, weight) != 0) return false;
        return Objects.equals(name, vegetable.name);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        temp = Double.doubleToLongBits(caloric);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Vegetable{" +
                "name='" + name + '\'' +
                ", caloric=" + caloric +
                ", weight=" + weight +
                '}';
    }
}
