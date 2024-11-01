package WeaponGroup;

import WeaponOriginal.Weapon;
import Player.Player;
import Interface.Attackable;

public class Bow extends Weapon {
    public Bow() {
        super("Arc", "Un arc puissant", 10, 20); // Ajout de la description
    }

    @Override
    public String asciiArt() {
        return
                "  \\  \n" +
                        "   \\ \n" +
                        "    O\n" +
                        "   / \n" +
                        "  /  \n";
    }

    @Override
    public void use(Player player) {
        System.out.println("Vous utilisez l'arc pour tirer une flèche !");
        // Ajoutez ici toute logique pour affecter le joueur ou autres actions
    }

    public void attackWithBow(Attackable target, int attackType) {
        int damage = calculateBowAttackDamage(attackType);
        target.takeDamage(damage);
        System.out.println("Vous avez attaqué " + target.getName() + " et infligé " + damage + " points de dégâts avec l'arc.");
    }

    private int calculateBowAttackDamage(int attackType) {
        switch (attackType) {
            case 1: // Tir précis
                System.out.println("Vous effectuez un tir précis avec l'arc.");
                return 15;
            case 2: // Tir en cloche
                System.out.println("Vous effectuez un tir en cloche, infligeant des dégâts puissants.");
                return 25;
            case 3: // Tir rapide
                System.out.println("Vous effectuez un tir rapide, visant à infliger des dégâts modérés avec vitesse.");
                return 20;
            case 4: // Tir multiple
                System.out.println("Vous tirez plusieurs flèches à la fois, infligeant des dégâts considérables.");
                return 35;
            default:
                System.out.println("Type d'attaque non reconnu. Aucun dégât infligé.");
                return 0;
        }
    }
}
