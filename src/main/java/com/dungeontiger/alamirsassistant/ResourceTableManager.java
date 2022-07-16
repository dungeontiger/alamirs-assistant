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
    private NLG nlg;

    public ResourceTableManager(Dice dice, NLG nlg) {
        this.dice = dice;
        this.nlg = nlg;
        try
        {
            String tablesPath = getClass().getClassLoader().getResource("tables").getPath();
            for (File campaignFile : new File(tablesPath).listFiles()) {
                String campaignId = campaignFile.getName();
                for (File tableFile : campaignFile.listFiles()) {
                    String tableId = tableFile.getName().replaceAll(".json", "");
                    JSONObject tableJSON = (JSONObject)JSON.parse(new FileInputStream(tableFile));
                    Table table = buildTable(tableJSON, tableFile.getName().replaceAll(".json", ""), tableFile.getParent());
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

    private Table buildTable(JSONObject tableJSON, String id, String basePath) throws JSONException, FileNotFoundException {
        String name = tableJSON.getString("name");
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
                    // referencing another table file
                    File tableRef = new File(basePath + "/" + resultJSON.getString("tableRef") + ".json");
                    JSONObject tableRefJSON = (JSONObject)JSON.parse(new FileInputStream(tableRef));
                    results.add(buildTable(tableRefJSON, "", basePath));
                } else if (resultJSON.has("table")) {
                    // embedded subtable
                    results.add(buildTable((JSONObject) resultJSON.get("table"), "", basePath));
                } else {
                    String title = "";
                    if (resultJSON.has("title")) {
                        title = resultJSON.getString("title");
                    }
                    List<ICompositeListItem> titleList = null;
                    if (resultJSON.has("titleList")) {
                        titleList = buildList(resultJSON.getJSONArray("titleList"));
                    }
                    String text = "";
                    if (resultJSON.has("text")) {
                        text = resultJSON.getString("text");
                    }
                    List<ICompositeListItem> textList = null;
                    if (resultJSON.has("textList")) {
                        textList = buildList(resultJSON.getJSONArray("textList"));
                    }
                    String reference = "";
                    if (resultJSON.has("reference")) {
                        reference = resultJSON.getString("reference");
                    }
                    // this will include lists too
                    TableResult tableResult = new TableResult(title, titleList, text, textList, reference);
                    results.add(tableResult);
                }
            }
            TableEntry tableEntry = new TableEntry(nlg, min, max, results);
            entries.add(tableEntry);
        }

        Table table = new Table(dice, id, name, description, roll, entries);
        return table;
    }

    private List<ICompositeListItem> buildList(JSONArray items) throws JSONException {
        List<ICompositeListItem> results = new ArrayList<>();
        for (int i = 0; i < items.length(); i++) {
            JSONObject o = items.getJSONObject(i);
            if (o.has("text")) {
                results.add(new TextCompositeListItem(o.getString("text")));
            } else if (o.has("roll")) {
                results.add(new RollCompositeListItem(o.getString("roll"), dice));
            } else if (o.has("monster")) {
                JSONObject m = o.getJSONObject("monster");
                results.add(new MonsterCompositeListItem(
                        m.getString("amount"),
                        m.getString("name"),
                        m.getString("pluralForm"),
                        m.getString("HP"),
                        m.getInt("dexModifier"),
                        m.getInt("AC"),
                        m.getString("reference"),
                        dice,
                        nlg));
            }
        }
        return results;
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
