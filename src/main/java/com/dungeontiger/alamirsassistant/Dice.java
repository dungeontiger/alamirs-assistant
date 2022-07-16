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
        DicePieces pieces = parseString(diceString);
        return roll(pieces.dice, pieces.sides, pieces.modifier);
    }

    public int max(int dice, int sides, int modifier) {
        return sides * dice + modifier;
    }

    public int max(String diceString) {
        DicePieces pieces = parseString(diceString);
        return max(pieces.dice, pieces.sides, pieces.modifier);
    }

    public int min(int dice, int sides, int modifier) {
        return dice + modifier;
    }

    public int min(String diceString) {
        DicePieces pieces = parseString(diceString);
        return min(pieces.dice, pieces.sides, pieces.modifier);
    }

    private DicePieces parseString(String diceString) {
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
        return new DicePieces(dice, sides, modifier);
    }
    private class DicePieces {
        public int dice;
        public int sides;
        public int modifier;
        public DicePieces(int dice, int sides, int modifier) {
            this.dice = dice;
            this.sides = sides;
            this.modifier = modifier;
        }
    }
}
