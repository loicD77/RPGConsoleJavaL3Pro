package WeaponGroup;

import WeaponOriginal.Weapon;
import Player.Player;

public class LegendarySword extends Weapon {
    public LegendarySword() {
        super("Épée légendaire", "Une épée puissante et ancienne.", 50, 100);
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
        System.out.println("Vous utilisez l'" + getName() + " pour attaquer !");
        player.equipWeapon(getName());
    }

    @Override
    public int calculateAttackDamage(int attackType) {
        switch (attackType) {
            case 1:
                System.out.println("Vous effectuez une frappe légendaire.");
                return getDamage() + 20;
            case 2:
                System.out.println("Vous lancez une attaque mythique.");
                return getDamage() + 40;
            case 3:
                System.out.println("Vous réalisez un estoc de légende.");
                return getDamage() + 30;
            case 4:
                System.out.println("Vous lancez une attaque divine.");
                return getDamage() + 60;
            default:
                System.out.println("Type d'attaque non reconnu. Aucun dégât infligé.");
                return 0;
        }
    }

    @Override
    public String[] getAttackOptions() {
        return new String[]{
                "Frappe légendaire",
                "Attaque mythique",
                "Estoc de légende",
                "Attaque divine"
        };
    }
}
