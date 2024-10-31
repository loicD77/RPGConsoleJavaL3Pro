package ProtectiveClothing;

import Player.Player;

public class Armor extends ProtectionItem {
    public Armor(String name, String description, int defense, int price) {
        super(name, description, defense, price);
    }

    @Override
    public String asciiArt() {
        return "   [===]   \n" +
                "  [     ]  \n" +
                "   [===]   \n";
    }

    @Override
    public void use(Player player) {
        System.out.println("Vous portez l'armure et augmentez votre défense de " + defense + " points.");
        player.increaseDefense(defense);
    }

    @Override
    public String getDescription() {
        return String.format("%s (Défense: %d, Prix: %d)", getName(), getDefensePoints(), getPrice());
    }
}
