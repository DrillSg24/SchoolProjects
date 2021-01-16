/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author tazi7_ukc
 */
public class LineTest {

    Line l, l1, l2;

    public LineTest() {
    }

    @BeforeEach
    public void setUp() {
        l = new Line('o', 10, 10, 20, 20);
        l1 = new Line('o', 0, 0, 10, 10);
    }

    @Test
    public void testIsInside() {
        assertFalse(l.isInside(new Point(0, 0)));
        assertTrue(l.isInside(new Point(15, 15)));
    }

    @Test
    public void testMove() {
        l.move(-10, -10);
        assertTrue(l.equals(l1));
    }

    @Test
    public void testZoomBy() {
        assertThrows(UnsupportedOperationException.class, () -> {
            l.zoomBy(2);
        });
    }

}
