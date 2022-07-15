package com.dungeontiger.alamirsassistant;

import java.util.List;

public class NLG {
    private Dice dice;
    public NLG() {
        dice = new Dice();
    }
    public NLG(Dice dice) {
        this.dice = dice;
    }
    public String replaceRolls(String text) {
        String result = text;
        int index = result.indexOf("$roll");
        while (index != -1) {
            int startIndex = result.indexOf("(", index);
            int endIndex = result.indexOf(")", startIndex);
            String roll = result.substring(startIndex + 1, endIndex);
            String value = ((Integer) dice.roll(roll)).toString();
            result = result.substring(0,  index) + value + result.substring(endIndex + 1);
            index = result.indexOf("$roll");
        }
        return result;
    }

    public String processList(List<ICompositeListItem> compositeList) {
        String result = "";
        for (ICompositeListItem item : compositeList) {
            result += replaceRolls(item.getResult());
        }
        return result;
    }
}
