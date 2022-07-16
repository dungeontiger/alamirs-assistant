package com.dungeontiger.alamirsassistant;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MonsterCompositeListItemTest {
    @Mock
    Dice dice;
    NLG nlg = new NLG();

    @Test
    public void testMonster() {
        when(dice.roll("2d4")).thenReturn(3);
        when(dice.roll("2d6+6")).thenReturn(13);
        when(dice.roll(1,20,1)).thenReturn(14);
        MonsterCompositeListItem monsterItem = new MonsterCompositeListItem("2d4", "Orc", "Orcs",
                "2d6+6", 1, 13, "MM:246", dice, nlg);
        MonsterStats monsterStats = monsterItem.getResult().getMonsters();
        assertEquals(14, monsterStats.getInitiative());
        assertEquals(3, monsterStats.getHPs().size());
        for (HP hp : monsterStats.getHPs()) {
            assertEquals(13, hp.getHP());
        }
    }
}
