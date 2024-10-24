public class Cyclops extends Monster {
    // Un champ pour stocker une description spécifique de l'attaque spéciale
    private String specialAttackDescription;

    public Cyclops(int playerLevel) {
        super("Cyclope", "Créature géante", 50, 8, playerLevel, 30, 40, 12); // Ajoutez une valeur pour experiencePoints
        this.specialAttackDescription = "Le Cyclope lance un puissant coup de poing !";
    }

    @Override
    public String asciiArt() {
        return "  👁  \n" +
                " /|\\ \n" +
                " / \\ \n";
    }

    @Override
    protected int specialAttack() {
        System.out.println(specialAttackDescription); // Affiche la description de l'attaque spéciale
        return getBaseDamage() * 2; // Attaque spéciale : un coup de poing puissant
    }

    // Surcharge de la méthode takeDamage pour ajouter une interaction spécifique au Cyclope
    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);
        if (!isAlive()) {
            System.out.println("Le Cyclope s'effondre sous le poids de ses blessures !");
        } else {
            System.out.println("Le Cyclope rugit de douleur !");
        }
    }

    // Méthode pour afficher l'état du Cyclope avec des détails supplémentaires
    @Override
    public void displayStatus() {
        super.displayStatus(); // Appel à la méthode de la classe mère
        System.out.println("Description : " + description);
        System.out.println("Dégâts d'attaque : " + damage);
        System.out.println("Chance d'attaque spéciale : " + specialAttackChance + "%");
    }
}
