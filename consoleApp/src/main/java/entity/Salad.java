package entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.DoubleStream;

/**
 * @author Stanislav Hlova
 */
public class Salad {
    private List<Vegetable> vegetables;

    public Salad() {
        this.vegetables = new ArrayList<>();
    }

    public List<Vegetable> getVegetables() {
        return vegetables;
    }

    public void setVegetables(List<Vegetable> vegetables) {
        this.vegetables = vegetables;
    }

    public void addVegetable(Vegetable vegetable) {
        this.vegetables.add(vegetable);
    }

    public double countCaloric() {
        return this.vegetables.stream()
                .flatMapToDouble(vegetable -> DoubleStream.of(vegetable.getCaloric()))
                .sum();
    }

    public void sortByWeight() {
        this.vegetables.sort(Comparator.comparingDouble(Vegetable::getWeight));
    }

    public List<Vegetable> getVegetablesInCaloricRange(double lowerBorder, double higherBorder) {
        return this.vegetables.stream()
                .filter(vegetable -> vegetable.getCaloric() >= lowerBorder && vegetable.getCaloric() <= higherBorder)
                .toList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Salad salad = (Salad) o;

        return Objects.equals(vegetables, salad.vegetables);
    }

    @Override
    public int hashCode() {
        return vegetables != null ? vegetables.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Salad{" +
                "vegetables=" + vegetables +
                '}';
    }
}
