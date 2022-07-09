package com.dungeontiger.alamirsassistant;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dice {

    private Random rand = new Random();
    private Pattern dicePattern = Pattern.compile("(\\d*)[D|d](\\d*)([+|-]?\\d*)?");

    public Dice() {
    }

    public Dice(long seed) {
        rand.setSeed(seed);
    }

    public int roll(int dice, int sides, int modifier) {
        int result = 0;
        for (int i = 0; i < dice; i++) {
            result += rand.nextInt(sides) + 1 ;
        }
        result += modifier;
        return result;
    }

    public int roll(String diceString) {
        int modifier = 0;
        Matcher m = dicePattern.matcher(diceString.replaceAll("\\s+",""));
        if (!m.find()) {
            throw new IllegalArgumentException("Cannot parse dice string: " + diceString);
        }
        int dice = Integer.parseInt(m.group(1));
        int sides = Integer.parseInt(m.group(2));
        if (m.groupCount() == 3 && !m.group(3).isEmpty()) {
            modifier = Integer.parseInt(m.group(3));
        }
        return roll(dice, sides, modifier);
    }

    public String replaceRolls(String text) {
        String result = text;
        int index = result.indexOf("$roll");
        while (index != -1) {
            int startIndex = result.indexOf("(", index);
            int endIndex = result.indexOf(")", startIndex);
            String roll = result.substring(startIndex + 1, endIndex);
            String value = ((Integer) roll(roll)).toString();
            result = result.substring(0,  index) + value + result.substring(endIndex + 1);
            index = result.indexOf("$roll");
        }
        return result;
    }
}
