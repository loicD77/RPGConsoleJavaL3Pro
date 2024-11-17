package MonsterGroup;

import MonsterOriginal.Monster;
import Player.Player; // Import de Player

import java.util.Random;

public class Skeleton extends Monster {
    private Random random;

    public Skeleton(int level) {

        super("Squelette", "Mort-vivant", 25 + (level * 5), 4 + level, level, 10, 20, 12);
        // Ajustement de la santé et des dégâts selon le niveau
        this.random = new Random();
    }

    @Override
    public String asciiArt() {
        return "  💀  \n" +
                " /|\\ \n" +
                " / \\ \n";
    }

    @Override
    protected int specialAttack() {
        System.out.println("Le Squelette tire une flèche mortelle !");
        return getBaseDamage() * 2;
    }

    // Nouvelle méthode pour effectuer une attaque aléatoire
    public void performAttack(Player player) {
        int attackChoice = random.nextInt(3); // Choisir aléatoirement entre 3 attaques différentes

        switch (attackChoice) {
            case 0:
                boneSlash(player);
                break;
            case 1:
                arrowShot(player);
                break;
            case 2:
                specialAttack(player);
                break;
        }
    }

    // Attaque de coup d'os
    private void boneSlash(Player player) {
        int damage = getBaseDamage() + random.nextInt(5);
        System.out.println("Le Squelette vous attaque avec un coup d'os, infligeant " + damage + " dégâts !");
        player.takeDamage(damage);
    }

    // Attaque de tir à l'arc
    private void arrowShot(Player player) {
        int damage = getBaseDamage() - 2 + random.nextInt(5);
        System.out.println("Le Squelette tire une flèche sur vous, infligeant " + damage + " dégâts !");
        player.takeDamage(damage);
    }

    // Attaque spéciale avec une flèche mortelle
    private void specialAttack(Player player) {
        int damage = specialAttack();
        System.out.println("Le Squelette tire une flèche mortelle sur vous, infligeant " + damage + " dégâts !");
        player.takeDamage(damage);
    }

    // Surcharge de la méthode takeDamage pour une interaction spécifique au Squelette
    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);
        if (!isAlive()) {
            System.out.println("Le Squelette s'effondre en un tas d'os !");
        } else {
            System.out.println("Le Squelette claque des dents avec rage, prêt à continuer le combat !");
        }
    }

    // Méthode pour afficher l'état du Squelette avec des détails supplémentaires
    @Override
    public void displayStatus() {
        super.displayStatus(); // Appel à la méthode de la classe mère
        System.out.println("Dégâts d'attaque : " + getBaseDamage());
        System.out.println("Chance d'attaque spéciale : " + specialAttackChance + "%");
    }
}
