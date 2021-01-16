/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author tazi7_ukc
 */
public class HumanPlayerTest {

    private HumanPlayer human;

    public HumanPlayerTest() {
    }

    @Test
    public void testCOnstructor_nullName() {
        System.out.println("Test constructing human player with null name");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            human = new HumanPlayer(null, 5000);
        });
    }

}
