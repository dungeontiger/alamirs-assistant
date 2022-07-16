package com.dungeontiger.alamirsassistant;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NLGTest {

    @Mock
    Dice dice;

    @Test
    public void testReplace() {
        when(dice.roll(anyString())).thenReturn(3);
        NLG nlg = new NLG(dice);
        String replaced = nlg.replaceRolls("$roll(1d4+1) Aarakocras");
        assertEquals("3 Aarakocras", replaced);
    }

    @Test
    public void testTwoReplace() {
        when(dice.roll("1d6")).thenReturn(2);
        when(dice.roll("1d8")).thenReturn(4);
        NLG nlg = new NLG(dice);
        String replaced = nlg.replaceRolls("$roll(1d6) guards and $roll(1d8) skeletons");
        assertEquals("2 guards and 4 skeletons", replaced);
    }

    @Test
    public void testVerySmallSize1() {
        NLG nlg = new NLG(dice);
        assertEquals("very small", nlg.getRelativeSize(5,0, 100));
    }

    @Test
    public void testSmallSize1() {
        NLG nlg = new NLG(dice);
        assertEquals("small", nlg.getRelativeSize(20,0, 100));
    }

    @Test
    public void testNormalSize1() {
        NLG nlg = new NLG(dice);
        assertEquals("normal", nlg.getRelativeSize(50,0, 100));
    }

    @Test
    public void testLargeSize1() {
        NLG nlg = new NLG(dice);
        assertEquals("large", nlg.getRelativeSize(80,0, 100));
    }

    @Test
    public void testVeryLargeSize1() {
        NLG nlg = new NLG(dice);
        assertEquals("very large", nlg.getRelativeSize(95,0, 100));
    }

    @Test
    public void testVerySmallSize2() {
        NLG nlg = new NLG(dice);
        assertEquals("very small", nlg.getRelativeSize(11,10, 20));
    }

    @Test
    public void testSmallSize2() {
        NLG nlg = new NLG(dice);
        assertEquals("small", nlg.getRelativeSize(12,10, 20));
    }

    @Test
    public void testNormalSize2() {
        NLG nlg = new NLG(dice);
        assertEquals("normal", nlg.getRelativeSize(15,10, 20));
    }

    @Test
    public void testLargeSize2() {
        NLG nlg = new NLG(dice);
        assertEquals("large", nlg.getRelativeSize(19,10, 20));
    }

    @Test
    public void testVeryLargeSize2() {
        NLG nlg = new NLG(dice);
        assertEquals("very large", nlg.getRelativeSize(20,10, 20));
    }
}
