package com.dungeontiger.alamirsassistant;

public class BaseController {
    protected Dice dice = new Dice();
    protected NLG nlg = new NLG(dice);
    protected ITableManager tableManager;
    protected BaseController() {
        tableManager = new ResourceTableManager(dice, nlg);
    }
}
