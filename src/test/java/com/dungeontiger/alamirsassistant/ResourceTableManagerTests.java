package com.dungeontiger.alamirsassistant;

import org.apache.wink.json4j.JSONException;
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
        List<String> tables = tableManager.getTableNames("TombOfAnnihilation");
        assertFalse(tables.contains("test"));
    }
}
