package com.dungeontiger.alamirsassistant;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class Controller {
    private Dice dice = new Dice();
    private ITableManager tableManager;

    public Controller() {
        tableManager = new ResourceTableManager(dice);
    }

    @GetMapping("/")
    public String index() {
        return "Alamir's Assistant";
    }

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
    public List<List<BaseTableResult>> tableRoll(@PathVariable("campaignId") String campaignId,
                                                 @PathVariable("tableId") String tableId,
                                                 @PathVariable("repeat") Optional<Integer> repeat) {
        List<List<BaseTableResult>> results = new ArrayList<>();
        int repeats = 1;
        if (repeat.isPresent()) {
            repeats = repeat.get();
        }
        for (int i = 0; i < repeats; i++) {
            results.add(tableManager.getTable(campaignId, tableId).roll());
        }
        return results;
    }
}
