package com.dungeontiger.alamirsassistant;

public class MonsterCompositeListItem implements ICompositeListItem {
    private String amount;
    private String name;
    private String pluralForm;
    private String HP;
    private int dexModifier;
    private int AC;
    private String reference;
    private Dice dice;

    public MonsterCompositeListItem(String amount, String name, String pluralForm, String HP, int dexModifier, int AC, String reference, Dice dice) {
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
    }

    @Override
    public String getResult() {
        String result = "";
        Integer num = dice.roll(amount);
        result += num + " ";
        if (num == 1) {
            result += name;
        } else {
            result += pluralForm;
        }
        return result;
    }
}
