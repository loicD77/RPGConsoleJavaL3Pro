public abstract class Monster {
    protected String name;
    protected String description;
    protected int maxHealth; // Santé maximale
    protected int health; // Santé actuelle
    protected int damage; // Dégâts infligés
    protected int level; // Niveau du monstre
    protected int baseHealth; // Santé de base
    protected int specialAttackChance; // Chance d'attaque spéciale

    protected int experiencePoints; // Ajout d'un champ pour les points d'expérience

    private int gold; // Champ pour l'or

    public Monster(String name, String description, int maxHealth, int damage, int level, int specialAttackChance, int experiencePoints, int gold) {
        this.name = name;
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


    // Méthode pour obtenir les dégâts de base
    public int getBaseDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }

    // Méthode pour obtenir la santé actuelle
    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getBaseHealth() {
        return baseHealth;
    }

    // Méthode abstraite pour l'ASCII art de chaque monstre
    public abstract String asciiArt();

    // Calcul des dégâts d'attaque
    public int attack() {
        int attackDamage = damage + level; // Dégâts en fonction du niveau
        if (canUseSpecialAttack()) {
            System.out.println(name + " utilise une attaque spéciale !");
            attackDamage += specialAttack(); // Augmentation des dégâts
        }
        return attackDamage;
    }

    // Méthode pour décider si le monstre utilise une attaque spéciale
    private boolean canUseSpecialAttack() {
        return Math.random() * 100 < specialAttackChance;
    }

    // Méthode pour définir les dégâts d'une attaque spéciale
    protected int specialAttack() {
        return damage * 2; // Une attaque spéciale fait 2x les dégâts normaux
    }

    // Réduit les points de vie du monstre et ajuste sa santé
    public void takeDamage(int damage) {
        health -= damage;
        health = Math.max(health, 0); // Ne pas descendre en dessous de 0
        System.out.println(name + " a subi " + damage + " dégâts ! Santé restante : " + health);
    }

    // Vérifie si le monstre est encore en vie
    public boolean isAlive() {
        return health > 0;
    }

    // Méthode pour afficher l'état du monstre
    public void displayStatus() {
        System.out.println("=== État du Monstre ===");
        System.out.println("Nom : " + name);
        System.out.println("Type : " + description);
        System.out.println("Santé : " + health + "/" + maxHealth);
        System.out.println("Niveau : " + level);
        System.out.println("========================");
    }

    // Ajout de comportements différents selon la santé restante
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

    // Méthode pour gérer la mort du monstre
    public void die() {
        System.out.println(name + " a été vaincu !");
    }
}
