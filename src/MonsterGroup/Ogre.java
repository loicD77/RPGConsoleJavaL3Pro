package MonsterGroup;
import MonsterOriginal.Monster;
import Player.Player;

import java.util.Random;

public class Ogre extends Monster {

    // Variable pour stocker l'état de rage de l'Ogre
    private boolean enraged;
    private Random random;

    public Ogre(int level) {
        super("Ogre", "Une créature massive", 50 + (level * 5), 10 + level, level, 5, 30, 17);
        this.random = new Random();
    }

    @Override
    public String asciiArt() {
        return "  O  \n" +
                " /|\\ \n" +
                " / \\ \n";
    }

    @Override
    public void attack(Player player) {
        int attackChoice = random.nextInt(4); // Choisir aléatoirement une attaque parmi 4
        switch (attackChoice) {
            case 0:
                basicAttack(player);
                break;
            case 1:
                clubSmash(player);
                break;
            case 2:
                groundSlam(player);
                break;
            case 3:
                specialAttack(player);
                break;
        }
    }

    private void basicAttack(Player player) {
        System.out.println("L'Ogre donne un coup de poing au joueur !");
        int damage = getBaseDamage();
        player.takeDamage(damage);
        System.out.println("L'Ogre inflige " + damage + " dégâts à " + player.getName() + ".");
    }

    private void clubSmash(Player player) {
        System.out.println("L'Ogre utilise sa massue pour écraser le joueur !");
        int damage = getBaseDamage() + 5;
        player.takeDamage(damage);
        System.out.println("L'Ogre inflige " + damage + " dégâts à " + player.getName() + " avec sa massue.");
    }

    private void groundSlam(Player player) {
        System.out.println("L'Ogre frappe le sol avec une telle force qu'il provoque une onde de choc !");
        int damage = getBaseDamage() + 8;
        player.takeDamage(damage);
        System.out.println("L'Ogre inflige " + damage + " dégâts à " + player.getName() + " avec son coup au sol.");
    }

    @Override
    protected int specialAttack() {
        if (!enraged) {
            System.out.println("L'Ogre s'énerve et entre dans une rage furieuse !");
            enraged = true; // L'Ogre devient enragé après sa première attaque spéciale
        }
        System.out.println("L'Ogre utilise sa force brute enragée !");
        return getBaseDamage() + 10; // L'attaque spéciale devient plus puissante si l'Ogre est enragé
    }

    private void specialAttack(Player player) {
        int damage = specialAttack();
        player.takeDamage(damage);
        System.out.println("L'Ogre utilise son attaque spéciale et inflige " + damage + " dégâts à " + player.getName() + ".");
    }

    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);
        if (isAlive()) {
            System.out.println("L'Ogre semble devenir encore plus furieux à mesure qu'il subit des dégâts !");
        } else {
            System.out.println("L'Ogre s'effondre dans un fracas immense !");
        }
    }

    @Override
    public void displayStatus() {
        super.displayStatus();
        System.out.println("État de rage : " + (enraged ? "Enragé" : "Calme"));
    }

    // L'Ogre peut se soigner légèrement s'il est enragé
    public void regenerate() {
        if (enraged) {
            int healAmount = 10;
            health += healAmount;
            health = Math.min(health, maxHealth); // Ne pas dépasser la santé maximale
            System.out.println("L'Ogre enragé se régénère et récupère " + healAmount + " points de vie !");
        }
    }
}
