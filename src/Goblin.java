public class Goblin extends Monster {
    public Goblin() {
        super("Gobelin", 20, 4, 1);
    }

    @Override
    public String asciiArt() {
        return "  G  \n" +
                " /|\\ \n" +
                " / \\ \n";
    }

    public void specialAbility() {
        System.out.println(name + " vole une arme à son adversaire !");
    }
}