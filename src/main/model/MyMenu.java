package model;

import model.item.BakedGoods;
import model.item.Drinks;
import model.item.Item;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/*
    Represents a menu containing a list of baked goods and drinks items
 */

public class MyMenu {

    // Combine into one list
//    private ArrayList<Drinks> drinks;
//    private ArrayList<BakedGoods> bakedGoods;
    private ArrayList<Item> items;

    // EFFECTS: construct empty lists of drinks items and baked goods items
    public MyMenu() {
        items = new ArrayList<>();
    }

    // EFFECTS: return the items in the drink section on the menu
    public ArrayList<Drinks> getDrinks() {
        ArrayList<Drinks> ans = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) instanceof Drinks) {
                ans.add((Drinks)items.get(i));
            }
        }
        return ans;
    }

    // EFFECTS: return the items in the baked goods section on the menu
    public ArrayList<BakedGoods> getBakedGoods() {
        ArrayList<BakedGoods> ans = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) instanceof BakedGoods) {
                ans.add((BakedGoods)items.get(i));
            }
        }
        return ans;
    }

    // MODIFIES: this
    // EFFECTS: add an item of category 'drinks' on the menu if the item is not on the menu.
    //         otherwise silently returns
    public void addDrinks(Drinks i) {
        if (!items.contains(i)) {
            items.add(i);
        }
    }

    // MODIFIES: this
    // EFFECTS: add an item on the 'baked goods' category of the menu if the item is not on the menu,
    //        otherwise silently returns
    public void addBakedGoods(BakedGoods i) {
        if (!items.contains(i)) {
            items.add(i);
        }
    }

    // MODIFIES: this
    // EFFECTS: remove the drink item from the menu
    public void removeDrinks(Drinks i) {
        items.remove(i);
    }

    // MODIFIES: this
    // EFFECTS: remove the baked goods item from the menu
    public void removeBakedGoods(BakedGoods i) {
        items.remove(i);
    }

    // EFFECTS: returns number of drink items
    public int getNumberOfDrinks() {
        int ans = 0;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) instanceof Drinks) {
                ans++;
            }
        }
        return ans;
    }

    // EFFECTS: returns number of baked good items
    public int getNumberOfBakedGoods() {
        int ans = 0;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) instanceof BakedGoods) {
                ans++;
            }
        }
        return ans;
    }

    // MODIFIES: this
    // EFFECTS: returns lists of items on menu as JSON format
    public JSONObject toJson() {
        JSONArray jsonMenu = new JSONArray();
        for (Item i: items) {
            if (i instanceof BakedGoods) {
                jsonMenu.put(((BakedGoods) i).toJson());
            }
            if (i instanceof Drinks) {
                jsonMenu.put(((Drinks) i).toJson());
            }
        }
        JSONObject json = new JSONObject();
        json.put("items", jsonMenu);
        return json;
    }
}
