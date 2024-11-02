package WeaponGroup;

import WeaponOriginal.Weapon;
import Player.Player;

public abstract class Bow extends Weapon {
    public Bow(String name, String description, int damage, int price) {
        super(name, description, damage, price);
    }

    @Override
    public String asciiArt() {
        return "    )\n" +
                "   /|\\\n" +
                "  / | \\\n" +
                "    |\n" +
                "    |\n";
    }

    @Override
    public void use(Player player) {
        System.out.println("Vous utilisez l'" + getName() + " pour attaquer !");
    }
}
