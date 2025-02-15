package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWeapon {

    @Test
    void testGetName() {
        Weapon w = new Weapon("foo", 20);
        assertEquals("foo", w.getName());
    }
}
