package com.dungeontiger.alamirsassistant;

public class RollCompositeListItem implements ICompositeListItem{
    private String roll;
    private Dice dice;
    private Integer multiply;

    public RollCompositeListItem(String roll, Integer multiply, Dice dice) {
        this.roll = roll;
        this.dice = dice;
        if (multiply == null) {
            this.multiply = 1;
        } else {
            this.multiply = multiply;
        }
    }

    @Override
    public CompositeListItemResult getResult() {
        Integer value = dice.roll(roll) * multiply;
        return new CompositeListItemResult(value.toString());
    }
}
