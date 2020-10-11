package model;

import model.item.BakedGoods;
import model.item.Drinks;
import model.item.Item;

import java.util.ArrayList;

public class MyMenu {


    private ArrayList<Item> drinks;
    private ArrayList<Item> bakedGoods;

    // EFFECTS: construct a list of items on the menu
    public MyMenu() {
        drinks = new ArrayList<>();
        bakedGoods = new ArrayList<>();
    }

    // EFFECTS: return the items in the drink section on the menu
    public ArrayList<Item> getDrinks() {
        return drinks;
    }

    // EFFECTS: return the items in the baked goods section on the menu
    public ArrayList<Item> getBakedGoods() {
        return bakedGoods;
    }

    // REQUIRES: drink item is not on the menu
    // MODIFIES: this
    // EFFECTS: add a drink item on the menu
    public void addDrinks(Drinks i) {
        if (!drinks.contains(i)) {
            drinks.add(i);
        }
    }

    // REQUIRES: baked goods item is not on the menu
    // MODIFIES: this
    // EFFECTS: add a baked goods item on the menu
    public void addBakedGoods(BakedGoods i) {
        if (!bakedGoods.contains(i)) {
            bakedGoods.add(i);
        }
    }

    // MODIFIES: this
    // EFFECTS: remove the drink item from the menu
    public void removeDrinks(Drinks i) {
        drinks.remove(i);
    }

    // MODIFIES: this
    // EFFECTS: remove the baked goods item from the menu
    public void removeBakedGoods(BakedGoods i) {
        bakedGoods.remove(i);
    }

    // EFFECTS: returns number of drink items
    public int getNumberOfDrinks() {
        return drinks.size();
    }

    // EFFECTS: returns number of baked good items
    public int getNumberOfBakedGoods() {
        return bakedGoods.size();
    }
}
