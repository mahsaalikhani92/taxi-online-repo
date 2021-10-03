package ir.taxi;

import ir.taxi.enumeration.MainMenu;
import ir.taxi.model.Taxi;

import java.util.Scanner;

/**
 * @author Mahsa Alikhani m-58
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Taxi taxi = new Taxi();
    public static void main(String[] args) {
        MainMenu.showMainMenu();
        String choice;
        do{
            choice = scanner.next();
        }while (ValidationUtil.isNumeric(choice));

        int choiceNumber = Integer.parseInt(choice);

        switch (choiceNumber){
            case 1:
                addGroupOfDriversByUser();
                break;
        }
    }

    private static void addGroupOfDriversByUser() {
        String numberOfDrivers;
        do{
            System.out.println("Enter number of drivers:");
            numberOfDrivers = scanner.next();
        }while (ValidationUtil.isNumeric(numberOfDrivers));
        taxi.addGroupOfDrivers(Integer.parseInt(numberOfDrivers));
    }
}
