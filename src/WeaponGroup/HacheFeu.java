package WeaponGroup;

import WeaponGroup.Axe;

public class HacheFeu extends Axe {
    public HacheFeu() {
        super("Hache de Feu", "Une hache enflammée, capable de brûler les ennemis", 130, 45);
    }

    @Override
    public int calculateAttackDamage(int attackType) {
        switch (attackType) {
            case 1:
                System.out.println("Vous effectuez un coup de feu avec la Hache de Feu.");
                return getDamage() + 15;
            case 2:
                System.out.println("Vous réalisez une brûlure intense avec la Hache de Feu.");
                return getDamage() + 35;
            case 3:
                System.out.println("Vous réalisez un lancer de flammes avec la Hache de Feu.");
                return getDamage() + 25;
            case 4:
                System.out.println("Vous libérez une tempête de feu avec la Hache de Feu.");
                return getDamage() + 45;
            default:
                System.out.println("Type d'attaque non reconnu. Aucun dégât infligé.");
                return 0;
        }
    }

    public String[] getAttackOptions() {
        return new String[] {
                "Coup de Feu",
                "Brûlure Intense",
                "Lancer de Flammes",
                "Tempête de Feu"
        };
    }
}
