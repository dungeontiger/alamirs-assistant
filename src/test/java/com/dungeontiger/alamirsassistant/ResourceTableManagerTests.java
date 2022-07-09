package com.dungeontiger.alamirsassistant;

import org.apache.wink.json4j.JSONException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResourceTableManagerTests {
    @Test
    public void simpleTest() {
        ResourceTableManager tableMgr = new ResourceTableManager(new Dice());
        assertEquals(1, tableMgr.getCampaigns().size());
    }

    @Test
    public void testCampaign() {
        ResourceTableManager tableMgr = new ResourceTableManager(new Dice());
        assertEquals("toa", tableMgr.getCampaigns().get(0));
    }

    @Test
    public void testTable() {
        ResourceTableManager tableMgr = new ResourceTableManager(new Dice());
        Table table = tableMgr.getTable("toa", "chult_weather");
        assertEquals("chult_weather", table.getId());
        assertEquals("Chult Weather", table.getName());
        assertEquals("1d20", table.getRoll());
    }
}
