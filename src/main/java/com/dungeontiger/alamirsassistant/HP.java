package com.dungeontiger.alamirsassistant;

import lombok.Data;

@Data
public class HP {
    private int HP;
    private String relativeSize;

    public HP(int HP, String relativeSize) {
        this.HP = HP;
        this.relativeSize = relativeSize;
    }
}
