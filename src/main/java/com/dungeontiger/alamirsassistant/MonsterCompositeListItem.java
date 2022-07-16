package com.dungeontiger.alamirsassistant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MonsterCompositeListItem implements ICompositeListItem {
    private String amount;
    private Integer rolledAmount = null;
    private String name;
    private String pluralForm;
    private String HP;
    private int dexModifier;
    private int AC;
    private String reference;
    private Dice dice;
    private NLG nlg;

    public MonsterCompositeListItem(String amount, String name, String pluralForm, String HP, int dexModifier, int AC, String reference, Dice dice, NLG nlg) {
        this.amount = amount;
        this.name = name;
        if (pluralForm != null) {
            this.pluralForm = pluralForm;
        } else {
            this.pluralForm = name + "s";
        }
        this.HP = HP;
        this.dexModifier = dexModifier;
        this.AC = AC;
        this.reference = reference;
        this.dice = dice;
        this.nlg = nlg;
    }

    @Override
    public String getResult() {
        String result = "";
        if (rolledAmount == null) {
            rolledAmount = dice.roll(amount);
        }
        result += rolledAmount + " ";
        if (rolledAmount == 1) {
            result += name;
        } else {
            result += pluralForm;
        }
        return result;
    }

    @Override
    public MonsterStats getMonsters() {
        List<HP> HPs = new ArrayList<>();
        if (rolledAmount == null) {
            rolledAmount = dice.roll(amount);
        }
        for (int i = 0; i < rolledAmount; i++) {
            int rolledHP = dice.roll(HP);
            HPs.add(new HP(rolledHP, nlg.getRelativeSize(rolledHP, dice.min(HP), dice.max(HP))));
        }
        return new MonsterStats(name, pluralForm, dice.roll(1, 20, dexModifier), AC, HPs, reference);
    }
}
