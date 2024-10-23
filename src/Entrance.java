public class Entrance extends DungeonPiece {
    @Override
    public String asciiArt() {
        return "     |-------------------|\n"
                + "     |                   |\n"
                + "     |      ENTRÉE      |\n"
                + "     |                   |\n"
                + "     |      _  _        |\n"
                + "     |     | || |       |\n"
                + "     |     | || |       |\n"
                + "     |_____|_||_|_______|\n"
                + "          /\\   \n"
                + "         /__\\ \n";
    }

    @Override
    public String description() {
        return "Vous êtes à l'entrée du donjon. Une lumière faible filtre à travers les fissures des murs.";
    }
}
