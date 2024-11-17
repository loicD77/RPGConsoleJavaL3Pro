package MonsterGroup;

import MonsterOriginal.Monster;
import Player.Player; // Import de Player

import java.util.Random;

public class Dragon extends Monster {
    // Une chaîne pour stocker le type de feu du dragon
    private String fireType;
    private Random random;

    public Dragon(int level) {
        super("Dragon", "Une créature légendaire", 100 + (level * 10), 15 + level, level, 20, 50, 24);
        this.fireType = "feu infernal"; // Type de feu par défaut
        this.random = new Random();
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

    // Nouvelle méthode pour effectuer une attaque aléatoire
    @Override
    public void attack(Player player) {
        if (!isAlive()) {
            return; // Ne pas attaquer si le dragon est déjà mort
        }

        int attackChoice = random.nextInt(3); // Choisir aléatoirement entre 3 attaques différentes

        switch (attackChoice) {
            case 0:
                biteAttack(player);
                break;
            case 1:
                tailSwipe(player);
                break;
            case 2:
                performSpecialAttack(player);
                break;
        }
    }

    // Attaque morsure du dragon
    private void biteAttack(Player player) {
        int damage = getBaseDamage() + random.nextInt(10);
        System.out.println("Le Dragon vous attaque avec une morsure féroce, infligeant " + damage + " dégâts !");
        player.takeDamage(damage);
    }

    // Attaque de balayage de la queue
    private void tailSwipe(Player player) {
        int damage = getBaseDamage() - 5 + random.nextInt(10);
        System.out.println("Le Dragon balance sa queue avec force, vous frappant et infligeant " + damage + " dégâts !");
        player.takeDamage(damage);
    }

    // Attaque spéciale avec du souffle de feu
    private void performSpecialAttack(Player player) {
        int damage = specialAttack();
        System.out.println("Le Dragon souffle du " + fireType + " sur vous, infligeant " + damage + " dégâts !");
        player.takeDamage(damage);
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
