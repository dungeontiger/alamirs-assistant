package com.dungeontiger.alamirsassistant;

import java.util.Collections;
import java.util.List;

public interface ICompositeListItem {
    String getResult();

    default MonsterStats getMonsters() {
        return null;
    }
}
