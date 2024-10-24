public class MonsterRoom extends DungeonPiece {
    public MonsterRoom() {
        super("Salle des Monstres", "Cette salle est remplie de cris de monstres affamés. L'odeur de la chair pourrie est omniprésente.", 2); // Exemple avec un niveau requis de 2
    }

    @Override
    public void enter(Player player) {
        System.out.println("Vous êtes entré dans la salle des monstres !");
        // Logique pour la salle des monstres, par exemple combattre des monstres
    }

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
}
