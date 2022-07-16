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

    public MonsterStats(String name, String pluralForm, int initiative, int AC, List<HP> HPs, String reference) {
        this.name = name;
        this.pluralForm = pluralForm;
        this.initiative = initiative;
        this.AC = AC;
        this.HPs = HPs;
        this.reference = reference;
    }
}
