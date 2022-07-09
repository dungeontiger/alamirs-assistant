package com.dungeontiger.alamirsassistant;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TableTest {
    @Test
    public void testSimpleEncounter() {
        ResourceTableManager tableManager = new ResourceTableManager(new Dice(2000));
        Table table = tableManager.getTable("toa", "check_encounter");
        List<BaseTableResult> results = table.roll();
        assertEquals(1, results.size());
    }
}

