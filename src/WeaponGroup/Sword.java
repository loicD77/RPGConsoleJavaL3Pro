package WeaponGroup;

import WeaponOriginal.Weapon;
import Player.Player;

public class Sword extends Weapon {
    public Sword() {
        super("Épée", "Une épée tranchante", 15, 10); // Ajoutez la description, les dégâts, et le prix
    }

    @Override
    public String asciiArt() {
        return
                "  /\\  \n" +
                        " /  \\ \n" +
                        "|    |\n" +
                        " \\  / \n" +
                        "  \\/  \n";
    }

    @Override
    public void use(Player player) {
        System.out.println("Vous utilisez l'épée pour attaquer !");
        // Ajouter toute logique d'utilisation qui affecte le joueur ou autres actions
    }
}
