import WeaponOriginal.Weapon;

public class Bow extends Weapon {
    public Bow() {
        super("Arc", "Un arc puissant", 10, 20); // Ajout de la description
    }

    @Override
    public String asciiArt() {
        return
                "  \\  \n" +
                        "   \\ \n" +
                        "    O\n" +
                        "   / \n" +
                        "  /  \n";
    }

    @Override
    public void use(Player player) {
        System.out.println("Vous utilisez l'arc pour tirer une flèche !");
        // Ajoute toute logique pour affecter le joueur ou autres actions, si nécessaire
    }
}
