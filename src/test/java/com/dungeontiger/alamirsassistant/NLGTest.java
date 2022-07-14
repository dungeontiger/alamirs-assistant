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
}
