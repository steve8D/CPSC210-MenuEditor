package ui;

import model.MyMenu;
import model.ShoppingCart;
import model.item.BakedGoods;
import model.item.Drinks;
import model.item.Item;

import java.util.Scanner;

/*
    Represents the customer interface of the restaurant application
 */

public class CustomerInterface {
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
        menu = new MyMenu();
        in = new Scanner(System.in);
        runProgram = true;

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
                runProgram = false;
                break;
            case "5":
                System.out.println("Have a good day!");
                runProgram = false;
                break;
        }
    }
}
