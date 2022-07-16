package com.dungeontiger.alamirsassistant;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TableResultTest {
    @Mock
    Dice dice;
    NLG nlg = new NLG();

    @Test
    public void testGetMonsters() {
        when(dice.roll("1d4")).thenReturn(2);
        List<ICompositeListItem> titleList = new ArrayList<>();
        List<ICompositeListItem> textList = new ArrayList<>();
        MonsterCompositeListItem orcs = new MonsterCompositeListItem("1d4", "Orc", "Orcs", "2d6+6", 1, 12, "", dice, nlg);
        MonsterCompositeListItem gnolls = new MonsterCompositeListItem("1d6", "Gnoll", "Gnolls", "5d8", 1, 15, "", dice, nlg);
        titleList.add(orcs);
        titleList.add(gnolls);
        MonsterCompositeListItem tabaxi = new MonsterCompositeListItem("2d4", "Tabaxi Hunter", "Tabaxi Hunterss", "9d8", 3, 14, "", dice, nlg);
        textList.add(tabaxi);
        TableResult result = new TableResult("", titleList, "",  textList, "");
        List<MonsterStats> monsterStats = result.getMonsters();
        assertEquals(3, monsterStats.size());
    }
}
