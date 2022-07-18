package com.dungeontiger.alamirsassistant;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.mockito.stubbing.Answer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TableTest {

    @Mock
    Dice dice;

    @Test
    public void testSimpleEncounter() {
        ResourceTableManager tableManager = new ResourceTableManager(new Dice(2000), new NLG());
        Table table = tableManager.getTable("TombOfAnnihilation", "Cache");
        List<ResponseResult> results = table.roll();
        assertEquals(1, results.size());
    }

    @Test
    public void testSubTables1() {
        when(dice.roll(anyString())).thenReturn(1);
        ResourceTableManager tableManager = new ResourceTableManager(dice, new NLG(dice));
        Table table = tableManager.getTable("test", "test_table_ref");
        List<ResponseResult> results = table.roll();
        assertTrue(results.size() == 1);
        ResponseResult result = results.get(0);
        assertEquals("test", result.getTitle());
    }

    @Test
    public void testSubTables2() {
        when(dice.roll(anyString())).thenReturn(2);
        ResourceTableManager tableManager = new ResourceTableManager(dice, new NLG(dice));
        Table table = tableManager.getTable("test", "test_table_ref");
        List<ResponseResult> results = table.roll();
        assertTrue(results.size() == 1);
        ResponseResult result = results.get(0);
        assertEquals("2 Lesser Half", result.getTitle());
    }

    @Test
    public void testComplexText() {
        when(dice.roll(anyString())).thenReturn(1);
        ResourceTableManager tableManager = new ResourceTableManager(dice, new NLG(dice));
        Table table = tableManager.getTable("test", "test_complex_result");
        ResponseResult result = table.roll().get(0);
        assertEquals("Test1", result.getTitle());
    }

    @Test
    public void testComplexTextAndRoll() {
        when(dice.roll("1d5")).thenReturn(2);
        when(dice.roll("1d6")).thenReturn(3);
        ResourceTableManager tableManager = new ResourceTableManager(dice, new NLG(dice));
        Table table = tableManager.getTable("test", "test_complex_result");
        ResponseResult result = table.roll().get(0);
        assertEquals("Test3", result.getTitle());
    }

    @Test
    public void testComplexTextAndMonsetr() {
        when(dice.roll("1d5")).thenReturn(3);
        when(dice.roll("1d6")).thenReturn(3);
        ResourceTableManager tableManager = new ResourceTableManager(dice, new NLG(dice));
        Table table = tableManager.getTable("test", "test_complex_result");
        ResponseResult result = table.roll().get(0);
        assertEquals("Test 3 Beholders", result.getTitle());
    }

    @Test
    public void testText() {
        when(dice.roll("1d4")).thenReturn(3);
        ResourceTableManager tableManager = new ResourceTableManager(dice, new NLG(dice));
        Table table = tableManager.getTable("test", "test");
        ResponseResult result = table.roll().get(0);
        assertEquals("This is a very nice long note. What do you think? Maybe I should send them a Lich.", result.getText());
    }

    @Test
    public void testGreaterUndeadEncounters() {
        testEncounterTable("TombOfAnnihilation", "GreaterUndeadJungleEncounters");
    }

    @Test
    public void testBeachEncounters() {
        testEncounterTable("TombOfAnnihilation", "BeachEncounters");
    }

    @Test
    public void testMultipleResults() {
        ResourceTableManager tableManager = new ResourceTableManager(new Dice(), new NLG(dice));
        Table table = tableManager.getTable("TombOfAnnihilation", "DeadExplorer");
        List<ResponseResult> results = table.roll();
        assertEquals(2, results.size());
    }

    @Test
    public void testMultiplyRoll() {
        when(dice.roll("1d6")).thenReturn(6);
        ResourceTableManager tableManager = new ResourceTableManager(dice, new NLG(dice));
        Table table = tableManager.getTable("test", "test_complex_result");
        List<ResponseResult> results = table.roll();
        assertEquals(1, results.size());
        assertEquals("6000", results.get(0).getTitle());
    }

    private void testEncounterTable(String campaignName, String tableName) {
        // we want to test all encounters in this table
        // to do that we always want to roll an encounter, therefore 1d20 is an 18
        // then for the 1d100 we want to iterate through them
        // but for all other rolls we want to just roll
        ResourceTableManager tableManager = new ResourceTableManager(dice, new NLG());
        Table table = tableManager.getTable(campaignName, tableName);
        for (int i = 1; i <= 100; i++) {
            int finalI = i;
            when(dice.roll(anyString())).thenAnswer(
                new Answer() {
                    Dice realDice = new Dice();
                    public Object answer(InvocationOnMock invocation) {
                        String dice = invocation.getArgument(0);
                        if (dice.compareTo("1d20") == 0) {
                            return 18;
                        } else if (dice.compareTo("1d100") == 0) {
                            return finalI;
                        } else if (dice.isEmpty()) {
                            return 0;
                        }
                        return realDice.roll(dice);
                    }
                }
            );
            when(dice.roll(anyInt(), anyInt(), anyInt())).thenAnswer(
              new Answer() {
                  Dice realDice = new Dice();
                  public Object answer(InvocationOnMock invocation) {
                      int dice = invocation.getArgument(0);
                      int sides = invocation.getArgument(1);
                      int modifier = invocation.getArgument(2);
                      return realDice.roll(dice,sides,modifier);
                  }
              }
            );
            List<ResponseResult> results = table.roll();
            assertNotNull(results.get(0));
        }
    }
}

