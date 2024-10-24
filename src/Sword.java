public class Sword extends Weapon {
    public Sword(String name, int damage, int price) {
        super(name, damage, price);
    }

    @Override
    public String asciiArt() {
        return "  /\\ \n" +
                " /  \\ \n" +
                "/____\\ \n" +
                "   || \n" +
                "   || \n" +
                "   || \n"; // Exemple simple d'art ASCII pour une épée
    }
}
