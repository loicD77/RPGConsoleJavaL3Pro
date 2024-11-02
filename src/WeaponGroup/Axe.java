package WeaponGroup;

import WeaponOriginal.Weapon;
import Player.Player;

public abstract class Axe extends Weapon {
    public Axe(String name, String description, int damage, int price) {
        super(name, description, damage, price);
    }

    @Override
    public String asciiArt() {
        return "   _  \n" +
                " _|_|_\n" +
                "|     |\n" +
                "|_____|\n" +
                "  | |  \n";
    }

    @Override
    public void use(Player player) {
        System.out.println("Vous utilisez la " + getName() + " pour attaquer !");
        // Autres actions spécifiques, par exemple : augmenter la force
    }
}
