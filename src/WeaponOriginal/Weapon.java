package WeaponOriginal;

import Item.Item; // Importez la classe Item

public abstract class Weapon extends Item {
    protected int damage; // Dégâts infligés par l'arme
    private int gold; // Ajoutez cette variable pour stocker l'or

    public Weapon(String name, String description, int damage, int price) {
        super(name, description, price); // Appel du constructeur de la classe parente avec le prix
        this.damage = damage; // Initialisation des dégâts
    }

    public int getDamage() {
        return damage; // Retourne les dégâts
    }

    public int getGold() {
        return gold; // Assurez-vous de renvoyer l'or
    }

    @Override
    public String getDescription() {
        return String.format("%s (Dégâts: %d, Prix: %d)", getName(), damage, getPrice()); // Affiche les détails de l'arme
    }

    public abstract String asciiArt(); // Méthode abstraite pour l'art ASCII

    public void use() {
        // Logique par défaut pour utiliser une arme
        System.out.println("Vous utilisez l'arme !");
    }
}
