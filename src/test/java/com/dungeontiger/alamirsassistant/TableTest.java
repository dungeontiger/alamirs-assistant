package com.dungeontiger.alamirsassistant;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
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
}

