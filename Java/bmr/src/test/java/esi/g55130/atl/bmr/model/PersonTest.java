package esi.g55130.atl.bmr.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class PersonTest {
    private Person person1, person2, person3;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        Person person_default = new Person();
    }

    @Test
    void constructor_IllegalParams() {
        assertThrows(IllegalArgumentException.class, () -> {
            person1 = new Person(10, 10, 10,null,Sex.FEMALE);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            person1 = new Person(10, 10, 10,LifeStyle.ACTIVE,null);
        });
        assertThrows(IllegalStateException.class, () -> {
            person1 = new Person(0, 10, 10,LifeStyle.ACTIVE,Sex.FEMALE);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            person1 = new Person(10, -10, 10,LifeStyle.ACTIVE,Sex.FEMALE);
        });
    }

}