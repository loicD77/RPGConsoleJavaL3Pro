package MonsterGroup;
import MonsterOriginal.Monster;

public class Troll extends Monster {
    // Variable pour stocker si le Troll a régénéré sa santé
    private boolean hasRegenerated;

    public Troll(int level) {
        super("Troll", "Créature puissante", 50, 10, level, 15, 30, 5); // 5 est la valeur pour l'or
        this.hasRegenerated = false; // Par défaut, le Troll n'a pas encore régénéré sa santé
    }

    @Override
    public String asciiArt() {
        return "  👺  \n" +
                " /|\\ \n" +
                " / \\ \n";
    }

    // Attaque spéciale du Troll
    @Override
    protected int specialAttack() {
        System.out.println("Le Troll brandit une massue pour une attaque brutale !");
        return getBaseDamage() * 2; // Attaque spéciale : dégâts doublés
    }

    // Le Troll peut régénérer sa santé une fois lorsqu'il est gravement blessé
    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage); // Appel à la méthode de la classe parente

        // Vérifie si la santé descend en dessous de 30% et s'il n'a pas encore régénéré
        if (health <= getBaseHealth() * 0.3 && !hasRegenerated) {
            regenerate();
            hasRegenerated = true; // Le Troll ne peut régénérer qu'une seule fois
        }

        // Message selon l'état du Troll
        if (!isAlive()) {
            System.out.println("Le Troll s'écroule dans un rugissement de colère !");
        } else {
            System.out.println("Le Troll grogne de rage, ses blessures semblant à peine le ralentir.");
        }
    }

    // Méthode de régénération pour le Troll
    public void regenerate() {
        int healAmount = getBaseHealth() / 2; // Le Troll regagne 50% de sa santé de base
        health += healAmount;
        health = Math.min(health, maxHealth); // Ne pas dépasser la santé maximale
        System.out.println("Le Troll régénère ses blessures et récupère " + healAmount + " points de vie !");
    }

    @Override
    public void displayStatus() {
        super.displayStatus(); // Affiche l'état de base du monstre
        System.out.println("Régénération disponible : " + (!hasRegenerated ? "Oui" : "Non"));
    }
}
