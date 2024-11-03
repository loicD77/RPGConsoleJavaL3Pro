package WeaponGroup;

import WeaponGroup.Sword;

public class SabreLumiere extends Sword {
    public SabreLumiere() {
        super("Sabre de Lumière", "Un sabre légendaire qui brille dans le noir", 150, 40);
    }

    @Override
    public int calculateAttackDamage(int attackType) {
        switch (attackType) {
            case 1:
                System.out.println("Vous effectuez un coup éclatant avec le Sabre de Lumière.");
                return getDamage() + 15;
            case 2:
                System.out.println("Vous réalisez une attaque lumineuse avec le Sabre de Lumière.");
                return getDamage() + 30;
            case 3:
                System.out.println("Vous réalisez une frappe aveuglante avec le Sabre de Lumière.");
                return getDamage() + 20;
            case 4:
                System.out.println("Vous lancez un faisceau de lumière avec le Sabre de Lumière.");
                return getDamage() + 40;
            default:
                System.out.println("Type d'attaque non reconnu. Aucun dégât infligé.");
                return 0;
        }
    }

    public String[] getAttackOptions() {
        return new String[] {
                "Coup Éclatant",
                "Attaque Lumineuse",
                "Frappe Aveuglante",
                "Lancer de Lumière"
        };
    }
}
