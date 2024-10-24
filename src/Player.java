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
    private int agility;
    private int strength;
    private int intelligence;
    private List<Item> inventory;
    private String asciiFace; // Visage ASCII du personnage
    private List<DungeonPiece> unlockedDungeonPieces;
    private int gold;


    public Player(String name, String asciiFace) {
        this.name = name;
        this.asciiFace = asciiFace; // Initialisation du visage ASCII
        this.maxHealth = 100;
        this.health = maxHealth;
        this.equippedWeapon = null;
        this.equippedArmor = null;
        this.experience = 0;
        this.level = 1;
        this.actionHistory = new ArrayList<>();
        this.strength = 10;
        this.agility = 10;
        this.intelligence = 10;
        this.inventory = new ArrayList<>();
        this.unlockedDungeonPieces = new ArrayList<>();
        this.gold = 0; // Initialiser l'or à 0
    }


    // Méthode pour ajouter un objet à l'inventaire
    public void addItemToInventory(Item item) {
        inventory.add(item);
        System.out.println("Vous avez ajouté " + item.getName() + " à votre inventaire.");
    }

    public int getHealth() {return health; }

    // Méthodes pour obtenir des informations sur le joueur
    public String getName() {
        return name;
    }

    // Méthode pour obtenir l'agilité
    public int getAgility() {
        return agility;
    }
    public int getLevel() {
        return level; // Méthode pour obtenir le niveau
    }

    public int getGold() {
        return gold;
    }

    public boolean isAlive() {
        return health > 0; // Ajout de la méthode isAlive
    }

    public void addGold(int amount) {
        this.gold += amount;
        System.out.println("Vous avez gagné " + amount + " pièces d'or. Or actuel : " + gold);
    }

    public void spendGold(int amount) {
        if (gold >= amount) {
            this.gold -= amount;
            System.out.println("Vous avez dépensé " + amount + " pièces d'or. Or restant : " + gold);
        } else {
            System.out.println("Vous n'avez pas assez d'or !");
        }
    }

    // Autres méthodes...

    public void equipWeapon(Weapon weapon) {
        this.equippedWeapon = weapon;
        logAction("a équipé " + weapon.getName());
    }

    public void heal(int amount) {
        health = Math.min(health + amount, maxHealth);
        logAction("s'est soigné de " + amount + " points de vie");
    }

    public void gainExperience(int amount) {
        experience += amount;
        logAction("a gagné " + amount + " points d'expérience");
        if (experience >= 100) {
            levelUp();
            experience -= 100;
        }
    }

    private void logAction(String action) {
        actionHistory.add(action);
    }

    public void levelUp() {
        level++;
        maxHealth += 20;
        health = maxHealth;
        strength += 5;
        agility += 2;
        intelligence += 3;
        System.out.println("Niveau supérieur ! Vous êtes maintenant niveau " + level);
        logAction("a atteint le niveau " + level);
    }

    public void displayEquipment() {
        System.out.println("Équipement actuel :");
        if (equippedWeapon != null) {
            System.out.println("Arme : " + equippedWeapon.getDescription());
        } else {
            System.out.println("Aucune arme équipée.");
        }
    }

    public int attack() {
        if (equippedWeapon != null) {
            int damage = strength + equippedWeapon.getDamage();
            System.out.println(name + " attaque avec " + equippedWeapon.getName() + " et inflige " + damage + " dégâts !");
            return damage;
        } else {
            System.out.println(name + " attaque mais n'a pas d'arme équipée !");
            return strength; // Dégâts de base si aucune arme n'est équipée
        }
    }

    public void takeDamage(int damage) {
        health -= damage;
        System.out.println(name + " subit " + damage + " dégâts !");

        if (health <= 0) {
            health = 0; // S'assurer que la santé ne devient pas négative
            System.out.println(name + " est mort !");
        }
    }

    public void displayStatus() {
        System.out.println("=== État du joueur ===");
        System.out.printf("Nom : %s%n", name);
        System.out.printf("Niveau : %d%n", level);
        System.out.printf("Santé : %d/%d%n", health, maxHealth);
        System.out.printf("Force : %d%n", strength);
        System.out.printf("Agilité : %d%n", agility);
        System.out.printf("Intelligence : %d%n", intelligence);
        System.out.printf("Or : %d%n", gold);
        displayEquipment();

        // État du joueur
        if (health == 0) {
            System.out.println("Vous êtes à terre. Trouvez un moyen de vous soigner !");
        } else if (health < maxHealth * 0.3) {
            System.out.println("Attention : votre santé est critique !");
        } else if (health < maxHealth) {
            System.out.println("Vous avez des blessures, soyez prudent !");
        } else {
            System.out.println("Vous êtes en pleine forme !");
        }

        System.out.println("======================");
    }

    // Méthode pour afficher l'ASCII art du personnage
    public String asciiArt() {
        return
                "    "+ asciiFace + "     \n" +  // Utiliser le visage ASCII choisi
                        "    /|\\    \n" +  // Bras
                        "     |     \n" +  // Corps
                        "    / \\    \n" +  // Jambes
                        "Nom : " + name + "\n" +
                        "Niveau : " + level + "\n" +
                        "PV : " + health + "\n" +
                        "Or : " + gold;
    }
    public String getAsciiFace() {
        return asciiFace; // Assurez-vous que 'asciiFace' est un champ de la classe Player
    }

}

