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
public class CircleTest {

    private Circle c, c1, c2;

    public CircleTest() {
    }

    @BeforeEach
    public void setUp() {
        c = new Circle(new Point(10, 10), 5, 'o');
        c1 = new Circle(new Point(5, 5), 5, 'o');
        c2 = new Circle(new Point(10, 10), 10, 'o');

    }

    @Test
    public void testConstructor_Exception(){
        assertThrows(IllegalArgumentException.class,()->{
            c = new Circle(new Point(10,10),-5,'o');
        });
    }
    @Test
    public void testIsInside() {
        assertTrue(c.isInside(new Point(12, 12)));
        assertFalse(c.isInside(new Point(0, 0)));
    }

    @Test
    public void testMove() {
        c1.move(5, 5);
        assertTrue(c1.equals(c));
    }

    @Test
    public void testZoomBy() {
        c2.zoomBy(0.5);
        assertTrue(c2.equals(c));
    }

    @Test
    public void testZoomBy_exception() {
        assertThrows(IllegalArgumentException.class, () -> {
            c.zoomBy(-1);
        });
    }

}
