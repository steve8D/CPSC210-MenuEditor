package model.item;

/*
    Represents an item belonging to the baked goods category on the menu.
 */

import org.json.JSONObject;

public class BakedGoods extends Item {

    // EFFECTS: constructs an item of category baked goods with price and quantity equal to zero
//    public BakedGoods(String name) {
//        super(name);
//    }

    // EFFECTS: constructs an item of category baked goods
    public BakedGoods(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    // This method is taken from the following GitHub page
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: returns the bakedgoods item as JSON object
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("category", "bakedgoods");
        jsonObject.put("name", getName());
        jsonObject.put("quantity", getQuantity());
        jsonObject.put("price", getPrice());
        return jsonObject;
    }
}
