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

    // REQUIRES: Item i is in stock
    // MODIFIES: this and Item
    // EFFECTS: adds Item i to the shopping cart and deduct one item from quantity of Item i
    public void addItem(Item i) {
        if (!i.isOutOfStock()) {
            shoppingCart.add(i);
            i.setQuantity(i.getQuantity() - 1);
        }
    }

    // MODIFIES: this and Item
    // EFFECTS: remove Item i from the shopping cart and adds one item into quantity of Item i
    public void removeItem(Item i) {
        shoppingCart.remove(i);
        i.setQuantity(i.getQuantity() + 1);
    }

    // EFFECTS: returns the total price of the items in the shopping cart
    public int totalPrice() {
        int totalPrice = 0;
        for (Item i: shoppingCart) {
            totalPrice += i.getPrice();
        }
        return totalPrice;
    }
}
