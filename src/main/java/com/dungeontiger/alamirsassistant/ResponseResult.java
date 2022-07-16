package com.dungeontiger.alamirsassistant;

import lombok.Data;

import java.util.List;

@Data
public class ResponseResult {
    private String title;
    private String reference;
    private String text;
    private List<MonsterStats> monsters;

    public ResponseResult(String title, String text, String reference, List<MonsterStats> monsters) {
        this.title = title;
        this.text = text;
        this.reference = reference;
        this.monsters = monsters;
    }
}
