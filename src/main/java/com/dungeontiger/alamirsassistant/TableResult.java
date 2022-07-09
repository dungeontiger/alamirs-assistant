package com.dungeontiger.alamirsassistant;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class TableResult extends BaseTableResult {
    String text;
    String reference;
    String notes;

    public TableResult(String text, String notes, String reference) {
        this.text = text;
        this.notes = notes;
        this.reference = reference;
    }
}

