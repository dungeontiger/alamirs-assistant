package com.dungeontiger.alamirsassistant;

import java.util.List;

public class TableReferenceResult extends BaseTableResult {
    private Table table;

    public TableReferenceResult(Table table) {
        this.table = table;
    }

    public List<BaseTableResult> roll() {
        return table.roll();
    }
}
