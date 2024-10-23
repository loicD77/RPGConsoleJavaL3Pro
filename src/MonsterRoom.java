public class MonsterRoom extends DungeonPiece {
    @Override
    public String asciiArt() {
        return "     |-------------------|\n"
                + "     |                   |\n"
                + "     |   SALLE DES       |\n"
                + "     |    MONSTRES       |\n"
                + "     |                   |\n"
                + "     |     /\\__/\\       |\n"
                + "     |    |        |     |\n"
                + "     |    |  O O   |     |\n"
                + "     |    |   ^^    |     |\n"
                + "     |    |_________|     |\n"
                + "     |                   |\n"
                + "     |___________________|\n";
    }

    @Override
    public String description() {
        return "Cette salle est remplie de cris de monstres affamés. L'odeur de la chair pourrie est omniprésente.";
    }
}
