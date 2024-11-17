package MonsterGroup;

import MonsterOriginal.Monster;
import Player.Player;

import java.util.Random;

public class Cyclops extends Monster {
    // Un champ pour stocker une description spécifique de l'attaque spéciale
    private String specialAttackDescription;
    private Random random;

    public Cyclops(int playerLevel) {
        super("Cyclope", "Créature géante", 50, 8, playerLevel, 30, 40, 12); // Ajoutez une valeur pour experiencePoints
        this.specialAttackDescription = "Le Cyclope lance un puissant coup de poing !";
        this.random = new Random();
    }

    @Override
    public String asciiArt() {
        return "  👁  \n" +
                " /|\\ \n" +
                " / \\ \n";
    }

    @Override
    public void attack(Player player) {
        int attackChoice = random.nextInt(3); // Choisir aléatoirement une attaque
        switch (attackChoice) {
            case 0:
                basicAttack(player);
                break;
            case 1:
                clubSmash(player);
                break;
            case 2:
                specialAttack(player);
                break;
        }
    }

    private void basicAttack(Player player) {
        System.out.println("Le Cyclope donne un coup de poing au joueur !");
        int damage = getBaseDamage();
        player.takeDamage(damage);
        System.out.println("Le Cyclope inflige " + damage + " dégâts à " + player.getName() + ".");
    }

    private void clubSmash(Player player) {
        System.out.println("Le Cyclope utilise sa massue pour écraser le joueur !");
        int damage = getBaseDamage() + 5;
        player.takeDamage(damage);
        System.out.println("Le Cyclope inflige " + damage + " dégâts à " + player.getName() + " avec sa massue.");
    }

    @Override
    protected int specialAttack() {
        System.out.println(specialAttackDescription); // Affiche la description de l'attaque spéciale
        return getBaseDamage() * 2; // Attaque spéciale : un coup de poing puissant
    }

    private void specialAttack(Player player) {
        int damage = specialAttack();
        player.takeDamage(damage);
        System.out.println("Le Cyclope utilise son attaque spéciale et inflige " + damage + " dégâts à " + player.getName() + ".");
    }
}
