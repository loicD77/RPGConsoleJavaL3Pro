public class Skeleton extends Monster {
    public Skeleton() {
        super("Squelette", 30, 5, 1);
    }

    @Override
    public String asciiArt() {
        return "  X  \n" +
                " /|\\ \n" +
                " / \\ \n";
    }

    public void specialAbility() {
        System.out.println(name + " lance une attaque rapide !");
    }
}