package com.dungeontiger.alamirsassistant;

import java.util.List;

public class Table extends BaseTableResult {
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

    public List<ResponseResult> roll() {
        Integer rolledValue = dice.roll(roll);
        for (TableEntry entry : entries) {
            if (entry.matches(rolledValue)) {
                try {
                    return entry.getResults();
                } catch (Exception e) {
                    throw new RuntimeException("Failed to roll in table " + name + ".", e);
                }
            }
        }
        throw new RuntimeException("Did not find an entry. Rolled " + rolledValue.toString() + " against table " + id);
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
