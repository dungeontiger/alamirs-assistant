package com.dungeontiger.alamirsassistant;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class TableResult extends BaseTableResult {
    String title;
    String reference;
    String text;

    public TableResult(String title, String text, String reference) {
        this.title = title;
        this.text = text;
        this.reference = reference;
    }
}

