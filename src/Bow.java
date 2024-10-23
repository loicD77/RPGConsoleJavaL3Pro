public class Bow extends Weapon {
    public Bow() {
        super("Arc", 10,20);
    }

    @Override
    public String asciiArt() {
        return
                "  \\  \n" +
                        "   \\ \n" +
                        "    O\n" +
                        "   / \n" +
                        "  /  \n";
    }
}
