package persistence;

import model.MyMenu;
import model.item.BakedGoods;
import model.item.Drinks;
import model.item.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {
    JsonReader jsonReader;
    MyMenu menu;

    @BeforeEach
    public void setUp() {
        menu = new MyMenu();
    }

    @Test
    public void testReadInvalidDirectory() {
        try {
            jsonReader = new JsonReader("./data/NonExistentMenu.json");
            jsonReader.read();
            fail("Exception was not thrown");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    public void testReadDirectory() {
        try {
            jsonReader = new JsonReader("./data/testReadMenu.json");
            menu = jsonReader.read();

            assertEquals(3, menu.getItems().size());
            checkObject("gold martini", 6, 100, menu.getItems().get(0));
            checkObject("gold cupcake", 2.5, 50, menu.getItems().get(1));
            checkObject("Milk Tea", 10, 800, menu.getItems().get(2));
            assertTrue(menu.getItems().get(0) instanceof Drinks);
            assertTrue(menu.getItems().get(1) instanceof BakedGoods);
            assertTrue(menu.getItems().get(2) instanceof Drinks);
        } catch (IOException e) {
            fail("Exception should not have thrown");
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
