package model;

import model.item.Drinks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    Drinks coffee;

    @BeforeEach
    public void setUp() {
        coffee = new Drinks("Iced Coffee", 2.5, 50);
    }

    @Test
    public void testConstructor() {
        assertEquals(2.5, coffee.getPrice());
        assertEquals("Iced Coffee", coffee.getName());
        assertEquals(50, coffee.getQuantity());
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
        coffee.setPrice(1);
        assertEquals(1, coffee.getPrice());
    }

    @Test
    public void testSetPriceNegative() {
        // Setting the price to a negative number should not change the original price
        coffee.setPrice(-1);
        assertEquals(2.5, coffee.getPrice());
    }

    @Test
    public void testSetQuantity() {
        coffee.setQuantity(75);
        assertEquals(75, coffee.getQuantity());
    }

    @Test
    public void testSetQuantityNegative() {
        // Setting the quantity to a negative number should not change the item's quantity
        coffee.setQuantity(-1);
        assertEquals(50, coffee.getQuantity());
    }

    @Test
    public void testIsOutOfStock() {
        assertFalse(coffee.isOutOfStock());

        // set quantity to 0
        coffee.setQuantity(0);
        assertTrue(coffee.isOutOfStock());
    }
}