package Item;
import Player.Player;

public class Equipment extends Item {
    private int defensePoints;

    public Equipment(String name, String description, int price, int defensePoints) {
        super(name, description, price); // Passe tous les paramètres nécessaires à la classe parente
        this.defensePoints = defensePoints;
    }

    public int getDefensePoints() {
        return defensePoints;
    }

    @Override
    public void use(Player player) {
        System.out.println(player.getName() + " utilise l'équipement : " + getName());
        player.increaseDefense(defensePoints);
    }

    @Override
    public String asciiArt() {
        return "ASCII art de " + getName();
    }
}
