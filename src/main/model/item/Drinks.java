package model.item;

/*
    Represents an item belonging to the drink category on the menu.
 */

import org.json.JSONObject;

public class Drinks extends Item {

    // EFFECTS: constructs an item of category drinks with price and quantity equal to zero
//    public Drinks(String name) {
//        super(name);
//    }

    // EFFECTS: constructs an item of category drinks
    public Drinks(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    // This method is taken from the following GitHub page
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: returns the item as JSON object
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("category", "drink");
        jsonObject.put("name", getName());
        jsonObject.put("quantity", getQuantity());
        jsonObject.put("price", getPrice());
        return jsonObject;
    }
}
