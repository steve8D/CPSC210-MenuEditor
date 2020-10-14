package model.item;

/*
    Represents an item belonging to the baked goods category on the menu.
 */

public class BakedGoods extends Item {

    // constructs a baked goods item on the menu
    // REQUIRES: price > 0 quantity >= 0
    // EFFECTS: constructs an item on the menu
    public BakedGoods(String name, double price, int quantity) {
        super(name, price, quantity);
    }
}
