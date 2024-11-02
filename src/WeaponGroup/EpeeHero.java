package WeaponGroup;

import WeaponGroup.Sword;

public class EpeeHero extends Sword {
    public EpeeHero() {
        super("Épée du Héros", "Une épée forgée pour les braves héros", 100, 30);
    }

    @Override
    public int calculateAttackDamage(int attackType) {
        switch (attackType) {
            case 1: // Coup de héros
                System.out.println("Vous effectuez un coup de héros.");
                return getDamage() + 10;
            case 2: // Attaque puissante du héros
                System.out.println("Vous réalisez une attaque puissante du héros.");
                return getDamage() + 20;
            case 3: // Frappe rapide du héros
                System.out.println("Vous réalisez une frappe rapide du héros.");
                return getDamage() + 15;
            case 4: // Attaque ultime du héros
                System.out.println("Vous effectuez une attaque ultime du héros.");
                return getDamage() + 30;
            default:
                System.out.println("Type d'attaque non reconnu. Aucun dégât infligé.");
                return 0;
        }
    }
}

