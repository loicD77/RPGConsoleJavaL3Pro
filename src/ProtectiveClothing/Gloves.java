public class Gloves extends ProtectionItem {
    private int defense; // Défense fournie par les gants

    public Gloves(String name, String description, int defense, int price) {
        super(name, description, defense, price); // Appel du constructeur de ProtectionItem
        this.defense = defense;
    }

    public int getDefense() {
        return defense; // Renvoie la défense des gants
    }

    @Override
    public String asciiArt() {
        return "   [|||]   \n" +
                "  [     ]  \n" +
                "   [|||]   \n";
    }

    @Override
    public void use(Player player) {
        // Logique pour l'utilisation des gants, par exemple augmenter la défense du joueur
        System.out.println("Vous portez les gants et augmentez votre défense de " + defense + " points.");
        player.increaseDefense(defense); // Assurez-vous que Player a une méthode increaseDefense(int)
    }
}
