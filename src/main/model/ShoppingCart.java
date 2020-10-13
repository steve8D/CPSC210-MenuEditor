package model;

import model.item.Item;

import java.util.ArrayList;

public class ShoppingCart {
    ArrayList<Item> shoppingCart;

    // EFFECTS: initialize a shopping cart
    public ShoppingCart() {
        shoppingCart = new ArrayList<>();
    }

    // EFFECTS: returns the number of items in the shopping cart
    public int getNumberOfItems() {
        return shoppingCart.size();
    }

    // EFFECTS: returns the items in the shopping cart
    public ArrayList<Item> getItems() {
        return shoppingCart;
    }

    // REQUIRES: Item i is in stock
    // MODIFIES: this
    // EFFECTS: adds Item i to the shopping cart
    public void addItem(Item i) {
        if (!i.isOutOfStock()) {
            shoppingCart.add(i);
        }
    }

    // MODIFIES: this
    // EFFECTS: remove Item i from the shopping cart
    public void removeItem(Item i) {
        shoppingCart.remove(i);
    }

    // MODIFIES: Item
    // EFFECTS: returns the total price of the items in the shopping cart
    //      and deduct one quantity for each item in the cart
    public int totalPrice() {
        int totalPrice = 0;
        for (Item i: shoppingCart) {
            totalPrice += i.getPrice();
            i.setQuantity(i.getQuantity() - 1);
        }
        return totalPrice;
    }
}
