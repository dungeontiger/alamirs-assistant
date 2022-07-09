package com.dungeontiger.alamirsassistant;

import java.util.ArrayList;
import java.util.List;

public class TableEntry {
    private int min;
    private int max;
    private List<BaseTableResult> results;

    public TableEntry(int min, int max, List<BaseTableResult> results) {
        this.min = min;
        this.max = max;
        this.results = results;
    }

    boolean matches(int value) {
        return value >= min && value <= max;
    }

    List<BaseTableResult> getResults() {
        List<BaseTableResult> returns = new ArrayList<>();
        for (BaseTableResult result : results) {
            if (result instanceof TableResult) {
                returns.add(result);
            } else if (result instanceof TableReferenceResult) {
                TableReferenceResult tableRef = (TableReferenceResult) result;
                returns.addAll(tableRef.roll());
            }
        }
        return returns;
    }
}
