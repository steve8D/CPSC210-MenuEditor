package model;

import model.item.BakedGoods;
import model.item.Drinks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyMenuTest {
    MyMenu menu;
    BakedGoods croissant;
    Drinks brownSugarMilkTea;
    BakedGoods swissRollCake;
    Drinks blackCoffee;

    @BeforeEach
    public void setUp() {
        menu = new MyMenu();
        croissant = new BakedGoods("Croissant", 0.5, 20);
        brownSugarMilkTea = new Drinks("Brown Sugar Milk Tea", 4.5, 50);
        swissRollCake = new BakedGoods("Swiss Roll Cake", 4, 10);
        blackCoffee = new Drinks("Iced Black Coffee", 2, 100);
    }

    @Test
    public void testConstructor() {
        assertEquals(0, menu.getNumberOfBakedGoods());
        assertEquals(0, menu.getNumberOfDrinks());
    }

    @Test
    public void testAddItems() {
        menu.addItem(brownSugarMilkTea);
        menu.addItem(blackCoffee);
        menu.addItem(croissant);
        menu.addItem(swissRollCake);
        assertTrue(menu.getDrinks().contains(brownSugarMilkTea));
        assertTrue(menu.getDrinks().contains(blackCoffee));
        assertTrue(menu.getBakedGoods().contains(croissant));
        assertTrue(menu.getBakedGoods().contains(swissRollCake));
        assertEquals(2, menu.getNumberOfDrinks());
        assertEquals(2, menu.getNumberOfBakedGoods());
    }

    @Test
    public void testAddItemsDuplicate() {
        menu.addItem(brownSugarMilkTea);
        menu.addItem(blackCoffee);
        menu.addItem(croissant);
        menu.addItem(swissRollCake);

        // add again
        menu.addItem(brownSugarMilkTea);
        menu.addItem(blackCoffee);
        menu.addItem(croissant);
        menu.addItem(swissRollCake);
        assertEquals(2, menu.getNumberOfDrinks());
        assertEquals(2, menu.getNumberOfBakedGoods());
    }

    @Test
    public void testRemoveOneItem() {
        // add two item and confirms they are both there
        menu.addItem(brownSugarMilkTea);
        assertTrue(menu.getDrinks().contains(brownSugarMilkTea));
        assertEquals(1, menu.getNumberOfDrinks());

        menu.addItem(swissRollCake);
        assertTrue(menu.getBakedGoods().contains(swissRollCake));
        assertEquals(1, menu.getNumberOfBakedGoods());

        // remove only the baked good item and confirms it is not on the menu anymore
        menu.removeItem(swissRollCake);
        assertFalse(menu.getBakedGoods().contains(swissRollCake));
        assertEquals(0, menu.getNumberOfBakedGoods());

        // remove the drink and confirms it is not on the menu anymore
        assertTrue(menu.getDrinks().contains(brownSugarMilkTea));
        assertEquals(1, menu.getNumberOfDrinks());
    }

    @Test
    public void testRemoveTwoItems() {
        // add two item and confirms they are both there
        menu.addItem(brownSugarMilkTea);
        assertTrue(menu.getDrinks().contains(brownSugarMilkTea));
        assertEquals(1, menu.getNumberOfDrinks());

        menu.addItem(swissRollCake);
        assertTrue(menu.getBakedGoods().contains(swissRollCake));
        assertEquals(1, menu.getNumberOfBakedGoods());

        // remove only the baked good item and confirms it is not on the menu anymore
        menu.removeItem(swissRollCake);
        assertFalse(menu.getBakedGoods().contains(swissRollCake));
        assertEquals(0, menu.getNumberOfBakedGoods());

        // remove the drink and confirms it is not on the menu anymore
        menu.removeItem(brownSugarMilkTea);
        assertFalse(menu.getDrinks().contains(brownSugarMilkTea));
        assertEquals(0, menu.getNumberOfDrinks());
    }

    @Test
    public void testDontRemoveItem() {
        // remove an item that is not added to the menu
        assertFalse(menu.getDrinks().contains(blackCoffee));
        menu.removeItem(blackCoffee);

        // remove a second item that is not added to the menu
        assertFalse(menu.getBakedGoods().contains(croissant));
        menu.removeItem(croissant);
    }
}
