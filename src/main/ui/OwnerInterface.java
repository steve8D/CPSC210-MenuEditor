package ui;

import model.MyMenu;
import model.exception.NegativeInputException;
import model.item.BakedGoods;
import model.item.Drinks;
import model.item.Item;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/*
    Represents the owner/manager interface of the restaurant application
 */

public class OwnerInterface {
    private static final String DIRECTORY = "./data/MyMenu.json";
    private MyMenu menu;
    private Scanner in;
    private String user;
    private boolean runProgram;

    // EFFECTS: runs the owner interface
    public OwnerInterface() {
        loadOwnerInterface();
    }

    // MODIFIES: this
    // EFFECTS: loads the owner interface
    public void loadOwnerInterface() {
        loadMenu();
        in = new Scanner(System.in);
        runProgram = true;
        while (runProgram) {
            printInstructions();
            handleInput();
        }
    }

    // MODIFIES: this
    // EFFECTS: add an item to the menu
    public void addItem() {
        System.out.println("Please enter the name of the item: ");
        String name = in.nextLine();
        System.out.println("Please enter the price of the item: ");
        double price = in.nextDouble();
        System.out.println("Please enter the quantity of the item: ");
        int quantity = in.nextInt();
        in.nextLine();
        System.out.println("Please indicate whether the item is a 'bakedgoods' or a 'drink': ");
        user = in.nextLine();
        if (user.equals("bakedgoods")) {
            menu.addItem(new BakedGoods(name, price, quantity));
        } else if (user.equals("drink")) {
            menu.addItem(new Drinks(name, price, quantity));
        }
    }

    // EFFECTS: print the baked goods item and drink item offered on the menu
    public void getItem() {
        System.out.println("The menu currently offers the following baked good items: ");
        System.out.println("----------------------------------------------------------");
        for (BakedGoods bakedGoods: menu.getBakedGoods()) {
            System.out.println(bakedGoods.getName());
        }
        System.out.println("And the following drink items: ");
        System.out.println("----------------------------------------------------------");
        for (Drinks drinks: menu.getDrinks()) {
            System.out.println(drinks.getName());
        }
    }

    // MODIFIES: this
    // EFFECTS: remove item from the menu if the item exists
    //      otherwise, print item not found.
    public void removeItem() {
        Item remove = null;
        System.out.println("Please enter the name of the item: ");
        user = in.nextLine();
        for (Item i: menu.getItems()) {
            if (i.getName().equals(user)) {
                remove = i;
            }
        }
        if (remove == null) {
            System.out.println("Cannot find item on the menu");
        } else {
            menu.removeItem(remove);
        }
    }

    // MODIFIES: this
    // EFFECTS: modify price of an item on the menu if it exists
    //      otherwise, print item not found.
    public void modifyPrice() {
        System.out.println("Enter name of the item: ");
        user = in.nextLine();
        Item modify = null;
        for (Item i: menu.getItems()) {
            if (i.getName().equals(user)) {
                modify = i;
            }
        }
        if (modify == null) {
            System.out.println("Cannot find item on menu");
        } else {
            System.out.println("Please enter the new price for the item: ");
            double newPrice = in.nextDouble();

            try {
                modify.setPrice(newPrice);
                in.nextLine();
                System.out.println("Price of " + modify.getName() + " is now " + newPrice);
            } catch (NegativeInputException e) {
                in.nextLine();
                System.out.println("Sorry you cannot sell an item with a negative price.");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: modify quantity of an item on the menu if it exists
    //      otherwise, print item not found.
    public void modifyQuantity() {
        System.out.println("Enter name of the item: ");
        user = in.nextLine();
        Item modify = null;
        for (Item i: menu.getItems()) {
            if (i.getName().equals(user)) {
                modify = i;
            }
        }
        if (modify == null) {
            System.out.println("Cannot find item on menu");
        } else {
            System.out.println("Please enter the new quantity for the item: ");
            int newQuantity = in.nextInt();

            try {
                modify.setQuantity(newQuantity);
                in.nextLine();
                System.out.println("Quantity of " + modify.getName() + " is now " + newQuantity);
            } catch (NegativeInputException e) {
                in.nextLine();
                System.out.println("Sorry you cannot create a negative amount of item.");
            }
        }
    }

    // EFFECTS: loads instructions to inform users of available inputs
    public void printInstructions() {
        System.out.println("Enter 1 to add an item to the menu\n"
                +    "Enter 2 to view the menu\n"
                +    "Enter 3 to remove an item from the menu\n"
                +    "Enter 4 to modify the price of an item on the menu\n"
                +    "Enter 5 to modify the quantity of an item on the menu\n"
                +    "Enter 6 to quit the program");
    }

    // MODIFIES: this
    // EFFECTS: redirect the user to other functions on the interface based on the user input
    public void handleInput() {
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
                modifyPrice();
                break;
            case "5":
                modifyQuantity();
                break;
            case "6":
                System.out.println("Have a good day!");
                saveMenu();
                runProgram = false;
                break;
        }
    }

    // The method saveMenu() is based on the following Github code
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: save the menu to file
    public void saveMenu() {
        try {
            JsonWriter writer = new JsonWriter(DIRECTORY);
            writer.write(menu);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save to: ");
        }
    }

    // The method loadMenu() is based on the following Github code
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: loads menu from file if file exists,
    //      otherwise, throw IOException and create a new empty menu.
    private void loadMenu() {
        try {
            JsonReader jsonReader = new JsonReader(DIRECTORY);
            menu = jsonReader.read();
            System.out.println("Loaded menu from " + DIRECTORY);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + DIRECTORY + ". Starting a new menu file.");
            menu = new MyMenu();
        }
    }
}
