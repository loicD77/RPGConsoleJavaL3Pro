package WeaponGroup;

import WeaponOriginal.Weapon;
import Player.Player;

public class Fist extends Weapon {
    public Fist() {
        super("Mains Nues", "Utiliser ses petites mains !", 3, 0); // Donnez-lui un nom et des dégâts de base.
    }

    @Override
    public String asciiArt() {
        return "  O\n /|\\ \n / \\"; // Représentation ASCII basique pour l'attaque à mains nues.
    }

    @Override
    public void use(Player player) {
        // Logique pour utiliser les mains nues
        // Par exemple, vous pouvez appliquer des dégâts à un ennemi
        // Cela dépend de la manière dont votre combat est géré
        System.out.println(player.getName() + " utilise ses " + getName() + " !");
        // Vous pouvez appeler une méthode pour infliger des dégâts ici
    }
}
