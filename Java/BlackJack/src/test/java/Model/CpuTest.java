/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author tazi7_ukc
 */
public class CpuTest {

    private Cpu cpu;

    @BeforeEach
    public void setUp() {
        cpu = new Cpu(5000);
    }

    @Test
    public void testConstructor_NegativeOrNullScore() {
        System.out.println("Test constructing CPu with negative or null score");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            cpu = new Cpu(0);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            cpu = new Cpu(-5);
        });
    }

    @Test
    public void testIsUnderLimit_true() {
        System.out.println("Test Cpu under 17");
        cpu.addToCurrentTotal(15);
        Assertions.assertTrue(cpu.isUnderLimit());
    }

    @Test
    public void testIsUnderLimit_false() {
        System.out.println("Test cpu over 17");
        cpu.addToCurrentTotal(18);
        Assertions.assertFalse(cpu.isUnderLimit());
    }

}
