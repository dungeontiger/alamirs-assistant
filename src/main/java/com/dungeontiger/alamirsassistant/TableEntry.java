package com.dungeontiger.alamirsassistant;

import java.util.ArrayList;
import java.util.List;

public class TableEntry {
    private int min;
    private int max;
    private List<BaseTableResult> results;
    private Dice dice;

    public TableEntry(Dice dice, int min, int max, List<BaseTableResult> results) {
        this.min = min;
        this.max = max;
        this.results = results;
        this.dice = dice;
    }

    boolean matches(int value) {
        return value >= min && value <= max;
    }

    List<BaseTableResult> getResults() {
        List<BaseTableResult> returns = new ArrayList<>();
        for (BaseTableResult result : results) {
            if (result instanceof TableResult) {
                returns.add(new TableResult(dice.replaceRolls(((TableResult) result).getText()),
                        dice.replaceRolls(((TableResult) result).getNotes()),
                        ((TableResult) result).getReference()));
            } else if (result instanceof TableReferenceResult) {
                TableReferenceResult tableRef = (TableReferenceResult) result;
                returns.addAll(tableRef.roll());
            }
        }
        return returns;
    }
}
