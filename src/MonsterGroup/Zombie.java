package MonsterGroup;

import MonsterOriginal.Monster;
import Player.Player;

public class Zombie extends Monster {
    private boolean hasResurrected;
    private boolean isResting;
    private boolean isPlayerPoisoned;
    private int poisonTurns;
    private int attackBlockTurns;

    public Zombie(int playerLevel) {
        super("Zombie", "Mort-vivant", 30, 5, playerLevel, 10, 15, 12);
        this.hasResurrected = false;
        this.isResting = false;
        this.isPlayerPoisoned = false;
        this.poisonTurns = 0;
        this.attackBlockTurns = 0;
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
        if (isResting) {
            System.out.println("Le Zombie est en train de se reposer et ne peut pas attaquer ce tour-ci.");
            isResting = false;
            return;
        }

        if (attackBlockTurns > 0) {
            System.out.println("Le joueur ne peut pas attaquer pendant encore " + attackBlockTurns + " tour(s).");
            attackBlockTurns--;
        }

        int attackType = (int) (Math.random() * 4) + 1;
        switch (attackType) {
            case 1:
                System.out.println("Le Zombie inflige une morsure toxique !");
                player.takeDamage(5);
                isPlayerPoisoned = true;
                poisonTurns = -1; // Le poison dure jusqu'à la fin du combat
                break;
            case 2:
                System.out.println("Le Zombie inflige une attaque féroce qui empêche le joueur d'attaquer pendant 3 tours !");
                player.takeDamage(10);
                attackBlockTurns = 3;
                break;
            case 3:
                System.out.println("Le Zombie donne un coup puissant !");
                player.takeDamage(15);
                break;
            case 4:
                System.out.println("Le Zombie se jette sur le joueur avec toute sa force et inflige 40 dégâts, mais se repose ensuite.");
                player.takeDamage(40);
                isResting = true;
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
