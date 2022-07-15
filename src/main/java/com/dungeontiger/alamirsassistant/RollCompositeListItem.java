package com.dungeontiger.alamirsassistant;

public class RollCompositeListItem implements ICompositeListItem{
    private String roll;
    private Dice dice;

    public RollCompositeListItem(String roll, Dice dice) {
        this.roll = roll;
        this.dice = dice;
    }

    @Override
    public String getResult() {
        Integer value = dice.roll(roll);
        return value.toString();
    }
}
