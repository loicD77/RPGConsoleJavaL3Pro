public class Dragon extends Monster {
    // Une chaîne pour stocker le type de feu du dragon
    private String fireType;

    public Dragon(int level) {
        super("Dragon", "Une créature légendaire", 100 + (level * 10), 15 + level, level, 20, 50, 24);
    }

    @Override
    public String asciiArt() {
        return "   /\\   \n" +
                "  /  \\  \n" +
                " |    | \n" +
                "  \\__/  \n" +
                "   /\\   \n" +
                "  /  \\  \n";
    }

    @Override
    protected int specialAttack() {
        System.out.println("Le Dragon souffle du " + fireType + " !");
        return getBaseDamage() * 3; // Attaque spéciale : souffle de feu
    }

    // Surcharge de la méthode takeDamage pour une interaction spécifique au Dragon
    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);
        if (!isAlive()) {
            System.out.println("Le Dragon s'effondre, ses ailes repliées contre lui !");
        } else {
            System.out.println("Le Dragon rugit avec colère, prêt à se venger !");
        }
    }

    // Méthode pour afficher l'état du Dragon avec des détails supplémentaires
    @Override
    public void displayStatus() {
        super.displayStatus(); // Appel à la méthode de la classe mère
        System.out.println("Type de feu : " + fireType);
        System.out.println("Dégâts d'attaque : " + getBaseDamage());
        System.out.println("Chance d'attaque spéciale : " + specialAttackChance + "%");
    }
}
