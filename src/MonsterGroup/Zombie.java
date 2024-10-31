package MonsterGroup;

import MonsterOriginal.Monster;
import Player.Player;

public class Zombie extends Monster {
    private boolean hasResurrected;

    public Zombie(int playerLevel) {
        super("Zombie", "Mort-vivant", 30, 5, playerLevel, 10, 15, 12);
        this.hasResurrected = false;
    }

    @Override
    public String asciiArt() {
        return "  Z  \n" +
                " /|\\ \n" +
                " / \\ \n";
    }

    @Override
    protected int specialAttack() {
        System.out.println("Le Zombie mord férocement avec ses dents pourries !");
        return getBaseDamage() * 2;
    }

    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);
        if (!isAlive() && !hasResurrected) {
            resurrect();
            hasResurrected = true;
        }
        if (!isAlive()) {
            System.out.println("Le Zombie s'effondre, enfin vaincu !");
        } else {
            System.out.println("Le Zombie gémit et se traîne encore... toujours en vie !");
        }
    }

    private void resurrect() {
        int resurrectHealth = getBaseHealth() / 2;
        health = resurrectHealth;
        System.out.println("Le Zombie se relève de ses cendres ! Il regagne " + resurrectHealth + " points de vie !");
    }

    @Override
    public void attack(Player player) {
        int attackType = (int) (Math.random() * 4) + 1;
        switch (attackType) {
            case 1:
                System.out.println("Le Zombie donne un coup de griffes !");
                player.takeDamage(getBaseDamage());
                break;
            case 2:
                System.out.println("Le Zombie mord avec ses dents pourries !");
                player.takeDamage(getBaseDamage() + 3);
                break;
            case 3:
                System.out.println("Le Zombie vomit un liquide toxique !");
                player.takeDamage(getBaseDamage() - 1);
                if (Math.random() < 0.5) {
                    System.out.println("Le joueur est empoisonné par le liquide toxique !");
                }
                break;
            case 4:
                System.out.println("Le Zombie se jette sur le joueur avec toute sa force !");
                player.takeDamage(getBaseDamage() * 2);
                break;
            default:
                System.out.println("Le Zombie semble confus...");
                break;
        }
    }

    @Override
    public void displayStatus() {
        super.displayStatus();
        System.out.println("Résurrection possible : " + (!hasResurrected ? "Oui" : "Non"));
    }
}
