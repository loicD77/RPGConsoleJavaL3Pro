package WeaponGroup;

import Player.Player;
import WeaponOriginal.Weapon;
import Interface.Attackable;

public class Axe extends Weapon {
    public Axe() {
        super("Hache", "Une hache tranchante", 15, 30); // Ajout de la description
    }

    @Override
    public String asciiArt() {
        return
                "   /\\ \n" +
                        "  /__\\ \n" +
                        "  \\  \\\n" +
                        "   \\/  \n";
    }

    @Override
    public void use(Player player) {
        System.out.println("Vous utilisez la hache pour frapper !");
        // Logique pour affecter le joueur ou d'autres actions, si nécessaire
    }

    public void attackWithAxe(Attackable target, int attackType) {
        int damage = calculateAxeAttackDamage(attackType);
        target.takeDamage(damage);
        System.out.println("Vous avez attaqué " + target.getName() + " et infligé " + damage + " points de dégâts avec la hache.");
    }

    private int calculateAxeAttackDamage(int attackType) {
        switch (attackType) {
            case 1: // Coup tranchant
                System.out.println("Vous effectuez un coup tranchant avec la hache.");
                return 20;
            case 2: // Coup puissant
                System.out.println("Vous effectuez un coup puissant avec la hache, infligeant des dégâts élevés.");
                return 35;
            case 3: // Coup rapide
                System.out.println("Vous effectuez un coup rapide avec la hache, visant à infliger des dégâts modérés avec vitesse.");
                return 25;
            case 4: // Double coup
                System.out.println("Vous effectuez un double coup avec la hache, infligeant des dégâts considérables.");
                return 40;
            default:
                System.out.println("Type d'attaque non reconnu. Aucun dégât infligé.");
                return 0;
        }
    }
}
