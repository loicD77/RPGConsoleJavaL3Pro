package ProtectiveClothing;
public class Helmet extends ProtectionItem {
    public Helmet(String name, String description, int defense, int price) {
        super(name, description, defense, price);
    }

    @Override
    public void use(Player player) {
        System.out.println("Vous portez " + getName() + ", augmentant votre défense de " + getDefense() + " points.");
        player.increaseDefense(getDefense());
    }

    @Override
    public String asciiArt() {
        return "   ___   \n" +
                "  /   \\  \n" +
                " | HELM |\n" +
                "  \\___/  ";
    }
}

