package ProtectiveClothing;

import Player.Player;

public class Shield extends ProtectionItem {
    public Shield(String name, String description, int defense, int price) {
        super(name, description, defense, price);
    }

    @Override
    public String asciiArt() {
        return "   ____  \n" +
                "  / __ \\ \n" +
                " / /  \\ \\\n" +
                "/_/    \\_\\\n";
    }

    @Override
    public String getDescription() {
        return String.format("%s (Défense: %d, Prix: %d)", getName(), getDefense(), getPrice());
    }
}
