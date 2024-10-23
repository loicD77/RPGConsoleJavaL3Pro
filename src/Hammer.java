public class Hammer extends Weapon {
    public Hammer() {
        super("Marteau", 20, 12);
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
}
