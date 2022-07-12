package com.dungeontiger.alamirsassistant;

import org.apache.wink.json4j.JSONException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ResourceTableManagerTests {
    @Test
    public void simpleTest() {
        ResourceTableManager tableMgr = new ResourceTableManager(new Dice());
        assertEquals(2, tableMgr.getCampaigns().size());
    }

    @Test
    public void testCampaign() {
        ResourceTableManager tableMgr = new ResourceTableManager(new Dice());
        assertTrue(tableMgr.getCampaigns().contains("toa"));
    }

    @Test
    public void testTable() {
        ResourceTableManager tableMgr = new ResourceTableManager(new Dice());
        Table table = tableMgr.getTable("toa", "chult_weather");
        assertEquals("chult_weather", table.getId());
        assertEquals("Chult Weather", table.getName());
        assertEquals("1d20", table.getRoll());
    }

    @Test
    public void testTableNames() {
        ResourceTableManager tableManager = new ResourceTableManager(new Dice());
        List<String> tables = tableManager.getTableNames("toa");
        assertFalse(tables.contains("test"));
    }
}
