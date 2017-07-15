package com.gemorelli.telegram.pitchforkbot;

public enum PitchforkEnum {
    TRADITIONAL("/traditional", "The Traditional", "---E"),
    LEFTHANDED("/lefthanded", "The Left Handed", "Ǝ---"),
    FANCY("/fancy", "The Fancy", "---{"),
    TWO_PRONGED("/twopronged", "The Two Pronged", "---<"),
    REINFORCED("/reinforced", "The Reinforced", "===E"),
    DISCOUNT("/33discount", "33% Discount", "---F"),
    GREATER_DISCOUNT("/66discount", "66% Discount", "---L"),
    BROKEN("/broken", "The Broken", "---|"),
    DEFECTIVE("/defective", "The Defective", "---e"),
    EURO("/euro", "The Euro", "---€"),
    POUND("/pound", "The Pound", "---£"),
    LIRA("/lira", "The Lira", "---₤");

    private final String command;
    private final String name;
    private final String value;

    PitchforkEnum(String command, String name, String value) {
        this.command = command;
        this.name = name;
        this.value = value;
    }

    public String getCommand() {
        return command;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
