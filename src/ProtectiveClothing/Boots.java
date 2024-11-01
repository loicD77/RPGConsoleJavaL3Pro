package ProtectiveClothing;

import ProtectiveClothing.ProtectionItem; // Corrigez l'import vers le bon package
import Player.Player;

public class Boots extends ProtectionItem {
    public Boots(String name, String description, int defense, int price) {
        super(name, description, defense, price);
    }

    @Override
    public void use(Player player) {
        System.out.println("Vous portez " + getName() + ", augmentant votre défense de " + getDefense() + " points.");
        player.increaseDefense(getDefense());
    }

    @Override
    public String asciiArt() {
        return "   ____   \n" +
                "  |BOOT|\n" +
                "   ‾‾‾‾   ";
    }

    @Override
    public String getDescription() {
        return String.format("%s (Défense: %d, Prix: %d)", getName(), getDefense(), getPrice());
    }
}
