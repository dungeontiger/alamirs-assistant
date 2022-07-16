package com.dungeontiger.alamirsassistant;

import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper=false)
public class TableResult extends BaseTableResult {
    private List<ICompositeListItem> titleList = new ArrayList<>();
    private String reference;
    private List<ICompositeListItem> textList = new ArrayList<>();
    private NLG nlg;

    public TableResult(String title, List<ICompositeListItem> titleList, String text, List<ICompositeListItem> textList, String reference, NLG nlg) {
        this.nlg = nlg;
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

    public ResponseResult getResponse() {
        List<MonsterStats> monsters = new ArrayList<>();
        String title = processList(titleList, monsters);
        String text = processList(textList, monsters);
        return new ResponseResult(title, text, reference, monsters);
    }

    private String processList(List<ICompositeListItem> items, List<MonsterStats> monsters) {
        String text = "";
        for (ICompositeListItem item : items) {
            CompositeListItemResult itemResult = item.getResult();
            text += nlg.replaceRolls(itemResult.getText());
            if (itemResult.getMonsters() != null) {
                monsters.add(itemResult.getMonsters());
            }
        }
        return text;
    }
}

