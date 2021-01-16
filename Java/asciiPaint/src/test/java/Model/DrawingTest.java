/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author tazi7_ukc
 */
public class DrawingTest {

    private Drawing d;

    public DrawingTest() {
    }

    @BeforeEach
    public void setUp() {
        d = new Drawing();
    }

    @Test
    public void testAddShape() {
        d.addShape(new Circle(new Point(25, 25), 10, 'v'));
        assertFalse(d.getShapes().isEmpty());
    }

    @Test
    public void testRemoveShape() {
        d.addShape(new Circle(new Point(25, 25), 10, 'v'));
        d.removeShape(0);
        assertTrue(d.getShapes().isEmpty());
    }

    @Test
    public void testRemoveShape_Exception() {
        assertThrows(IllegalArgumentException.class, () -> {
            d.removeShape(0);
        });
    }

    @Test
    public void testGetShapeAt() {
        System.out.println("Test no shape to get");
        assertEquals(null, d.getShapeAt(new Point(20, 20)));
        ColoredShape newShape = new Circle(new Point(25, 25), 10, 'o');
        d.addShape(newShape);
        assertEquals(newShape, d.getShapeAt(new Point(20, 20)));
    }

    @Test
    public void testGetShapeByIndex_Exception() {
        assertThrows(IllegalArgumentException.class, () -> {
            d.getShapeByIndex(-1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            d.addShape(new Circle(new Point(25, 25), 10, 'v'));
            d.getShapeByIndex(1);
        });
    }

}
