package com.gemorelli.telegram.rpgtoolsbot;

enum RPGToolsCommands {
    ROLL_DICE("/roll", "Roll a dice", "1d20");

    private final String command;
    private final String description;
    private final String defaultValue;

    RPGToolsCommands(String command, String description, String defaultValue) {
        this.command = command;
        this.description = description;
        this.defaultValue = defaultValue;
    }

    public String getCommand() {
        return command;
    }

    public String getDescription() {
        return description;
    }

    public String getDefaultValue() {
        return defaultValue;
    }
}
