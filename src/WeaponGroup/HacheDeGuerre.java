package WeaponGroup;

import WeaponOriginal.Weapon;
import Player.Player;

public class HacheDeGuerre extends Weapon {
    public HacheDeGuerre() {
        super("Hache de Guerre", "Une hache puissante utilisée par les guerriers", 90, 25);
    }

    @Override
    public String asciiArt() {
        return "   _  \n" +
                " _|_|_\n" +
                "|     |\n" +
                "|_____|\n" +
                "  | |  \n";
    }

    @Override
    public void use(Player player) {
        System.out.println("Vous utilisez la Hache de Guerre pour attaquer !");
    }

    @Override
    public int calculateAttackDamage(int attackType) {
        switch (attackType) {
            case 1:
                System.out.println("Vous effectuez un coup puissant avec la Hache de Guerre.");
                return getDamage() + 15;
            case 2:
                System.out.println("Vous réalisez une attaque fracassante avec la Hache de Guerre.");
                return getDamage() + 30;
            case 3:
                System.out.println("Vous lancez une attaque tourbillon avec la Hache de Guerre.");
                return getDamage() + 20;
            case 4:
                System.out.println("Vous réalisez une frappe ultime avec la Hache de Guerre.");
                return getDamage() + 40;
            default:
                System.out.println("Type d'attaque non reconnu. Aucun dégât infligé.");
                return 0;
        }
    }

    public String[] getAttackOptions() {
        return new String[] {
                "Coup Puissant",
                "Attaque Fracassante",
                "Attaque Tourbillon",
                "Frappe Ultime"
        };
    }
}
