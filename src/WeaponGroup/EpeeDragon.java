package WeaponGroup;

import WeaponGroup.Sword;

public class EpeeDragon extends Sword {
    public EpeeDragon() {
        super("Épée du Dragon", "Une épée fabriquée à partir des écailles d'un dragon", 180, 50);
    }

    @Override
    public int calculateAttackDamage(int attackType) {
        switch (attackType) {
            case 1: // Frappe du dragon
                System.out.println("Vous effectuez une frappe du dragon avec l'Épée du Dragon.");
                return getDamage() + 20;
            case 2: // Flamme de dragon
                System.out.println("Vous réalisez une attaque flamboyante avec l'Épée du Dragon.");
                return getDamage() + 40;
            case 3: // Coup d'aile
                System.out.println("Vous réalisez un coup d'aile rapide avec l'Épée du Dragon.");
                return getDamage() + 25;
            case 4: // Rage du dragon
                System.out.println("Vous libérez la rage du dragon avec l'Épée du Dragon.");
                return getDamage() + 50;
            default:
                System.out.println("Type d'attaque non reconnu. Aucun dégât infligé.");
                return 0;
        }
    }
}
