package WeaponGroup;

import WeaponOriginal.Weapon;
import Player.Player;

public abstract class Hammer extends Weapon {
    public Hammer(String name, String description, int damage, int price) {
        super(name, description, damage, price);
    }

    @Override
    public String asciiArt() {
        return "   ____  \n" +
                "  |    | \n" +
                "  |____| \n" +
                "    ||   \n" +
                "    ||   \n";
    }

    @Override
    public void use(Player player) {
        System.out.println("Vous utilisez le " + getName() + " pour attaquer !");
    }
}
