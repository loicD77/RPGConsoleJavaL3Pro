import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int health;
    private int maxHealth;
    private Weapon equippedWeapon;
    private Armor equippedArmor;
    private int experience;
    private int level;
    private List<String> actionHistory;

    // Nouvelles statistiques
    private int strength;       // Influence les dégâts infligés
    private int agility;        // Influence l'esquive
    private int intelligence;   // Peut influencer l'utilisation d'objets spéciaux

    // Inventaire
    private List<Item> inventory;

    public Player(String name) {
        this.name = name;
        this.maxHealth = 100; // Points de vie maximum
        this.health = maxHealth; // Santé initiale
        this.equippedWeapon = null; // Pas d'arme équipée par défaut
        this.equippedArmor = null; // Pas d'armure équipée par défaut
        this.experience = 0; // Points d'expérience initiaux
        this.level = 1; // Niveau initial
        this.actionHistory = new ArrayList<>(); // Historique des actions
        this.strength = 10; // Force initiale
        this.agility = 10; // Agilité initiale
        this.intelligence = 10; // Intelligence initiale
        this.inventory = new ArrayList<>(); // Inventaire initial
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level; // Niveau du joueur
    }

    public int getMaxHealth() {
        return maxHealth; // Méthode ajoutée
    }

    public void equipWeapon(Weapon weapon) {
        this.equippedWeapon = weapon;
        logAction("a équipé " + weapon.getName());
        displayEquipment();
    }

    public void equipArmor(Armor armor) {
        this.equippedArmor = armor;
        logAction("a équipé " + armor.getName());
        displayEquipment();
    }

    public String asciiArt() {
        return "   O   \n" +
                "  /|\\  \n" +
                "  / \\  \n";
    }

    public int getHealth() {
        return health; // Santé actuelle
    }

    public void takeDamage(int damage) {
        int damageTaken = Math.max(damage - (equippedArmor != null ? equippedArmor.getDefense() : 0), 0);
        health -= damageTaken;
        health = Math.max(health, 0);
        System.out.println("Vous avez subi " + damageTaken + " dégâts ! Santé restante : " + health);
        logAction("a subi " + damageTaken + " dégâts");
    }

    public boolean isAlive() {
        return health > 0; // Vérifie si le joueur est vivant
    }

    public int attack() {
        if (equippedWeapon == null) {
            System.out.println("Vous n'avez pas d'arme équipée !");
            return 0; // Pas de dégâts si pas d'arme
        }

        int totalDamage = equippedWeapon.getDamage() + strength; // Dégâts totaux
        System.out.println("Vous attaquez avec " + equippedWeapon.getName() + " !");
        logAction("a attaqué avec " + equippedWeapon.getName());
        return totalDamage;
    }

    public void heal(int amount) {
        health = Math.min(health + amount, maxHealth); // Restauration de la santé
        System.out.println("Vous vous êtes soigné de " + amount + " points de vie. Santé actuelle : " + health);
        logAction("s'est soigné de " + amount + " points de vie");
    }

    public void levelUp() {
        level++;
        maxHealth += 20; // Augmente la santé maximale
        health = maxHealth; // Restaure la santé au maximum
        strength += 5; // Augmente la force
        agility += 2; // Augmente l'agilité
        intelligence += 3; // Augmente l'intelligence
        System.out.println("Vous êtes passé au niveau " + level + " !");
        logAction("a atteint le niveau " + level);
    }

    public void gainExperience(int amount) {
        experience += amount; // Augmente l'expérience
        System.out.println("Vous avez gagné " + amount + " points d'expérience !");
        logAction("a gagné " + amount + " points d'expérience");

        if (experience >= 100) {
            levelUp(); // Niveau supérieur si expérience suffisante
            experience -= 100; // Réinitialise l'expérience pour le prochain niveau
        }
    }

    public void displayStatus() {
        System.out.println("=== État du joueur ===");
        System.out.printf("Nom : %s\nNiveau : %d\nSanté : %d/%d\nForce : %d\nAgilité : %d\nIntelligence : %d\n",
                name, level, health, maxHealth, strength, agility, intelligence);
        displayEquipment();
        System.out.println("======================");
    }


    private void displayEquipment() {
        if (equippedWeapon != null) {
            System.out.println("Arme équipée : " + equippedWeapon.getName());
        } else {
            System.out.println("Aucune arme équipée.");
        }
        if (equippedArmor != null) {
            System.out.println("Armure équipée : " + equippedArmor.getName());
        } else {
            System.out.println("Aucune armure équipée.");
        }
    }

    private void logAction(String action) {
        actionHistory.add(action);
    }

    public List<String> getActionHistory() {
        return actionHistory; // Historique des actions
    }

    // Méthodes pour gérer l'inventaire
    public void addItemToInventory(Item item) {
        inventory.add(item);
        System.out.println("Vous avez ajouté " + item.getName() + " à votre inventaire.");
    }

    public void useItem(Item item) {
        if (inventory.contains(item)) {
            item.use(this); // Utilise l'objet
            inventory.remove(item);
        } else {
            System.out.println("Vous n'avez pas cet objet dans votre inventaire.");
        }
    }

    public List<Item> getInventory() {
        return inventory; // Retourne l'inventaire
    }
}
