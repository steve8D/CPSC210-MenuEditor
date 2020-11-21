package model.item;

/*
    Represents an abstract food item on the menu that has a name, price, and quantity
 */

import model.exception.NegativeInputException;

public abstract class Item {
    private double price;
    private String name;
    private int quantity;

    /*
    REQUIRES: name has a non-zero length
    EFFECTS: Constructs a food item with name.
             If price >= 0, then set price of food item to price. Otherwise, set price to 0.
             If quantity >= 0, then set quantity of food item to quantity. Otherwise, set quantity to 0.
    */
    public Item(String name, double price, int quantity) {
        this.name = name;
        if (price >= 0 && quantity >= 0) {
            this.price = price;
            this.quantity = quantity;
        } else {
            this.price = 0;
            this.quantity = 0;
        }
    }

    // EFFECTS: returns the price of the item
    public double getPrice() {
        return price;
    }

    // MODIFIES: this
    // EFFECTS: sets price of item to price if price is larger than 0
    //         otherwise, throw NegativeInputException
    public void setPrice(double price) throws NegativeInputException {
        if (price < 0) {
            throw new NegativeInputException();
        }
        this.price = price;
    }

    // EFFECTS: returns the name of the item
    public String getName() {
        return name;
    }

    // EFFECTS: returns the quantity of the item
    public int getQuantity() {
        return quantity;
    }

    // MODIFIES: this
    // EFFECTS: set item to quantity if quantity is larger than 0
    //         otherwise, throw NegativeInputException
    public void setQuantity(int quantity) throws NegativeInputException {
        if (quantity < 0) {
            throw new NegativeInputException();
        }
        this.quantity = quantity;
    }

    // EFFECTS: returns true if the item's quantity is equal to zero, otherwise returns false.
    public boolean isOutOfStock() {
        return quantity == 0;
    }

    @Override
    public String toString() {
        return name;
    }
}
