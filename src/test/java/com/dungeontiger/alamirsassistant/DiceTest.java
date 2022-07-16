package com.dungeontiger.alamirsassistant;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiceTest {

    @Test
    public void testSimpleRoll() {
        Dice dice = new Dice(1000);
        assertEquals(8, dice.roll(1, 10, 0));
    }

    @Test
    public void testFullRoll() {
        Dice dice = new Dice(1000);
        assertEquals(12, dice.roll(2, 10, -2));
    }

    @Test
    public void testString() {
        Dice dice = new Dice(1000);
        assertEquals(12, dice.roll("2d10-2"));
    }

    @Test
    public void testStringNoMod() {
        Dice dice = new Dice(1000);
        assertEquals(8, dice.roll("1d10"));
    }

    @Test
    public void testMin() {
        Dice dice = new Dice();
        assertEquals(35, dice.min("5d100 + 30"));
    }

    @Test
    public void testMax() {
        Dice dice = new Dice();
        assertEquals(105, dice.max("5d20 + 5"));
    }
}
