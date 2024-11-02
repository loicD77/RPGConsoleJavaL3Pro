package WeaponGroup;

import WeaponGroup.Axe;
import Player.Player;

public class HacheBerserker extends Axe {
    public HacheBerserker() {
        super("Hache Berserker", "Une hache pour les berserkers, augmentant la force au combat", 160, 55);
    }

    @Override
    public String asciiArt() {
        return "   _  \n" +
                " _|_|_\n" +
                "| !!! |\n" +
                "|_____|\n" +
                "  | |  \n";
    }

    @Override
    public void use(Player player) {
        System.out.println("Vous utilisez la Hache Berserker pour attaquer !");
    }

    @Override
    public int calculateAttackDamage(int attackType) {
        switch (attackType) {
            case 1: // Coup de berserker
                System.out.println("Vous effectuez un coup de berserker avec la Hache Berserker.");
                return getDamage() + 20;
            case 2: // Rage dévastatrice
                System.out.println("Vous libérez une rage dévastatrice avec la Hache Berserker.");
                return getDamage() + 45;
            case 3: // Attaque furieuse
                System.out.println("Vous réalisez une attaque furieuse avec la Hache Berserker.");
                return getDamage() + 30;
            case 4: // Carnage total
                System.out.println("Vous déclenchez un carnage total avec la Hache Berserker.");
                return getDamage() + 60;
            default:
                System.out.println("Type d'attaque non reconnu. Aucun dégât infligé.");
                return 0;
        }
    }
}
