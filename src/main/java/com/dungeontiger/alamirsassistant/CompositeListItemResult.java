package com.dungeontiger.alamirsassistant;

import java.util.Collections;

public class CompositeListItemResult {
    private String textResult;
    private MonsterStats monsters = null;

    public CompositeListItemResult(String textResult, MonsterStats monsters) {
        this.textResult = textResult;
        this.monsters = monsters;
    }

    public CompositeListItemResult(String textResult) {
        this.textResult = textResult;
    }

    public String getText() {
        return textResult;
    }

    public MonsterStats getMonsters() {
        return monsters;
    }
}
