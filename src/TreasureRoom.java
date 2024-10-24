public class TreasureRoom extends DungeonPiece {
    public TreasureRoom() {
        super("Salle au Trésor", "Une pièce remplie de trésors étincelants.", 1); // Exemple avec un niveau requis de 1
    }

    @Override
    public void enter(Player player) {
        System.out.println("Vous êtes entré dans la salle au trésor !");
        // Logique pour la salle au trésor
        System.out.println("Vous voyez des coffres remplis d'or scintillant et de bijoux étincelants.");
        // Ajouter la logique pour récupérer le trésor, par exemple
    }

    @Override
    public String asciiArt() {
        return "     |-------------------|\n"
                + "     |                   |\n"
                + "     |   SALLE AU TRÉSOR |\n"
                + "     |                   |\n"
                + "     |       _.-=-._     |\n"
                + "     |     .'       '.   |\n"
                + "     |    /           \\  |\n"
                + "     |   |  *  *  *   |  |\n"
                + "     |    \\         /   |\n"
                + "     |     '._._._.'    |\n"
                + "     |                   |\n"
                + "     |___________________|\n";
    }
}
