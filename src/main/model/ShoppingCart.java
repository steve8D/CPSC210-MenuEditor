package model;

import model.Item;

import java.util.ArrayList;

public class ShoppingCart {
    ArrayList<Item> shoppingCart;


    // EFFECTS: initialize a shopping cart
    public ShoppingCart() {
        shoppingCart = new ArrayList<>();
    }

    // REQUIRES: Item i exists in the menu
    // MODIFIES: this
    // EFFECTS: adds Item i to the shopping cart
    public void addItem(Item i) {
        shoppingCart.add(i);
    }

    public void removeItem(Item i) {
        shoppingCart.remove(i);
    }

    public void totalPrice() {
        int totalPrice = 0;
        for (Item i: shoppingCart) {
            totalPrice += i.getPrice();
        }
    }
}
