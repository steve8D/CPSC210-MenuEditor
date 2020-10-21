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
    public void testAddDrinks() {
        menu.addItem(brownSugarMilkTea);
        menu.addItem(blackCoffee);
        menu.addItem(croissant);
        menu.addItem(swissRollCake);
        assertEquals(2, menu.getNumberOfDrinks());
        assertEquals(2, menu.getNumberOfBakedGoods());
    }

    @Test
    public void testaddDrinksDuplicate() {
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
    public void testRemoveDrinks() {
        // add a drink item and confirms it is there
        menu.addItem(brownSugarMilkTea);
        assertTrue(menu.getDrinks().contains(brownSugarMilkTea));
        assertEquals(1, menu.getNumberOfDrinks());

        // remove the drink and confirms it is not on the menu anymore
        menu.removeDrinks(brownSugarMilkTea);
        assertFalse(menu.getDrinks().contains(brownSugarMilkTea));
        assertEquals(0, menu.getNumberOfDrinks());
    }

    @Test
    public void testDontRemoveDrinks() {
        // remove an item that is not added to the menu
        assertFalse(menu.getDrinks().contains(blackCoffee));
        menu.removeDrinks(blackCoffee);
    }

    @Test
    public void testRemoveBakedGoods() {
        menu.addItem(swissRollCake);
        assertTrue(menu.getBakedGoods().contains(swissRollCake));
        assertEquals(1, menu.getNumberOfBakedGoods());

        // remove the baked good item and confirms it is not on the menu anymore
        menu.removeBakedGoods(swissRollCake);
        assertFalse(menu.getBakedGoods().contains(swissRollCake));
        assertEquals(0, menu.getNumberOfBakedGoods());
    }

    @Test
    public void testDontRemoveBakedGoods() {
        // remove an item that is not added to the menu
        assertFalse(menu.getBakedGoods().contains(croissant));
        menu.removeBakedGoods(croissant);
    }
}
