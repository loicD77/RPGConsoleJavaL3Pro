public class Zombie extends Monster {
    public Zombie() {
        super("Zombie", 25, 3, 1);
    }

    @Override
    public String asciiArt() {
        return "  Z  \n" +
                " /|\\ \n" +
                " / \\ \n";
    }

    public void specialAbility() {
        System.out.println(name + " mord son adversaire !");
    }
}