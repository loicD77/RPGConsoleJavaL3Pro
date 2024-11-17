package MonsterOriginal;

import java.util.Scanner;
import MainFiles.GameEntity;
import Interface.Attackable;
import Player.Player;

public abstract class Monster extends GameEntity implements Attackable {
    protected String description;
    protected int maxHealth; // Santé maximale
    protected int health; // Santé actuelle
    protected int damage; // Dégâts infligés
    protected int level; // Niveau du monstre
    protected int baseHealth; // Santé de base
    private boolean isResurrected; // Indicateur de résurrection
    protected int specialAttackChance; // Chance d'attaque spéciale
    protected int experiencePoints; // Points d'expérience

    protected int evasionChance; // Pourcentage de chance d'esquiver

    private int gold; // Champ pour l'or
    private boolean isResting = false; // Indique si le monstre est en repos
    private int restTurns = 0; // Nombre de tours restant pour le repos

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
        this.isResurrected = false;
        this.evasionChance = evasionChance;
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
            isResting = true; // Le monstre doit se reposer après une attaque spéciale
            restTurns = 1; // Par exemple, le monstre se repose pendant un tour
        }
        return attackDamage;
    }

    public void attack(Player player) {
        if (isResting) {
            System.out.println(name + " est en train de se reposer et ne peut pas attaquer ce tour-ci.");
            restTurns--;
            if (restTurns == 0) {
                isResting = false; // Fin du repos
            }
            return;
        }

        int attackDamage = damage + level; // Exemple de calcul des dégâts
        if (canUseSpecialAttack()) {
            System.out.println(name + " utilise une attaque spéciale !");
            attackDamage += specialAttack();
            isResting = true; // Le monstre doit se reposer après une attaque spéciale
            restTurns = 1; // Par exemple, le monstre se repose pendant un tour
        }

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

        // Gestion de la résurrection (si applicable)
        if (health == 0 && !isResurrected) {
            isResurrected = true;
            health = 15; // Exemple de valeur pour la santé après résurrection
            System.out.println(name + " se relève de ses cendres ! Il regagne 15 points de vie !");
        } else if (health == 0 && isResurrected) {
            System.out.println(name + " s'effondre, enfin vaincu !");
        }
    }


    // Méthode pour ajouter de l'or au monstre
    public void addGold(int amount) {
        if (amount > 0) {
            this.gold += amount;
            System.out.println(getName() + " a gagné " + amount + " pièces d'or. Total d'or : " + this.gold);
        }
    }

    // Getter pour la chance d'esquiver
    public int getEvasionChance() {
        return evasionChance;
    }

    // Setter pour la chance d'esquiver
    public void setEvasionChance(int evasionChance) {
        this.evasionChance = evasionChance;
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
}
