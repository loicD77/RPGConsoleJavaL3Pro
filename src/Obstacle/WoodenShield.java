package Obstacle;

public class WoodenShield extends ProtectionItem {
    public WoodenShield() {
        super("Bouclier en bois", "Un bouclier en bois basique.", 15, 20);
    }

    @Override
    public String asciiArt() {
        return "ASCII du Bouclier en bois";
    }

    @Override
    public void use(Player player) {
        System.out.println("Vous équipez le " + getName() + ", augmentant votre défense de " + getDefensePoints() + ".");
        player.increaseDefense(getDefensePoints());
    }
}
