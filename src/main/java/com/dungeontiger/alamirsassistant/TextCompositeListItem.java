package com.dungeontiger.alamirsassistant;

public class TextCompositeListItem implements ICompositeListItem {
    private String text;
    public TextCompositeListItem(String text) {
        this.text = text;
    }
    public CompositeListItemResult getResult() {
        return new CompositeListItemResult(text);
    }
}
