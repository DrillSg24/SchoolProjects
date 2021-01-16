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
public class RectangleTest {

    private Rectangle r, r1, r2;

    public RectangleTest() {
    }

    @BeforeEach
    public void setUp() {
        r = new Rectangle(new Point(10, 10), 15, 20, 'r');
        r1 = new Rectangle(new Point(5, 5), 15, 20, 'r');
        r2 = new Rectangle(new Point(10, 10), 3, 4, 'r');
    }

    @Test
    public void testConstructor_Exceptions() {
        assertThrows(IllegalArgumentException.class, () -> {
            r = new Rectangle(new Point(10, 10), -15, 20, 'r');
        });
        assertThrows(IllegalArgumentException.class, () -> {
            r = new Rectangle(new Point(10, 10), 15, -20, 'r');
        });
    }

    @Test
    public void testIsInside() {
        assertTrue(r.isInside(new Point(15, 15)));
        assertFalse(r.isInside(new Point(0, 0)));
    }

    @Test
    public void testMove() {
        r1.move(5, 5);
        assertEquals(r, r1);
    }

    @Test
    public void testZoomBy() {
        r2.zoomBy(5);
        assertEquals(r, r2);
    }

}
