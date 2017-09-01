package com.gemorelli.telegram.pitchforkbot;

enum PitchforkEnum {
    TRADITIONAL("/traditional", "Traditional", "---E"),
    LEFTHANDED("/lefthanded", "Left Handed", "Ǝ---"),
    FANCY("/fancy", "The Fancy", "---{"),
    TWO_PRONGED("/twopronged", "Two Pronged", "---<"),
    REINFORCED("/reinforced", "Reinforced", "===E"),
    TWO_HANDED("/twohanded", "Two Handed", "------E"),
    DOUBLE("/double", "Double", "Ǝ------E"),
    HOLY("/holy", "Holy", "+---E"),
    DISCOUNT("/33discount", "33% Discount", "---F"),
    GREATER_DISCOUNT("/66discount", "66% Discount", "---L"),
    BROKEN("/broken", "Broken", "---|"),
    DEFECTIVE("/defective", "Defective", "---e"),
    EURO("/euro", "Euro", "---€"),
    POUND("/pound", "Pound", "---£"),
    LIRA("/lira", "Lira", "---₤"),
    FLAMING("/flaming", "With torch", "---E " + String.format("%c", 0x1F525)),
    ANGRY_SKELETON("/angryskeleton", "Angry Skeleton", "Ǝ--- " + String.format("%c", 0x1F480) + " " + String.format("%c", 0x1F525));

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
