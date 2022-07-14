package com.dungeontiger.alamirsassistant;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ResourceTableManagerTests {
    @Test
    public void simpleTest() {
        ResourceTableManager tableMgr = new ResourceTableManager(new Dice(), new NLG());
        assertEquals(2, tableMgr.getCampaigns().size());
    }

    @Test
    public void testCampaign() {
        ResourceTableManager tableMgr = new ResourceTableManager(new Dice(), new NLG());
        assertTrue(tableMgr.getCampaigns().contains("TombOfAnnihilation"));
    }

    @Test
    public void testTable() {
        ResourceTableManager tableMgr = new ResourceTableManager(new Dice(), new NLG());
        Table table = tableMgr.getTable("TombOfAnnihilation", "Weather");
        assertEquals("Weather", table.getId());
        assertEquals("Chult Weather", table.getName());
        assertEquals("1d20", table.getRoll());
    }

    @Test
    public void testTableNames() {
        ResourceTableManager tableManager = new ResourceTableManager(new Dice(), new NLG());
        List<String> tablesNames = tableManager.getTableNames("TombOfAnnihilation");
        assertFalse(tablesNames.contains("test"));
    }

    @Test
    public void testGetTables() {
        ResourceTableManager tableManager = new ResourceTableManager(new Dice(), new NLG());
        List<Table> tables = tableManager.getTables("test");
        assertEquals("test", tables.get(0).getId());
        assertEquals("Test a bunch of results.", tables.get(0).getDescription());
    }
}
