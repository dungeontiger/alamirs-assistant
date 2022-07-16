package com.dungeontiger.alamirsassistant;

import java.util.ArrayList;
import java.util.List;

public class TableEntry {
    private int min;
    private int max;
    private List<BaseTableResult> results;
    private NLG nlg;

    public TableEntry(NLG nlg, int min, int max, List<BaseTableResult> results) {
        this.min = min;
        this.max = max;
        this.results = results;
        this.nlg = nlg;
    }

    boolean matches(int value) {
        return value >= min && value <= max;
    }

    List<ResponseResult> getResults() {
        List<ResponseResult> returns = new ArrayList<>();
        for (BaseTableResult result : results) {
            if (result instanceof Table) {
                Table table = (Table)result;
                returns.addAll(table.roll());
           } else if (result instanceof TableResult) {
                TableResult tableResult = (TableResult) result;
                returns.add(new ResponseResult(tableResult.getTitle(nlg),
                        tableResult.getText(nlg),
                        tableResult.getReference(),
                        tableResult.getMonsters()));
            }
        }
        if (returns.size() == 0) {
            throw new RuntimeException("No result found for this table entry. min=" + min + " max=" + max);
        }
        return returns;
    }
}
