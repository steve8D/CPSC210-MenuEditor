package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    Drinks coffee;
    BakedGoods tiramisu;

    @BeforeEach
    public void setUp() {
        coffee = new Drinks("Iced Coffee", 2.5, 50);
        tiramisu = new BakedGoods("Tiramisu", 2.5, 25);
    }

    @Test
    public void testConstructor() {

    }
}