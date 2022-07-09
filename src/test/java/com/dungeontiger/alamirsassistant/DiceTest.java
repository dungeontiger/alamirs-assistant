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
    public void testReplace() {
        Dice dice = new Dice(1000);
        String replaced = dice.replaceRolls("$roll(1d4+1) Aarakocras");
        assertEquals("4 Aarakocras", replaced);
    }

    @Test
    public void testTwoReplace() {
        Dice dice = new Dice(1000);
        String replaced = dice.replaceRolls("$roll(1d6) guards and $roll(1d6) skeletons");
        assertEquals("2 guards and 2 skeletons", replaced);
    }
}
