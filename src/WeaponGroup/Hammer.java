package WeaponGroup;

public class Hammer extends Weapon {
    public Hammer() {
        super("Marteau", "Un marteau lourd", 20, 12); // Ajout de la description
    }

    @Override
    public String asciiArt() {
        return
                "   _  \n" +
                        " _|_|_\n" +
                        "|     |\n" +
                        "|_____| \n" +
                        "  | |  \n";
    }

    @Override
    public void use(Player player) {
        System.out.println("Vous utilisez le marteau pour frapper !");
        // Ajoute toute logique pour affecter le joueur ou d'autres actions, si nécessaire
    }
}
