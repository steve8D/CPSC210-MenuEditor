package model.item;

/*
    Represents an item belonging to the drink category on the menu.
 */

public class Drinks extends Item {

    // constructs a drink item on the menu
    // REQUIRES: price > 0, quantity >= 0
    // EFFECTS: constructs an item on the menu
    public Drinks(String name, double price, int quantity) {
        super(name, price, quantity);
    }
}
