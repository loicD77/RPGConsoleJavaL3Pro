package Item;

import Player.Player;

public abstract class Item {
    public String name;
    private String description;
    private int price;

    public Item(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() { // Méthode pour obtenir le prix de l'item
        return price;
    }

    public String getDescription() {
        return description;
    }

    public abstract String asciiArt(); // Méthode abstraite

    public abstract void use(Player player); // Méthode abstraite avec Player
}
