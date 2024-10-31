package ProtectiveClothing; // Le package correspondant au dossier dans lequel se trouve la classe Armor

// Import des autres classes utilisées dans cette classe
import ProtectiveOriginal.ProtectionItem; // ProtectionItem se trouve dans le package ProtectiveOriginal
import Player.Player; // Player se trouve dans le package Player

public class Armor extends ProtectionItem {
    public Armor(String name, String description, int defense, int price) {
        super(name, description, defense, price);
    }

    @Override
    public String asciiArt() {
        return "   [===]   \n" +
                "  [     ]  \n" +
                "   [===]   \n";
    }

    @Override
    public void use(Player player) {
        // Logique pour l'utilisation de l'armure, par exemple augmenter la défense du joueur
        System.out.println("Vous portez l'armure et augmentez votre défense de " + defense + " points.");
        player.increaseDefense(defense); // Assure-toi que Player a une méthode increaseDefense(int)
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Armor armor = (Armor) obj;
        return this.name.equals(armor.name); // ou une autre propriété unique
    }

    @Override
    public String getDescription() {
        // Utilisation des getters pour accéder aux attributs
        return String.format("%s (Défense: %d, Prix: %d)", getName(), getDefense(), getPrice());
    }
}
