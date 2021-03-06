package com.dungeontiger.alamirsassistant;

import java.util.List;

public interface ITableManager {
    List<String> getCampaigns();
    List<Table> getTables(String campaign);
    List<String> getTableNames(String campaign);
    Table getTable(String campaign, String table);
}
