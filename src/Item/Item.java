package Item;

import Player.Player; // Importez la classe Player du package Player

public abstract class Item {
    public String name;
    private String description;
    private int price; // Changez le type de String à int

    public Item(String name, String description, int price) { // Ajoutez le prix au constructeur
        this.name = name;
        this.description = description;
        this.price = price; // Assignez correctement le prix
    }

    public String getName() {
        return name;
    }

    public int getPrice() { // Méthode pour obtenir le prix de l'item
        return price; // Retournez l'int
    }

    public String getDescription() {
        return description;
    }

    public abstract String asciiArt(); // Méthode abstraite

    public abstract void use(Player player); // Méthode abstraite avec Player
}
