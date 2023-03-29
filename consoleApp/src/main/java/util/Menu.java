package util;

import entity.Salad;
import entity.Vegetable;

import java.util.List;
import java.util.Scanner;

/**
 * @author Stanislav Hlova
 */
public class Menu {
    private Salad salad;
    private Scanner scanner;

    public void start() {
        this.scanner = new Scanner(System.in);
        this.salad = new Salad();
        boolean isContinue = true;
        while (isContinue) {
            printMenuItems();
            System.out.print("Enter the number of the menu:");
            String choice = this.scanner.nextLine();
            switch (choice) {
                case "1" -> printVegetables(this.salad.getVegetables());
                case "2" -> addNewVegetable();
                case "3" -> System.out.printf("The salad's caloric is:%s\n", this.salad.countCaloric());
                case "4" -> {
                    sortVegetablesByWeight();
                    System.out.println("Vegetables has been sorted!");
                }
                case "5" -> findVegetablesByCaloric();
                case "6" -> isContinue = false;
                default -> System.out.println("You enter incorrect number of item. Try again!");
            }
        }
        System.out.println("See you later. Bye!");
    }

    private void findVegetablesByCaloric() {
        System.out.println("Enter a lower border of the range:");
        double lowerBorder = Double.parseDouble(this.scanner.nextLine());
        System.out.println("Enter a higher border of the range:");
        double higherBorder = Double.parseDouble(this.scanner.nextLine());
        printVegetables(salad.getVegetablesInCaloricRange(lowerBorder, higherBorder));

    }

    private void sortVegetablesByWeight() {
        this.salad.sortByWeight();
    }

    private void printMenuItems() {
        System.out.println("--------------- Menu ---------------");
        System.out.println("1 - Show all vegetable in the salad.");
        System.out.println("2 - Add a new vegetable to the salad.");
        System.out.println("3 - Count the caloric of the salad.");
        System.out.println("4 - Sort the vegetables in the salad by weight.");
        System.out.println("5 - Search the vegetables by the range of caloric in the salad.");
        System.out.println("6 - Exit.");
    }

    private void printVegetables(List<Vegetable> vegetables) {
        if (vegetables == null || vegetables.isEmpty()) {
            System.out.println("No vegetables!");
            return;
        }
        final String pattern = "|%30s|%15s|%15s|\n";
        System.out.printf(pattern, "Name", "Caloric", "Weight(gr)");
        for (Vegetable vegetable : vegetables) {
            System.out.printf(pattern, vegetable.getName(), vegetable.getCaloric(), vegetable.getWeight());
        }
    }

    private void addNewVegetable() {
        System.out.println("Enter data for a new Vegetable.");
        System.out.println("Enter a name of vegetable:");
        String name = this.scanner.nextLine();
        System.out.println("Enter a caloric of vegetable:");
        double caloric = Double.parseDouble(this.scanner.nextLine());
        System.out.println("Enter a weight of vegetable:");
        double weight = Double.parseDouble(this.scanner.nextLine());
        this.salad.addVegetable(new Vegetable(name, caloric, weight));
    }


}
