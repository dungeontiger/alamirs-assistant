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
        List<BaseTableResult> results = table.roll();
        assertEquals(1, results.size());
    }

    @Test
    public void testSubTables1() {
        when(dice.roll(anyString())).thenReturn(1);
        ResourceTableManager tableManager = new ResourceTableManager(dice, new NLG(dice));
        Table table = tableManager.getTable("test", "test_table_ref");
        List<BaseTableResult> results = table.roll();
        assertTrue(results.size() == 1);
        BaseTableResult result = results.get(0);
        assertTrue(result instanceof TableResult);
        TableResult tableResult = (TableResult) result;
        assertEquals("test", tableResult.getText());
    }

    @Test
    public void testSubTables2() {
        when(dice.roll(anyString())).thenReturn(2);
        ResourceTableManager tableManager = new ResourceTableManager(dice, new NLG(dice));
        Table table = tableManager.getTable("test", "test_table_ref");
        List<BaseTableResult> results = table.roll();
        assertTrue(results.size() == 1);
        BaseTableResult result = results.get(0);
        assertTrue(result instanceof TableResult);
        TableResult tableResult = (TableResult) result;
        assertEquals("2 Lesser Half", tableResult.getText());
    }
}

