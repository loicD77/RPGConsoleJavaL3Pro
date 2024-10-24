public class Entrance extends DungeonPiece {
    public Entrance() {
        // Appel du constructeur parent avec les bons paramètres
        super("Entrée", "Vous êtes à l'entrée du donjon.", 1); // Nom, description, et niveau requis
    }

    @Override
    public String asciiArt() {
        return "     |-------------------|\n"
                + "     |                   |\n"
                + "     |      ENTRÉE       |\n"
                + "     |                   |\n"
                + "     |      _  _         |\n"
                + "     |     | || |        |\n"
                + "     |     | || |        |\n"
                + "     |_____|_||_|________|\n"
                + "          /\\   \n"
                + "         /__\\ \n";
    }

    @Override
    public void enter(Player player) {
        System.out.println("Vous entrez dans le donjon. Bonne chance !");
    }
}
