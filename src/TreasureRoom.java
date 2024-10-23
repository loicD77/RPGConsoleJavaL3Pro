public class TreasureRoom extends DungeonPiece {
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

    @Override
    public String description() {
        return "Vous voyez des coffres remplis d'or scintillant et de bijoux étincelants.";
    }
}
