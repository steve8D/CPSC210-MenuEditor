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
    public void loadOwnerInterface() {
        while (runProgram) {
            printInstructions();
            handleInput();
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
    // EFFECTS: add a drink item to the menu
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
    // EFFECTS: add a baked goods item to the menu
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
        if (remove == null) {
            System.out.println("Cannot find baked goods item on the menu");
        } else {
            menu.removeBakedGoods(remove);
        }
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
        if (remove == null) {
            System.out.println("Cannot find drink item on the menu");
        } else {
            menu.removeDrinks(remove);
        }
    }

    // EFFECTS: handles user input to identify the category of item to modify price from the menu
    public void modifyPrice() {
        System.out.println("Please indicate whether the item is a 'bakedgoods' or a 'drink': ");
        user = in.nextLine();
        if (user.equals("bakedgoods")) {
            modifyPriceBakedGoods();
        } else if (user.equals("drink")) {
            modifyPriceDrink();
        }
    }

    // EFFECTS: this
    // MODIFIES: modify the price of the baked goods item if it's on the menu
    public void modifyPriceBakedGoods() {
        BakedGoods modifyBakedGoodsPrice = null;
        for (BakedGoods bakedGoods: menu.getBakedGoods()) {
            if (bakedGoods.getName().equals(user)) {
                modifyBakedGoodsPrice = bakedGoods;
            }
        }
        if (modifyBakedGoodsPrice == null) {
            System.out.println("Cannot find item on menu");
        } else {
            System.out.println("Please enter the new price for the item: ");
            double newPrice = in.nextDouble();
            modifyBakedGoodsPrice.setPrice(newPrice);
        }
    }

    // EFFECTS: this
    // MODIFIES: modify the price of the drink item if it's on the menu
    public void modifyPriceDrink() {
        Drinks modifyDrinkPrice = null;
        for (Drinks d : menu.getDrinks()) {
            if (d.getName().equals(user)) {
                modifyDrinkPrice = d;
            }
        }
        if (modifyDrinkPrice == null) {
            System.out.println("Cannot find item on the menu.");
        } else {
            System.out.println("Please enter the new price for the item: ");
            double newPrice = in.nextDouble();
            modifyDrinkPrice.setPrice(newPrice);
        }
    }

    // EFFECTS: handles user input to identify the category of item to modify quantity from the menu
    public void modifyQuantity() {
        System.out.println("Please indicate whether the item is a 'bakedgoods' or a 'drink': ");
        user = in.nextLine();
        if (user.equals("bakedgoods")) {
            modifyQuantityBakedGoods();
        } else if (user.equals("drink")) {
            modifyQuantityDrink();
        }
    }

    // MODIFIES: this
    // EFFECTS: modify the quantity of the baked goods item if it's on the menu
    public void modifyQuantityBakedGoods() {
        BakedGoods modifyQuantityBakedGoods = null;
        for (BakedGoods bakedGoods: menu.getBakedGoods()) {
            if (bakedGoods.getName().equals(user)) {
                modifyQuantityBakedGoods = bakedGoods;
            }
        }
        if (modifyQuantityBakedGoods == null) {
            System.out.println("Cannot find item on the menu.");
        } else {
            System.out.println("Please enter the new quantity for the item: ");
            int newQuantity = in.nextInt();
            modifyQuantityBakedGoods.setQuantity(newQuantity);
        }
    }

    // MODIFIES: this
    // EFFECTS: modify the quantity of the baked goods item if it's on the menu
    public void modifyQuantityDrink() {
        Drinks modifyQuantityDrink = null;
        for (Drinks d : menu.getDrinks()) {
            if (d.getName().equals(user)) {
                modifyQuantityDrink = d;
            }
        }
        if (modifyQuantityDrink == null) {
            System.out.println("Cannot find item on the menu.");
        } else {
            System.out.println("Please enter the new quantity for the item: ");
            int setQuantity = in.nextInt();
            modifyQuantityDrink.setQuantity(setQuantity);
        }
    }

    // EFFECTS: prints the instructions to instruct the user what to input
    public void printInstructions() {
        System.out.println("Enter 1 to add an item to the menu\n"
                +    "Enter 2 to view the menu\n"
                +    "Enter 3 to remove an item from the menu\n"
                +    "Enter 4 to modify the price of an item on the menu\n"
                +    "Enter 5 to modify the quantity of an item on the menu\n"
                +    "Enter 6 to quit the program");
    }

    // EFFECTS: redirect the user to other interfaces based on the user input
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
                runProgram = false;
                break;
        }
    }
}
