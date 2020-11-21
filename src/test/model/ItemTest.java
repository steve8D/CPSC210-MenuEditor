package model;

import model.exception.NegativeInputException;
import model.item.BakedGoods;
import model.item.Drinks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    Drinks coffee;
    BakedGoods tiramisu;

    @BeforeEach
    public void setUp() {
        coffee = new Drinks("Iced Coffee", 2.5, 50);
        tiramisu = new BakedGoods("Tiramisu", 1, 50);
    }

    @Test
    public void testConstructorDrinks() {
        assertEquals(2.5, coffee.getPrice());
        assertEquals("Iced Coffee", coffee.getName());
        assertEquals(50, coffee.getQuantity());
    }

    @Test
    public void testConstructorBakedGoods() {
        assertEquals(1, tiramisu.getPrice());
        assertEquals("Tiramisu", tiramisu.getName());
        assertEquals(50, tiramisu.getQuantity());
    }


    @Test
    public void testConstructorNegative() {
        // set price to negative
        coffee = new Drinks("Cappucino", -1, 20);
        assertEquals(0, coffee.getPrice());

        coffee = new Drinks("American", 5, -20);
        assertEquals(0, coffee.getQuantity());
    }

    @Test
    public void testSetPrice() {
        try {
            coffee.setPrice(1);
        } catch (NegativeInputException e) {
            fail("Exception should not have thrown");
        }
        assertEquals(1, coffee.getPrice());
    }

    @Test
    public void testSetPriceNegative() {
        // Setting the price to a negative number should not change the original price
        try {
            coffee.setPrice(-1);
            fail("Exception should have thrown.");
        } catch (NegativeInputException e) {
            // pass
        }
        assertEquals(2.5, coffee.getPrice());
    }

    @Test
    public void testSetQuantity() {
        try {
            coffee.setQuantity(75);
        } catch (NegativeInputException e) {
            fail("Exception should not have thrown");
        }
        assertEquals(75, coffee.getQuantity());
    }

    @Test
    public void testSetQuantityNegative() {
        // Setting the quantity to a negative number should not change the item's quantity
        try {
            coffee.setQuantity(-1);
            fail("Exception should have thrown");
        } catch (NegativeInputException e) {
            // pass
        }
        assertEquals(50, coffee.getQuantity());
    }

    @Test
    public void testIsOutOfStock() {
        assertFalse(coffee.isOutOfStock());

        // set quantity to 0
        try {
            coffee.setQuantity(0);
        } catch (NegativeInputException e) {
            // ignore
        }
        assertTrue(coffee.isOutOfStock());
    }
}