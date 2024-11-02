package WeaponGroup;

import WeaponOriginal.Weapon;
import Player.Player;

public abstract class Sword extends Weapon {
    public Sword(String name, String description, int damage, int price) {
        super(name, description, damage, price);
    }

    @Override
    public String asciiArt() {
        return "    />\n" +
                "   /< \\\n" +
                "  |   ||\n" +
                "  |   ||\n" +
                "  |   ||\n" +
                " /|___|\\\n" +
                "|_______|\n" +
                "  | | |\n" +
                "  |_|_|\n";
    }

    @Override
    public void use(Player player) {
        System.out.println("Vous utilisez la " + getName() + " pour attaquer !");
    }
}
