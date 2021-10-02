package ir.taxi;

import ir.taxi.enumeration.MainMenu;
import ir.taxi.model.Taxi;

import java.util.Scanner;

/**
 * @author Mahsa Alikhani m-58
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MainMenu mainMenu = null;
        mainMenu.showMainMenu();
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                System.out.println("Enter number of drivers:");
                int numberOfDrivers = scanner.nextInt();
                Taxi taxi = new Taxi();
                taxi.addGroupOfDrivers(numberOfDrivers);
                break;
        }
    }
}
