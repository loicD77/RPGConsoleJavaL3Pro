package WeaponGroup;

import WeaponGroup.Axe;
import Player.Player;

public class HacheGelee extends Axe {
    public HacheGelee() {
        super("Hache Gelée", "Une hache qui gèle ses cibles", 140, 50);
    }

    @Override
    public String asciiArt() {
        return "   _  \n" +
                " _|_|_\n" +
                "|  ~  |\n" +
                "|_____|\n" +
                "  | |  \n";
    }

    @Override
    public void use(Player player) {
        System.out.println("Vous utilisez la Hache Gelée pour attaquer !");
    }

    @Override
    public int calculateAttackDamage(int attackType) {
        switch (attackType) {
            case 1: // Coup gelé
                System.out.println("Vous effectuez un coup gelé avec la Hache Gelée.");
                return getDamage() + 10;
            case 2: // Tempête de glace
                System.out.println("Vous réalisez une tempête de glace avec la Hache Gelée.");
                return getDamage() + 35;
            case 3: // Attaque rapide
                System.out.println("Vous lancez une attaque rapide avec la Hache Gelée.");
                return getDamage() + 18;
            case 4: // Avalanche
                System.out.println("Vous déclenchez une avalanche avec la Hache Gelée.");
                return getDamage() + 45;
            default:
                System.out.println("Type d'attaque non reconnu. Aucun dégât infligé.");
                return 0;
        }
    }
}
