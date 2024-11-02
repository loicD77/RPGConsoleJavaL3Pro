package MonsterOriginal;

import Player.Player;
import java.util.Random;

public class FinalBoss extends Monster {
    private boolean isResting; // Indicateur pour savoir si le boss est en train de se reposer
    private boolean playerIsPoisoned; // Indicateur pour savoir si le joueur est empoisonné
    private int poisonTurnsLeft; // Nombre de tours où le poison fait effet
    private Random random;

    public FinalBoss() {
        super("Boss Final", "Un boss redoutable qui attend le joueur.", 500, 50, 5, 10, 100, 500);
        this.isResting = false;
        this.playerIsPoisoned = false;
        this.poisonTurnsLeft = 0;
        this.random = new Random();
    }

    @Override
    public String asciiArt() {
        return "      _____       \n" +
                "     /     \\      \n" +
                "    |  O O  |     \n" +
                "    \\   ^   /     \n" +
                "     \\_____/      \n" +
                "       | |        \n" +
                "      /   \\       \n";
    }

    @Override
    protected int specialAttack() {
        return getBaseDamage() * 2; // Attaque spéciale infligeant des dégâts doublés
    }

    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);
        if (!isAlive()) {
            System.out.println("Le Boss Final s'effondre, vaincu !");
        } else {
            System.out.println("Le Boss Final rugit de rage !");
        }
    }

    public void poisonPlayer(Player player) {
        playerIsPoisoned = true;
        poisonTurnsLeft = 5;
        System.out.println("Le Boss Final vous a empoisonné ! Vous perdrez des points de vie pendant 5 tours !");
    }

    @Override
    public void attack(Player player) {
        if (isResting) {
            System.out.println("Le Boss Final est en train de se reposer et regagne beaucoup de points de vie !");
            health += 100; // Récupération de 100 PV
            if (health > maxHealth) {
                health = maxHealth; // Ne pas dépasser les PV max
            }
            System.out.println("PV du Boss Final : " + health);
            isResting = false; // Fin du repos
            return;
        }

        if (random.nextInt(100) < specialAttackChance) {
            System.out.println("Le Boss Final utilise une attaque spéciale !");
            int damage = specialAttack();
            System.out.println("Le Boss Final inflige " + damage + " points de dégâts !");
            player.takeDamage(damage);
            return;
        }

        int attackChoice = random.nextInt(4);
        switch (attackChoice) {
            case 0:
                System.out.println("Le Boss Final lance un coup puissant !");
                player.takeDamage(damage + 20);
                break;
            case 1:
                System.out.println("Le Boss Final empoisonne le joueur !");
                poisonPlayer(player);
                break;
            case 2:
                System.out.println("Le Boss Final attaque avec ses griffes !");
                player.takeDamage(damage);
                break;
            case 3:
                System.out.println("Le Boss Final décide de se reposer pendant 1 tour.");
                isResting = true;
                break;
        }

        // Si le joueur est empoisonné, il perd des points de vie à chaque tour
        if (playerIsPoisoned) {
            int poisonDamage = 10;
            player.takeDamage(poisonDamage);
            System.out.println("Vous perdez " + poisonDamage + " points de vie à cause du poison. Tours restants : " + poisonTurnsLeft);
            poisonTurnsLeft--;

            if (poisonTurnsLeft <= 0) {
                playerIsPoisoned = false; // Fin de l'effet du poison
                System.out.println("L'effet du poison s'est dissipé.");
            }
        }
    }

    @Override
    public void displayStatus() {
        super.displayStatus();
        System.out.println("Dégâts d'attaque : " + getBaseDamage());
        System.out.println("Points d'expérience à gagner : " + getExperiencePoints());
    }
}
