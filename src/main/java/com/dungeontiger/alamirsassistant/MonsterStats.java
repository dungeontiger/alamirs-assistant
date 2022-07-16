package com.dungeontiger.alamirsassistant;

import lombok.Data;

import java.util.List;

@Data
public class MonsterStats {
    private String name;
    private String pluralForm;
    private int initiative;
    private int AC;
    private List<HP> HPs;
    private String reference;
    private int minHP;
    private int avgHP;
    private int maxHP;

    public MonsterStats(String name, String pluralForm, int initiative, int AC, List<HP> HPs, String reference, int minHP, int avgHP, int maxHP) {
        this.name = name;
        this.pluralForm = pluralForm;
        this.initiative = initiative;
        this.AC = AC;
        this.HPs = HPs;
        this.reference = reference;
        this.minHP = minHP;
        this.avgHP = avgHP;
        this.maxHP = maxHP;
    }
}
