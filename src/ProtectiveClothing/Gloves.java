package ProtectiveClothing;

import Player.Player; // Import de la classe Player

public class Gloves extends ProtectionItem {
    public Gloves(String name, String description, int defense, int price) {
        super(name, description, defense, price); // Appel du constructeur de ProtectionItem
    }

    @Override
    public String asciiArt() {
        return "   [|||]   \n" +
                "  [     ]  \n" +
                "   [|||]   \n";
    }

    @Override
    public void use(Player player) {
        // Logique pour l'utilisation des gants, par exemple augmenter la défense du joueur
        System.out.println("Vous portez les gants et augmentez votre défense de " + getDefense() + " points.");
        player.increaseDefense(getDefense());
    }

    @Override
    public String getDescription() {
        return String.format("%s (Défense: %d, Prix: %d)", getName(), getDefense(), getPrice());
    }
}
