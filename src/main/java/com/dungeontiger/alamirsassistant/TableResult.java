package com.dungeontiger.alamirsassistant;

import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper=false)
public class TableResult extends BaseTableResult {
    private List<ICompositeListItem> titleList = new ArrayList<>();
    private String reference;
    private List<ICompositeListItem> textList = new ArrayList<>();

    public TableResult(String title, List<ICompositeListItem> titleList, String text, List<ICompositeListItem> textList, String reference) {
        if (title != null)  {
            this.titleList.add(new TextCompositeListItem(title));
        }
        if (titleList != null) {
            this.titleList.addAll(titleList);
        }
        if (text != null) {
            this.textList.add(new TextCompositeListItem(text));
        }
        if (textList != null) {
            this.textList.addAll(textList);
        }
        this.reference = reference;
    }

    public String getTitle(NLG nlg) {
        return nlg.processList(titleList);
    }

    public String getText(NLG nlg) {
        return nlg.processList(textList);
    }

    public String getReference() {
        return reference;
    }
}

