package ProtectiveClothing;

import Player.Player;

public class Helmet extends ProtectionItem {
    public Helmet(String name, String description, int defense, int price) {
        super(name, description, defense, price);
    }

    @Override
    public String asciiArt() {
        return "   ___   \n" +
                "  /   \\  \n" +
                " | HELM |\n" +
                "  \\___/  ";
    }

    @Override
    public String getDescription() {
        return String.format("%s (Défense: %d, Prix: %d)", getName(), getDefense(), getPrice());
    }
}
