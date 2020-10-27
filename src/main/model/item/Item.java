package model.item;

/*
    Represents an abstract food item on the menu that has a name, price, and quantity
 */

public abstract class Item {
    private double price;
    private String name;
    private int quantity;

    // EFFECTS: constructs an item if price is larger than 0 and
    //        quantity is larger or equal to 0
    //        otherwise, silently returns
    public Item(String name, double price, int quantity) {
        if (price > 0 && quantity >= 0) {
            this.price = price;
            this.name = name;
            this.quantity = quantity;
        }
    }

    // EFFECTS: returns the price of the item
    public double getPrice() {
        return price;
    }

    // MODIFIES: this
    // EFFECTS: sets item to price if price is larger than 0
    //         otherwise, silently returns
    public void setPrice(double price) {
        if (price > 0) {
            this.price = price;
        }
    }

    // EFFECTS: returns the name of the item
    public String getName() {
        return name;
    }

    // EFFECTS: returns the quantity of the item
    public int getQuantity() {
        return quantity;
    }

    // REQUIRES: quantity >= 0
    // MODIFIES: this
    // EFFECTS: set the quantity of the item on the menu
    public void setQuantity(int quantity) {
        if (quantity >= 0) {
            this.quantity = quantity;
        }
    }

    // EFFECTS: returns true if the item's quantity is equal to zero, otherwise returns false.
    public boolean isOutOfStock() {
        return quantity == 0;
    }
}
