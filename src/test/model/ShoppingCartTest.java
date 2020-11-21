package model;

import model.exception.NegativeInputException;
import model.item.BakedGoods;
import model.item.Drinks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartTest {
    ShoppingCart checkOut;
    BakedGoods croissant;
    Drinks brownSugarMilkTea;

    @BeforeEach
    public void setUp() {
        checkOut = new ShoppingCart();
        croissant = new BakedGoods("Croissant", 0.5, 50);
        brownSugarMilkTea = new Drinks("Brown Sugar Milk Tea", 5.5, 20);
    }

    @Test
    public void testConstructor() {
        assertEquals(0, checkOut.getNumberOfItems());
    }

    @Test
    public void testAddItem() {
        checkOut.addItem(croissant);
        checkOut.addItem(brownSugarMilkTea);
        checkOut.addItem(brownSugarMilkTea);
        assertEquals(3, checkOut.getNumberOfItems());
        assertTrue(checkOut.getItems().contains(brownSugarMilkTea));
        assertTrue(checkOut.getItems().contains(croissant));
    }

    @Test
    public void testAddItemOutOfStock() {
        // set croissant quantity to 0, the quality was not good!
        try {
            croissant.setQuantity(0);
            brownSugarMilkTea.setQuantity(0);
        } catch (NegativeInputException e) {
            fail("Exception should not have thrown");
        }
        checkOut.addItem(croissant);
        checkOut.addItem(brownSugarMilkTea);
        assertEquals(0, checkOut.getNumberOfItems());
        assertFalse(checkOut.getItems().contains(brownSugarMilkTea));
        assertFalse(checkOut.getItems().contains(croissant));
    }

    @Test
    public void testRemoveItem() {
        // add the items into the cart
        checkOut.addItem(croissant);
        checkOut.addItem(brownSugarMilkTea);
        checkOut.addItem(brownSugarMilkTea);

        // remove the milk tea because customer added one extra by mistake
        checkOut.removeItem(brownSugarMilkTea);
        assertEquals(2, checkOut.getNumberOfItems());
    }

    @Test
    public void testTotalPrice() {
        // add the items into the cart
        checkOut.addItem(croissant);
        checkOut.addItem(brownSugarMilkTea);
        checkOut.addItem(brownSugarMilkTea);

        assertEquals(11.5, checkOut.totalPrice());
        assertEquals(49, croissant.getQuantity());
        assertEquals(18, brownSugarMilkTea.getQuantity());
    }
}
