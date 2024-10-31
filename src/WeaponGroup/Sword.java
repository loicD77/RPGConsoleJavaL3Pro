public class Sword extends Weapon {
    public Sword(String name, int damage, int price) {
        super(name, "Une épée tranchante", damage, price); // Assure-toi d'ajouter le quatrième paramètre attendu
    }

    @Override
    public void use(Player player) {
        System.out.println("Vous utilisez l'épée " + getName() + " pour attaquer avec " + getDamage() + " points de dégâts !");
    }

    @Override
    public String asciiArt() {
        return "  /\\ \n" +
                " /  \\ \n" +
                "/____\\ \n" +
                "   || \n" +
                "   || \n" +
                "   || \n";
    }
}
