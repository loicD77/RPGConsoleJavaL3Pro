package WeaponOriginal;

import Item.Item;

public abstract class Weapon extends Item {
    protected int damage; // Dégâts infligés par l'arme
    private int gold;

    public Weapon(String name, String description, int damage, int price) { // Constructeur pour les armes et ses dérivées
        super(name, description, price); // Appel du constructeur de la classe parente avec le prix
        this.damage = damage; // Initialisation des dégâts
    }

    public int getDamage() {
        return damage; // Retourne les dégâts
    }

    public int getGold() {
        return gold;
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

    public abstract int calculateAttackDamage(int attackType);
    public abstract String[] getAttackOptions();
}
