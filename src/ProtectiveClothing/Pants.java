package ProtectiveClothing;

import ProtectiveOriginal.ProtectionItem; // Assurez-vous d'utiliser le bon package ici
import Player.Player;

public class Pants extends ProtectionItem {
    public Pants(String name, String description, int defense, int price) {
        super(name, description, defense, price);
    }

    @Override
    public void use(Player player) {
        System.out.println("Vous portez " + getName() + ", augmentant votre défense de " + getDefense() + " points.");
        player.increaseDefense(getDefense());
    }

    @Override
    public String asciiArt() {
        return "  |PANTS|  \n" +
                "  /    \\  \n";
    }
}
