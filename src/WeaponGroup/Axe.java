import WeaponOriginal.Weapon;

public class Axe extends Weapon {
    public Axe() {
        super("Hache", "Une hache tranchante", 15, 30); // Ajout de la description
    }

    @Override
    public String asciiArt() {
        return
                "   /\\ \n" +
                        "  /__\\ \n" +
                        "  \\  \\\n" +
                        "   \\/  \n";
    }

    @Override
    public void use(Player player) {
        System.out.println("Vous utilisez la hache pour frapper !");
        // Logique pour affecter le joueur ou d'autres actions, si nécessaire
    }
}
