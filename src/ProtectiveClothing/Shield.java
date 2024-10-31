package ProtectiveClothing; // Déplacez Shield dans le même package que ProtectionItem

import Player.Player;

public class Shield extends ProtectionItem {
    public Shield(String name, String description, int defense, int price) {
        super(name, description, defense, price);
    }

    @Override
    public void use(Player player) {
        System.out.println("Vous utilisez " + getName() + ", augmentant votre défense de " + getDefense() + " points.");
        player.increaseDefense(getDefense());
    }

    @Override
    public String asciiArt() {
        return "  ______ \n" +
                " /      \\\n" +
                "|        |\n" +
                "| SHIELD |\n" +
                " \\______/ ";
    }
}
