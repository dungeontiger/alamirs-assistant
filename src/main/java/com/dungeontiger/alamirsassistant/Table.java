package com.dungeontiger.alamirsassistant;

import java.util.List;

public class Table {
    private String name;
    private String id;
    private String roll;
    private String description;
    private List<TableEntry> entries;
    private Dice dice;

    public Table(Dice dice, String id, String name, String description, String roll, List<TableEntry> entries) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.roll = roll;
        this.entries = entries;
        this.dice = dice;
    }

    public List<BaseTableResult> roll() {
        Integer rolledValue = dice.roll(roll);
        for (TableEntry entry : entries) {
            if (entry.matches(rolledValue)) {
                return entry.getResults();
            }
        }
        throw new RuntimeException("Did not find an entry. Rolled " + rolledValue.toString() + " against talbe " + id);
    }

    String getName() {
        return name;
    }

    String getId() {
        return id;
    }

    String getRoll() {
        return roll;
    }

    String getDescription() {
        return description;
    }
}
