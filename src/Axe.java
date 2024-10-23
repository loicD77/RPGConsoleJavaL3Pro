public class Axe extends Weapon {
    public Axe() {
        super("Hache", 15, 30); // Exemple avec un prix de 30
    }

    @Override
    public String asciiArt() {
        return
                "   /\\ \n" +
                        "  /__\\ \n" +
                        "  \\  \\\n" +
                        "   \\/  \n";
    }
}
