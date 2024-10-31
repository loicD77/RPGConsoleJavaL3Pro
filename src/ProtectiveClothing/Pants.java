public class Pants extends ProtectionItem {
    private int defense; // Défense fournie par le pantalon

    public Pants(String name, String description, int defense, int price) {
        super(name, description, defense, price); // Appel du constructeur de ProtectionItem
        this.defense = defense;
    }

    public int getDefense() {
        return defense; // Renvoie la défense du pantalon
    }

    @Override
    public String asciiArt() {
        return "   [|||]   \n" +
                "  [     ]  \n" +
                "   [|||]   \n";
    }

    @Override
    public void use(Player player) {
        // Logique pour l'utilisation du pantalon, par exemple augmenter la défense du joueur
        System.out.println("Vous portez le pantalon et augmentez votre défense de " + defense + " points.");
        player.increaseDefense(defense); // Assurez-vous que Player a une méthode increaseDefense(int)
    }
}
