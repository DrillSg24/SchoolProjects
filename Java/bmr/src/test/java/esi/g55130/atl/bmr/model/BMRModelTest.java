package esi.g55130.atl.bmr.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BMRModelTest {

    private BMRModel model, model1, model2;

    @BeforeEach
    void setUp() {
        model = new BMRModel();
        model1 = new BMRModel();
        model2 = new BMRModel();
        model.buildPersonModel(170, 80, 25, LifeStyle.EXTREMELY_ACTIVE, Sex.MALE);
        model1.buildPersonModel(10, 10, 100, LifeStyle.ACTIVE, Sex.MALE);
        model2.buildPersonModel(150, 50, 25, LifeStyle.ACTIVE, Sex.FEMALE);
    }

    @Test
    void getBMR_Success() {
        double expected = 1287.5;
        assertEquals(expected, model2.getBMR());
    }

    @Test
    void getBMR_SuccessNegative() {
        double expected = -427;
        assertEquals(expected, model1.getBMR());
    }

    @Test
    void getCalories_negativeBMR() {
        assertThrows(IllegalStateException.class, () -> {
            model1.getCalories();
        });
    }

    @Test
    void getCalories_Success() {
        double expected = model.getBMR() * 1.9;
        assertEquals(expected, model.getCalories());
    }
}