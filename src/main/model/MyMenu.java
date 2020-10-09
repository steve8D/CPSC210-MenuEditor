package model;

import model.Item;

import java.util.ArrayList;
import java.util.List;

public class MyMenu {
    private ArrayList<Item> menu;

    // EFFECTS: construct a list of items on the menu
    public MyMenu() {
        menu = new ArrayList<>();
    }

    // EFFECTS: return the items on the menu
    public List<Item> getItems() {
        return menu;
    }

    // MODIFIES: this
    // EFFECTS: add the item on the menu
    public void addItem(Item i) {
        menu.add(i);
    }

    // REQUIRES: the item must exists on the menu
    // MODIFIES: this
    // EFFECTS: remove the item from the list
    public void deleteItem(Item i) {
        menu.remove(i);
    }
}
