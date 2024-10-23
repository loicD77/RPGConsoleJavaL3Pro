public class Dragon extends Monster {
    public Dragon() {
        super("Dragon", 100, 15, 1);
    }

    @Override
    public String asciiArt() {
        return "   /\\   \n" +
                "  /__\\  \n" +
                "  |__|  \n";
    }

    public void specialAbility() {
        System.out.println(name + " crache du feu !");
    }
}