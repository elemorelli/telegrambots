package com.gemorelli.telegram.rpgtoolsbot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class RPGToolsService {

    private static List<RPGToolsCommands> commands;

    private static RPGToolsService instance = new RPGToolsService();

    private RPGToolsService() {
        commands = new ArrayList<RPGToolsCommands>();
        Collections.addAll(commands, RPGToolsCommands.values());
    }

    static List<RPGToolsCommands> getCommands() {
        if (instance == null)
            instance = new RPGToolsService();
        return commands;
    }

    static String rollDice(String input) {
        return "";
    }
}
