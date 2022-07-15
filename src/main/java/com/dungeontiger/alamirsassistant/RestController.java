package com.dungeontiger.alamirsassistant;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.web.bind.annotation.RestController
public class RestController extends BaseController {
    @GetMapping(value = {"/roll/{diceString}", "/roll/{diceString}/{repeat}"})
    public List<Integer> roll(@PathVariable("diceString") String diceString, @PathVariable("repeat") Optional<Integer> repeat) {
        List<Integer> results = new ArrayList<>();
        int repeats = 1;
        if (repeat.isPresent()) {
            repeats = repeat.get();
        }
        for (int i = 0; i < repeats; i++) {
            results.add(dice.roll(diceString));
        }
        return results;
    }

    @GetMapping(value = {"/tableRoll/{campaignId}/{tableId}", "/tableRoll/{campaignId}/{tableId}/{repeat}"})
    public List<List<ResponseResult>> tableRoll(@PathVariable("campaignId") String campaignId,
                                                 @PathVariable("tableId") String tableId,
                                                 @PathVariable("repeat") Optional<Integer> repeat) {
        List<List<ResponseResult>> results = new ArrayList<>();
        int repeats = 1;
        if (repeat.isPresent()) {
            repeats = repeat.get();
        }
        for (int i = 0; i < repeats; i++) {
            results.add(tableManager.getTable(campaignId, tableId).roll());
        }
        return results;
    }

    @GetMapping("/tableRoll/campaigns")
    public List<String> campaigns() {
        return tableManager.getCampaigns();
    }

    @GetMapping("/tableRoll/{campaignId}/tables")
    public List<String> tables(@PathVariable("campaignId") String campaignId) {
        return tableManager.getTableNames(campaignId);
    }
}
