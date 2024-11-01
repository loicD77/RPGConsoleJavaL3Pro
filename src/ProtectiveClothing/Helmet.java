package ProtectiveClothing;

import ProtectiveClothing.ProtectionItem; // Corrigez l'import vers le bon package
import Player.Player; // Import de la classe Player

public class Helmet extends ProtectionItem {
    public Helmet(String name, String description, int defense, int price) {
        super(name, description, defense, price);
    }

    @Override
    public void use(Player player) {
        System.out.println("Vous portez " + getName() + ", augmentant votre défense de " + getDefense() + " points.");
        player.increaseDefense(getDefense());
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
