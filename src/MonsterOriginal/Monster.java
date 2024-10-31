package MonsterOriginal;

import java.util.Scanner;
import Entity.GameEntity; // Assurez-vous que cette ligne est présente
import Interface.Attackable;
import Player.Player; // Ajoutez cet import pour utiliser la classe Player


public abstract class Monster extends GameEntity implements Attackable {
    protected String description;
    protected int maxHealth; // Santé maximale
    protected int health; // Santé actuelle
    protected int damage; // Dégâts infligés
    protected int level; // Niveau du monstre
    protected int baseHealth; // Santé de base
    protected int specialAttackChance; // Chance d'attaque spéciale
    protected int experiencePoints; // Points d'expérience
    private int gold; // Champ pour l'or

    // Constructeur
    public Monster(String name, String description, int maxHealth, int damage, int level, int specialAttackChance, int experiencePoints, int gold) {
        super(name); // Appel du constructeur de GameEntity avec le nom du monstre
        this.description = description;
        this.maxHealth = maxHealth;
        this.health = maxHealth; // Initialise la santé actuelle à la santé maximale
        this.damage = damage;
        this.level = level;
        this.specialAttackChance = specialAttackChance; // Initialise specialAttackChance
        this.baseHealth = maxHealth; // Initialise baseHealth
        this.experiencePoints = experiencePoints;
        this.gold = gold; // Initialiser l'or du monstre
    }


    public int getGold() {
        return gold; // Méthode pour obtenir l'or du monstre
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public int getBaseDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getBaseHealth() {
        return baseHealth;
    }

    public abstract String asciiArt();

    public int attack() {
        int attackDamage = damage + level;
        if (canUseSpecialAttack()) {
            System.out.println(name + " utilise une attaque spéciale !");
            attackDamage += specialAttack();
        }
        return attackDamage;
    }

    public void attack(Player player) {
        int attackDamage = damage + level; // Exemple de calcul des dégâts
        System.out.println(name + " attaque " + player.getName() + " et inflige " + attackDamage + " dégâts !");
        player.takeDamage(attackDamage); // Supposons que Player a une méthode takeDamage
    }


    private boolean canUseSpecialAttack() {
        return Math.random() * 100 < specialAttackChance;
    }

    protected int specialAttack() {
        return damage * 2; // Une attaque spéciale fait 2x les dégâts normaux
    }

    public void takeDamage(int damage) {
        health -= damage;
        health = Math.max(health, 0); // Ne pas descendre en dessous de 0
        System.out.println(name + " a subi " + damage + " dégâts ! Santé restante : " + health);
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void displayStatus() {
        System.out.println("=== État du Monstre ===");
        System.out.println("Nom : " + name);
        System.out.println("Type : " + description);
        System.out.println("Santé : " + health + "/" + maxHealth);
        System.out.println("Niveau : " + level);
        System.out.println("========================");
    }


    private void handleObstacle(Player player, Obstacle obstacle) {
        System.out.println("Vous rencontrez un obstacle : " + obstacle.getName() + " (PV: " + obstacle.getHealth() + ")");

        while (obstacle.isAlive()) {
            System.out.println("Que voulez-vous faire ? (1: Attaquer, 2: Fuire)");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.println("Choisissez votre type d'attaque : (1: Coup de poing, 2: Attaque puissante, 3: Attaque rapide, 4: Attaque spéciale) : ");
                int attackType = scanner.nextInt();
                player.attack(obstacle, attackType); // Attaque l'obstacle
            } else if (choice == 2) {
                System.out.println("Vous avez fui !");
                return;
            }
        }
    }

    private void handleMonster(Player player, Monster monster) {
        System.out.println("Un " + monster.getName() + " apparaît !");

        while (player.isAlive() && monster.isAlive()) {
            System.out.println("Que voulez-vous faire ? (1: Attaquer, 2: Fuire)");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.println("Choisissez votre type d'attaque : (1: Coup de poing, 2: Attaque puissante, 3: Attaque rapide, 4: Attaque spéciale) : ");
                int attackType = scanner.nextInt();
                player.attack(monster, attackType); // Attaque le monstre
            } else if (choice == 2) {
                System.out.println("Vous avez fui !");
                return;
            }
        }
    }


    public String healthBasedBehavior() {
        if (health > baseHealth * 0.75) {
            return name + " semble en pleine forme, prêt à combattre avec fureur !";
        } else if (health > baseHealth * 0.5) {
            return name + " commence à montrer des signes de fatigue...";
        } else if (health > baseHealth * 0.25) {
            return name + " est gravement blessé mais continue de se battre courageusement.";
        } else {
            return name + " est presque à bout, vacille sur ses jambes...";
        }
    }

    public void die() {
        System.out.println(name + " a été vaincu !");
    }
}
