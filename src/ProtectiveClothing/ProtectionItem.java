package ProtectiveClothing;

import Item.Item; // Import de la classe Item
import Player.Player; // Import de la classe Player

public abstract class ProtectionItem extends Item {
    protected int defense; // Attributs protégés
    protected int price;

    public ProtectionItem(String name, String description, int defense, int price) {
        super(name, description, price);
        this.defense = defense;
        this.price = price;
    }

    public int getDefense() {
        return defense; // Getter pour la défense
    }

    public int getPrice() {
        return price; // Getter pour le prix
    }

    @Override
    public abstract String asciiArt(); // Méthode abstraite

    @Override
    public void use(Player player) {
        System.out.println("Vous portez " + getName() + ", augmentant votre défense de " + getDefense() + " points.");
        player.increaseDefense(getDefense());
    }
}
