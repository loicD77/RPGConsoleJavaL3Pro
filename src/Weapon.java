public abstract class Weapon {
    protected String name;
    protected int damage;
    protected int price; // Ajout d'un attribut pour le prix de l'arme

    public Weapon(String name, int damage, int price) {
        this.name = name;
        this.damage = damage;
        this.price = price; // Initialisation du prix
    }

    // Méthode pour obtenir le nom de l'arme
    public String getName() {
        return name;
    }

    // Méthode pour obtenir les dégâts de l'arme
    public int getDamage() {
        return damage;
    }

    // Méthode pour obtenir le prix de l'arme
    public int getPrice() {
        return price;
    }

    // Méthode pour obtenir une description complète de l'arme
    public String getDescription() {
        return String.format("%s (Dégâts: %d, Prix: %d)", name, damage, price);
    }

    // Méthode abstraite pour l'art ASCII de l'arme
    public abstract String asciiArt();
}
