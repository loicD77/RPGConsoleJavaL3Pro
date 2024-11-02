package ProtectiveClothing;

import Player.Player;

public class Shield extends ProtectionItem {
    // Constructeur de la classe Shield
    public Shield(String name, String description, int defense, int price) {
        super(name, description, defense, price);
    }

    @Override
    public String asciiArt() {
        return
                "   ____  \n" +
                        "  / __ \\ \n" +
                        " / /  \\ \\\n" +
                        "/_/    \\_\\\n";
    }

    @Override
    public void use(Player player) {
        System.out.println("Vous équipez le bouclier et augmentez votre défense de " + defense + " points.");
        player.increaseDefense(defense);
    }

    @Override
    public String getDescription() {
        return String.format("%s (Défense: %d, Prix: %d)", getName(), getDefensePoints(), getPrice());
    }

    // Méthode pour obtenir les points de défense
    public int getDefensePoints() {
        return this.defense;
    }
}
