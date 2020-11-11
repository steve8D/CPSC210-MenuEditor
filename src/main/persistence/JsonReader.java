package persistence;

import model.MyMenu;
import model.item.BakedGoods;
import model.item.Drinks;
import model.item.Item;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/*
    Represents a file reader that reads the menu stored in JSON format
 */

// The implementation of JsonReader is based on the following Github code
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReader {
    private final String source;
    private MyMenu menu;

    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads menu from file and returns it;
    // throws IOException if an error occurs reading data from file
    public MyMenu read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        menu = new MyMenu();
        return getItems(menu, jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // MODIFIES: menu
    // EFFECTS: parses food items from JSON object
    private MyMenu getItems(MyMenu menu, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("items");
        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            addItem(menu, nextItem);
        }
        return menu;
    }


    // MODIFIES: menu
    // EFFECTS: parses one food item from JSON object and adds it to the menu
    private void addItem(MyMenu menu, JSONObject jsonObject) {
        Item i;
        int quantity = jsonObject.getInt("quantity");
        double price = jsonObject.getDouble("price");
        String name = jsonObject.getString("name");
        String category = jsonObject.getString("category");
        if (category.equals("drink")) {
            i = new Drinks(name, price, quantity);
        } else {
            i = new BakedGoods(name, price, quantity);
        }
        menu.addItem(i);
    }
}
