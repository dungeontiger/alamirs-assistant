package com.dungeontiger.alamirsassistant;

public class BaseController {
    protected Dice dice = new Dice();
    protected ITableManager tableManager;
    protected BaseController() {
        tableManager = new ResourceTableManager(dice);
    }
}
