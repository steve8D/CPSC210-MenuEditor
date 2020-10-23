package ui;

import model.MyMenu;
import model.ShoppingCart;
import model.item.BakedGoods;
import model.item.Drinks;
import model.item.Item;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/*
    Represents the customer interface of the restaurant application
 */

public class CustomerInterface {
    private static final String DIRECTORY = "./data/MyMenu.json";
    private ShoppingCart shoppingCart;
    private MyMenu menu;
    private Scanner in;
    private String user;
    private boolean runProgram;

    // EFFECTS: runs the customer interface
    public CustomerInterface() {
        loadCustomerInterface();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void loadCustomerInterface() {
        shoppingCart = new ShoppingCart();
        in = new Scanner(System.in);
        runProgram = true;
        loadMenu();

        while (runProgram) {
            loadInstructions();
            handleInput();
        }
    }

    // MODIFIES: this
    // EFFECTS: adds an item to the shopping cart if the item is on the menu
    //          otherwise prints the item is not found
    public void addItem() {
        Item add = null;
        System.out.println("Please enter the name of the item: ");
        user = in.nextLine();
        for (BakedGoods bakedGoods: menu.getBakedGoods()) {
            if (bakedGoods.getName().equals(user)) {
                add = bakedGoods;
                shoppingCart.addItem(add);
                break;
            }
        }
        for (Drinks drinks: menu.getDrinks()) {
            if (drinks.getName().equals(user)) {
                add = drinks;
                shoppingCart.addItem(add);
                break;
            }
        }
        if (add == null) {
            System.out.println("Item is not offered on the menu currently. Sorry for the inconvenience.");
        }
    }

    // EFFECTS: print the baked goods item and drink item on the menu
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

    // MODIFIES: this
    // EFFECTS: delete an item from the shopping cart if the user added the item in the shopping cart
    //          otherwise prints the item is not found
    public void deleteItem() {
        Item delete = null;
        System.out.println("Please enter the name of the item: ");
        user = in.nextLine();
        for (Item i: shoppingCart.getItems()) {
            if (i.getName().equals(user)) {
                delete = i;
            }
        }
        if (delete == null) {
            System.out.println("Item is not currently in the shopping cart.");
        } else {
            shoppingCart.removeItem(delete);
        }
    }

    // EFFECTS: prints the total price of the items in the shopping cart
    public void checkOut() {
        System.out.println("The total price of the items in your cart is " + shoppingCart.totalPrice());
        System.out.println("---------------------------------------------------");
        System.out.println("Thank you for stopping by our store.");
    }

    // EFFECTS: loads instructions to inform users of available inputs
    public void loadInstructions() {
        System.out.println("Enter 1 to view the menu\n"
                +    "Enter 2 to add an item to the shopping cart\n"
                +    "Enter 3 to remove an item from the shopping cart\n"
                +    "Enter 4 to check out and see your total price in the cart\n"
                +    "Enter 5 to quit the program");
    }

    // MODIFIES: this
    // EFFECTS: redirect the user to other functions on the interface based on the user input
    public void handleInput() {
        user = in.nextLine();
        switch (user) {
            case "1":
                getItem();
                break;
            case "2":
                addItem();
                break;
            case "3":
                deleteItem();
                break;
            case "4":
                checkOut();
                saveMenu();
                runProgram = false;
                break;
            case "5":
                System.out.println("Have a good day!");
                runProgram = false;
                break;
        }
    }

    // The method saveMenu() is based on the following Github code
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: save the menu to file if the directory exists
    //      otherwise, throw FileNotFound exception
    public void saveMenu() {
        try {
            JsonWriter writer = new JsonWriter(DIRECTORY);
            writer.write(menu);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save to: " + DIRECTORY);
        }
    }

    // The method loadMenu() is based on the following Github code
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: loads menu from file if it exists
    //         otherwise, throw IOException and exit the program.
    private void loadMenu() {
        try {
            JsonReader jsonReader = new JsonReader(DIRECTORY);
            menu = jsonReader.read();
            System.out.println("Loaded menu from " + DIRECTORY);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + DIRECTORY);
            System.out.println("Please contact the manager to fix the issue. Sorry for the inconvenience.");
            runProgram = false;
        }
    }
}
