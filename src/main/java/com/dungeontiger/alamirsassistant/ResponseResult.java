package com.dungeontiger.alamirsassistant;

import lombok.Data;

@Data
public class ResponseResult {
    private String title;
    private String reference;
    private String text;

    public ResponseResult(String title, String text, String reference) {
        this.title = title;
        this.text = text;
        this.reference = reference;
    }
}
