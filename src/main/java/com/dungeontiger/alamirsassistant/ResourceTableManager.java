package com.dungeontiger.alamirsassistant;

import org.apache.wink.json4j.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResourceTableManager implements ITableManager {
    // campaign maps to a map of table id to table
    private Map<String, Map<String, Table>> tables = new HashMap<>();
    private Dice dice;

    public ResourceTableManager(Dice dice) {
        this.dice = dice;
        try
        {
            String tablesPath = getClass().getClassLoader().getResource("tables").getPath();
            for (File campaignFile : new File(tablesPath).listFiles()) {
                String campaignId = campaignFile.getName();
                for (File tableFile : campaignFile.listFiles()) {
                    String tableId = tableFile.getName().replaceAll(".json", "");
                    Table table = buildTable(tableFile);
                    Map<String, Table> tableMap = tables.get(campaignId);
                    if (tableMap == null) {
                        tableMap = new HashMap<>();
                        tables.put(campaignId, tableMap);
                    }
                    tableMap.put(tableId, table);
                }
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private Table buildTable(File tableFile) throws FileNotFoundException, JSONException {
        JSONObject tableJSON = (JSONObject)JSON.parse(new FileInputStream(tableFile));
        String name = tableJSON.getString("name");
        String id = tableFile.getName().replaceAll(".json", "");
        String description = tableJSON.get("description").toString();
        String roll = tableJSON.get("roll").toString();
        List<TableEntry> entries = new ArrayList<>();
        JSONArray entriesJSON = tableJSON.getJSONArray("entries");
        for (Object e : entriesJSON) {
            JSONObject entry = (JSONObject) e;
            int min = entry.getInt("min");
            int max = entry.getInt("max");
            List<BaseTableResult> results = new ArrayList<>();
            JSONArray resultsJSON = entry.getJSONArray("results");
            for (Object f : resultsJSON) {
                JSONObject resultJSON = (JSONObject) f;
                if (resultJSON.has("tableRef")) {
                    File tableRef = new File(tableFile.getParent() + "/" + resultJSON.getString("tableRef") + ".json");
                    results.add(new TableReferenceResult(buildTable(tableRef)));
                } else {
                    String text = "";
                    if (resultJSON.has("text")) {
                        text = resultJSON.getString("text");
                    }
                    String notes = "";
                    if (resultJSON.has("notes")) {
                        notes = resultJSON.getString("notes");
                    }
                    String reference = "";
                    if (resultJSON.has("reference")) {
                        reference = resultJSON.getString("reference");
                    }
                    TableResult tableResult = new TableResult(text, notes, reference);
                    results.add(tableResult);
                }
            }
            TableEntry tableEntry = new TableEntry(dice, min, max, results);
            entries.add(tableEntry);
        }

        Table table = new Table(dice, id, name, description, roll, entries);
        return table;
    }

    @Override
    public List<String> getCampaigns() {
        return tables.keySet().stream().toList();
    }

    @Override
    public List<Table> getTables(String campaign) {
        return tables.get(campaign).values().stream().toList();
    }

    @Override
    public Table getTable(String campaign, String table) {
        return tables.get(campaign).get(table);
    }

    @Override
    public List<String> getTableNames(String campaignId) {
        return tables.get(campaignId).keySet().stream().toList();
    }
}
