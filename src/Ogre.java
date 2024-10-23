public class Ogre extends Monster {
    public Ogre() {
        super("Ogre", 40, 6, 1);
    }

    @Override
    public String asciiArt() {
        return "  O  \n" +
                " /|\\ \n" +
                " / \\ \n";
    }

    public void specialAbility() {
        System.out.println(name + " utilise sa force brute !");
    }
}