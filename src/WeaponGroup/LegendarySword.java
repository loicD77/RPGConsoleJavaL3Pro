package WeaponGroup;

import WeaponOriginal.Weapon; // Import de la classe Weapon du package WeaponOriginal
import Player.Player; // Import de la classe Player

public class LegendarySword extends Weapon {
    public LegendarySword() {
        super("Épée légendaire", "Une épée puissante et ancienne.", 50, 100);
    }

    @Override
    public String asciiArt() {
        return "    />\n" +
                "   /< \\\n" +
                "  |   ||\n" +
                "  |   ||\n" +
                "  |   ||\n" +
                " /|___|\\\n" +
                "|_______|\n" +
                "  | | |\n" +
                "  |_|_|\n";
    }

    @Override
    public void use(Player player) {
        // Logique pour utiliser l'épée, par exemple, infliger des dégâts à un ennemi
        System.out.println("Vous utilisez l'" + getName() + " pour attaquer !");
        // Vous pouvez également appeler des méthodes sur le joueur ici si nécessaire
        // Par exemple : augmenter la force ou changer l'arme équipée du joueur
        player.equipWeapon(getName());
    }
}
