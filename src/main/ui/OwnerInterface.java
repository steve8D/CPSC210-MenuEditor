package ui;

import model.MyMenu;
import model.item.BakedGoods;
import model.item.Drinks;

import java.util.Scanner;

public class OwnerInterface {
    private MyMenu menu;
    private Scanner in;
    private String user;
    private boolean runProgram;

    public OwnerInterface() {
        menu = new MyMenu();
        in = new Scanner(System.in);
        runProgram = true;
    }

    // EFFECTS: print instructions and handle user input
    public void handleInput() {
        while (runProgram) {
            System.out.println("Enter 1 to add an item to the menu\n"
                    +    "Enter 2 to view the menu\n"
                    +    "Enter 3 to remove an item from the menu\n"
                    +    "Enter 4 to quit the program");
            user = in.nextLine();
            switch (user) {
                case "1":
                    addItem();
                    break;
                case "2":
                    getItem();
                    break;
                case "3":
                    removeItem();
                    break;
                case "4":
                    System.out.println("Have a good day!");
                    runProgram = false;
                    break;
            }
        }
    }

    // EFFECTS: handles user input to categorize section of item to add to menu
    public void addItem() {
        System.out.println("Please indicate whether the item is a 'bakedgoods' or a 'drink': ");
        user = in.nextLine();
        if (user.equals("bakedgoods")) {
            addBakedGoods();
        } else if (user.equals("drink")) {
            addDrink();
        }
    }

    // MODIFIES: this
    // EFFECTS: remove a drink item from the menu
    public void addBakedGoods() {
        System.out.println("Please enter the name of the item: ");
        String name = in.nextLine();
        System.out.println("Please enter the price of the item: ");
        double price = in.nextDouble();
        System.out.println("Please enter the quantity of the item: ");
        int quantity = in.nextInt();
        in.nextLine();
        menu.addBakedGoods(new BakedGoods(name, price, quantity));
    }

    // MODIFIES: this
    // EFFECTS: remove a baked goods item from the menu
    public void addDrink() {
        System.out.println("Please enter the name of the item: ");
        String name = in.nextLine();
        System.out.println("Please enter the price of the item: ");
        double price = in.nextDouble();
        System.out.println("Please enter the quantity of the item: ");
        int quantity = in.nextInt();
        in.nextLine();
        menu.addDrinks(new Drinks(name, price, quantity));
    }

    // MODIFIES: this
    // EFFECTS: print the baked goods item and drink item offered on the menu
    public void getItem() {
        System.out.println("The menu currently offers the following baked good items: ");
        for (BakedGoods bakedGoods: menu.getBakedGoods()) {
            System.out.println(bakedGoods.getName());
        }
        System.out.println("and the following drink items: ");
        for (Drinks drinks: menu.getDrinks()) {
            System.out.println(drinks.getName());
        }
    }

    // EFFECTS: handles user input to identify the category of item to remove from the menu
    public void removeItem() {
        System.out.println("Please indicate whether the item is a 'bakedgoods' or a 'drink': ");
        user = in.nextLine();
        if (user.equals("bakedgoods")) {
            removeBakedGoods();
        } else if (user.equals("drink")) {
            removeDrink();
        }
    }

    // MODIFIES: this
    // EFFECTS: remove a baked goods item from the menu
    public void removeBakedGoods() {
        BakedGoods remove = null;
        System.out.println("Please enter the name of the item: ");
        user = in.nextLine();
        for (BakedGoods bakedGoods: menu.getBakedGoods()) {
            if (bakedGoods.getName().equals(user)) {
                remove = bakedGoods;
            }
        }
        menu.removeBakedGoods(remove);
    }

    // MODIFIES: this
    // EFFECTS: remove a drink item from the menu
    public void removeDrink() {
        Drinks remove = null;
        System.out.println("Please enter the name of the item: ");
        user = in.nextLine();
        for (Drinks d: menu.getDrinks()) {
            if (d.getName().equals(user)) {
                remove = d;
            }
        }
        menu.removeDrinks(remove);
    }

}
