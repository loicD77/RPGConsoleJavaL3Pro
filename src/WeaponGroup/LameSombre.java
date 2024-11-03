package WeaponGroup;

import WeaponOriginal.Weapon;
import Player.Player;

public class LameSombre extends Weapon {
    public LameSombre() {
        super("Lame Sombre", "Une épée aux pouvoirs mystérieux", 120, 35);
    }

    @Override
    public String asciiArt() {
        return "   /\\  \n" +
                "  /  \\ \n" +
                " |    |\n" +
                "  \\  / \n" +
                "   \\/  \n";
    }

    @Override
    public void use(Player player) {
        System.out.println("Vous utilisez la Lame Sombre pour attaquer !");
        player.equipWeapon(getName());
    }

    @Override
    public int calculateAttackDamage(int attackType) {
        switch (attackType) {
            case 1:
                System.out.println("Vous effectuez un coup d'ombre avec la Lame Sombre.");
                return getDamage() + 12;
            case 2:
                System.out.println("Vous effectuez une attaque ombragée avec la Lame Sombre.");
                return getDamage() + 25;
            case 3:
                System.out.println("Vous réalisez un estoc du néant avec la Lame Sombre.");
                return getDamage() + 18;
            case 4:
                System.out.println("Vous lancez une attaque finale des ténèbres avec la Lame Sombre.");
                return getDamage() + 35;
            default:
                System.out.println("Type d'attaque non reconnu. Aucun dégât infligé.");
                return 0;
        }
    }

    @Override
    public String[] getAttackOptions() {
        return new String[]{
                "Coup d'ombre",
                "Attaque ombragée",
                "Estoc du néant",
                "Attaque finale des ténèbres"
        };
    }
}
