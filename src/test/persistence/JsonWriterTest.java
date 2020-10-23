package persistence;

import model.MyMenu;
import model.item.BakedGoods;
import model.item.Drinks;
import model.item.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {
    JsonWriter jsonWriter;
    JsonReader jsonReader;
    MyMenu menu;

    @BeforeEach
    public void setUp() {
        menu = new MyMenu();
    }

    @Test
    public void testInvalidDirectory() {
        try {
            jsonWriter = new JsonWriter("./data\0MyMenu.json");
            jsonWriter.write(menu);
            fail("IO exception should have been caught");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    public void testWriteNewEmptyFile() {
        try {
            jsonWriter = new JsonWriter("./data/NewEmptyMenu.json");
            jsonWriter.write(menu);

            jsonReader = new JsonReader("./data/NewEmptyMenu.json");
            assertEquals(0, menu.getItems().size());
        } catch (FileNotFoundException e) {
            fail("Exception should not have been caught");
        }
    }

    @Test
    public void testWriteFileOwnerInterface() {
        try {
            jsonWriter = new JsonWriter("./data/NewMenu.json");
            BakedGoods bakedGoods = new BakedGoods("Cheesecake", 2.5, 50);
            Drinks drinks = new Drinks("Iced Tea", 5, 100);

            // add items and save file
            menu.addItem(bakedGoods);
            menu.addItem(drinks);
            jsonWriter.write(menu);

            // read file
            jsonReader = new JsonReader("./data/NewMenu.json");
            menu = jsonReader.read();
            assertEquals(2, menu.getItems().size());
            checkObject("Cheesecake", 2.5, 50, menu.getItems().get(0));
            checkObject("Iced Tea", 5, 100, menu.getItems().get(1));
            assertTrue(menu.getItems().get(0) instanceof BakedGoods);
            assertTrue(menu.getItems().get(1) instanceof Drinks);
        } catch (IOException e) {
            fail("Exception should not have been caught");
        }
    }

    // The method to check the attributes of the object on the menu
    // was implemented from the following source
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    private void checkObject(String name, double price, int quantity, Item i) {
        assertEquals(name, i.getName());
        assertEquals(price, i.getPrice());
        assertEquals(quantity, i.getQuantity());
    }
}
