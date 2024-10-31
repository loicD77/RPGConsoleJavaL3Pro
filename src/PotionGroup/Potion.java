package PotionGroup;

import Item.Item; // Import de la classe Item, située dans le package Item
import Player.Player; // Import de la classe Player, située dans le package Player

public class Potion extends Item {
    private int healingAmount; // Quantité de soin que la potion apporte

    public Potion(String name, String description, int healingAmount, int price) {
        super(name, description, price); // Appel du constructeur de la classe parente avec le prix
        this.healingAmount = healingAmount; // Initialisation de la quantité de soin
    }

    public int getHealingAmount() {
        return healingAmount; // Retourne la quantité de soin
    }

    @Override
    public String getDescription() {
        // Utilisation de la méthode getDescription() de la classe parente
        return String.format("%s (Soins: %d)", getName(), healingAmount); // Affiche les détails de la potion
    }

    @Override
    public String asciiArt() {
        return "   _____   \n" +
                "  /     \\  \n" +
                " |  ***  | \n" +
                "  \\_____/  \n";
    }

    @Override
    public void use(Player player) {
        // Utilise la méthode restoreHealth
        player.restoreHealth(healingAmount);
        System.out.println("Vous utilisez une potion et regagnez " + healingAmount + " points de vie.");
    }

}
