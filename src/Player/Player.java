package Player;

import java.util.ArrayList;
import java.util.List;

// Imports des autres packages du projet
import WeaponOriginal.Weapon;
import ProtectiveClothing.ProtectionItem;
import ProtectiveClothing.Armor;
import ProtectiveClothing.Helmet;
import ProtectiveClothing.Shield;
import ProtectiveClothing.Boots;
import ProtectiveClothing.Pants;
import ProtectiveClothing.Gloves;
import Item.Inventory;
import Item.Item;
import MonsterOriginal.Monster;
import Dungeon.DungeonPiece;
import PotionGroup.Potion;
import PotionGroup.StatusEffect;
import MonsterOriginal.Obstacle;
import MonsterGroup.Boss;
import Interface.Attackable; // Importez l'interface Attackable pour pouvoir l'utiliser

public class Player {
    private String name;
    private int health;
    private int maxHealth;
    private int baseDamage = 10;
    private int gold; // Or du joueur

    private Weapon equippedWeapon;
    private Armor equippedArmor;
    private int experience;
    private int level;
    private int defense;
    private List<String> actionHistory;
    private int agility;
    private int strength;
    private int intelligence;
    private Inventory inventory; // Inventaire principal
    private Inventory equippedItems; // Inventaire équipé

    private String asciiFace;
    private List<DungeonPiece> unlockedDungeonPieces;

    private List<StatusEffect> activeEffects;
    private List<Potion> potions;
    private Helmet equippedHelmet;
    private Shield equippedShield;
    private Boots equippedBoots;
    private Pants equippedPants;
    private Gloves equippedGloves;

    private int experiencePoints; // Points d'expérience accumulés
    private int experienceToNextLevel; // Points nécessaires pour passer au niveau suivant

    private int playerX; // Position du joueur en X
    private int playerY; // Position du joueur en Y

    public Player(String name, String asciiFace) {
        this.name = name;
        this.asciiFace = asciiFace;
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
        this.defense = 10;
        this.inventory = new Inventory();
        this.unlockedDungeonPieces = new ArrayList<>();
        this.gold = 0;
        this.activeEffects = new ArrayList<>();
        this.potions = new ArrayList<>();
        this.equippedItems = new Inventory();
        this.playerX = 1; // Position initiale
        this.playerY = 1; // Position initiale
    }

    public void displayStatus() {
        System.out.println("=== Statut du Joueur ===");
        System.out.println("Nom : " + name);
        System.out.println("Niveau : " + level);
        System.out.println("PV : " + health + "/" + maxHealth);
        System.out.println("Or : " + gold);
        System.out.println("Force : " + strength);
        System.out.println("Agilité : " + agility);
        System.out.println("Intelligence : " + intelligence);
        System.out.println("Défense : " + defense);

        System.out.println("Arme équipée : " + (equippedWeapon != null ? equippedWeapon.getName() : "Aucune arme équipée."));
        System.out.println("Armure équipée : " + (equippedArmor != null ? equippedArmor.getName() : "Aucune armure équipée."));
        System.out.println("Casque équipé : " + (equippedHelmet != null ? equippedHelmet.getName() : "Aucun casque équipé."));
        System.out.println("Bouclier équipé : " + (equippedShield != null ? equippedShield.getName() : "Aucun bouclier équipé."));
        System.out.println("Bottes équipées : " + (equippedBoots != null ? equippedBoots.getName() : "Aucune botte équipée."));
        System.out.println("Pantalon équipé : " + (equippedPants != null ? equippedPants.getName() : "Aucun pantalon équipé."));
        System.out.println("Gants équipés : " + (equippedGloves != null ? equippedGloves.getName() : "Aucun gant équipé."));
        showInventory();
        System.out.println("========================");
        System.out.println("Apparence du Personnage :");
        System.out.println("    " + asciiFace);
        System.out.println("    /|\\ ");
        System.out.println("     |  ");
        System.out.println("    / \\ ");
        System.out.println("========================");
    }

    public void equipWeapon(String weaponName) {
        for (Item item : inventory.getItems()) {
            if (item instanceof Weapon && item.getName().equals(weaponName)) {
                equippedWeapon = (Weapon) item;
                System.out.println("Vous avez équipé " + equippedWeapon.getName() + ".");
                if (equippedWeapon.getName().equalsIgnoreCase("Marteau")) {
                    System.out.println("Vous avez maintenant accès aux attaques spécifiques du Marteau.");
                }
                return; // Quitte la méthode après avoir équipé l'arme
            }
        }
        System.out.println("L'arme " + weaponName + " n'est pas dans votre inventaire.");
    }


    public void equipArmor(Armor armor) {
        if (armor != null && inventory.contains(armor)) {
            equippedArmor = armor;
            equippedItems.addItem(armor); // Ajoute l'armure à l'inventaire équipé
            System.out.println("Vous avez équipé " + armor.getName() + ".");
        } else {
            System.out.println("L'armure n'est pas dans votre inventaire.");
        }
    }

    // Méthode pour équiper un objet de protection
    public void equipProtectionItem(ProtectionItem item) {
        if (item instanceof Armor) {
            // Equipe une armure
            Armor armor = (Armor) item;
            System.out.println("Vous équipez l'armure : " + armor.getName());
            increaseDefense(armor.getDefense());
        } else if (item instanceof Shield) {
            // Equipe un bouclier
            Shield shield = (Shield) item;
            System.out.println("Vous équipez le bouclier : " + shield.getName());
            increaseDefense(shield.getDefense());
        } else {
            System.out.println("Cet objet ne peut pas être équipé.");
        }
    }

    public void increaseDefense(int amount) {
        if (amount > 0) {
            defense += amount;
            System.out.println("Défense augmentée de " + amount + ". Défense actuelle : " + defense);
        }
    }

    public void addItemToInventory(Item item) {
        if (inventory.isFull()) {
            System.out.println("L'inventaire est plein. Vous ne pouvez pas ajouter " + item.getName() + ".");
            return;
        }
        inventory.addItem(item);
        System.out.println("Vous avez ajouté " + item.getName() + " à votre inventaire.");
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    public int getLevel() {
        return this.level;
    }

    public int getGold() {
        return gold;
    }

    public boolean spendGold(int amount) {
        if (amount > 0 && amount <= gold) {
            gold -= amount;
            System.out.println(amount + " pièces d'or dépensées.");
            return true;
        } else {
            System.out.println("Vous n'avez pas assez d'or pour cette action.");
            return false;
        }
    }

    private void levelUp() {
        level++;
        experience = 0; // Remet l'expérience à zéro après le niveau supérieur
        experienceToNextLevel += 50; // Augmente les points nécessaires pour le prochain niveau (par exemple)
        int previousMaxHealth = maxHealth; // Sauvegarde la valeur des PV max précédents
        maxHealth += 20; // Augmente la santé maximale

        // Si la vie actuelle est supérieure à la nouvelle santé maximale, on la restreint
        if (health > maxHealth) {
            health = maxHealth;
        }

        strength += 5; // Améliore les statistiques
        agility += 5;
        intelligence += 5;
        defense += 5;

        System.out.println("Vous avez atteint le niveau " + level + "! Toutes vos statistiques ont été améliorées.");
        System.out.println("Vos PV maximums sont maintenant de " + maxHealth + ". Vous avez toujours " + health + " points de vie actuels.");
    }

    public String getAsciiFace() {
        return asciiFace;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void gainExperience(int points) {
        if (points > 0) {
            experience += points;
            System.out.println("Vous avez gagné " + points + " points d'expérience.");

            // Vérifie si le joueur a assez de points pour monter de niveau
            if (experience >= experienceToNextLevel) {
                levelUp();
            }
        } else {
            System.out.println("Points d'expérience invalides.");
        }
    }

    public void showInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Votre inventaire est vide.");
            return;
        }

        System.out.println("Votre inventaire :");
        for (Item item : inventory.getItems()) {
            System.out.println("- " + item.getName() + ": " + item.getDescription());
        }
        System.out.println("Nombre d'éléments dans l'inventaire : " + inventory.getItemCount());
        System.out.println("Total d'objets: " + inventory.size() + "/" + inventory.maxInventorySize());
    }

    public void showEquippedItems() {
        System.out.println("=== Objets Équipés ===");
        System.out.println("Arme : " + (equippedWeapon != null ? equippedWeapon.getName() : "Aucune arme équipée"));
        System.out.println("Armure : " + (equippedArmor != null ? equippedArmor.getName() : "Aucune armure équipée"));
        System.out.println("Casque : " + (equippedHelmet != null ? equippedHelmet.getName() : "Aucun casque équipé"));
        System.out.println("Bouclier : " + (equippedShield != null ? equippedShield.getName() : "Aucun bouclier équipé"));
        System.out.println("Bottes : " + (equippedBoots != null ? equippedBoots.getName() : "Aucune botte équipée"));
        System.out.println("Pantalon : " + (equippedPants != null ? equippedPants.getName() : "Aucun pantalon équipé"));
        System.out.println("Gants : " + (equippedGloves != null ? equippedGloves.getName() : "Aucun gant équipé"));
        System.out.println("========================");
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void move(int deltaX, int deltaY) {
        playerX += deltaX;
        playerY += deltaY;
    }

    public int getStrength() {
        return strength;
    }

    public void takeDamage(int damage) {
        // Appliquer les dégâts au joueur sans ajustement
        health -= damage;

        // Empêcher la santé de descendre en dessous de zéro
        if (health < 0) {
            health = 0;
        }

        // Afficher les informations sur les dégâts reçus
        System.out.println(name + " a subi " + damage + " points de dégâts. Santé actuelle : " + health);
    }


    public String getName() {
        return name;
    }

    public void restoreHealth(int amount) {
        if (amount > 0) {
            health += amount;
            if (health > maxHealth) {
                health = maxHealth; // Empêche la santé d'excéder le maximum
            }
            System.out.println("Vous avez regagné " + amount + " points de vie. Santé actuelle : " + health + "/" + maxHealth);
        } else {
            System.out.println("Montant de soin invalide. La restauration de santé ne peut pas être négative.");
        }
    }

    public boolean isAlive() {
        return health > 0; // Vérifie si le joueur a encore des points de vie
    }

    public int getHealth() {
        return health;
    }

    public void attack(Attackable target, int attackType) {
        int damage;

        // Si une arme est équipée, vérifier son type et calculer les dégâts en conséquence
        if (equippedWeapon != null) {
            if (equippedWeapon.getName().equalsIgnoreCase("Marteau")) {
                damage = calculateHammerAttackDamage(attackType);
            } else if (equippedWeapon.getName().equalsIgnoreCase("Arc")) {
                damage = calculateBowAttackDamage(attackType);
            } else if (equippedWeapon.getName().equalsIgnoreCase("Hache")) {
                damage = calculateAxeAttackDamage(attackType);
            } else {
                damage = calculateStandardAttackDamage(attackType); // Peut être personnalisé pour chaque arme
            }
        } else {
            // Si aucune arme n'est équipée, utiliser les attaques standard du joueur
            damage = calculateStandardAttackDamage(attackType);
        }

        target.takeDamage(damage);
        System.out.println(name + " a attaqué " + target.getName() + " et a infligé " + damage + " points de dégâts.");
    }


    private int calculateBowAttackDamage(int attackType) {
        switch (attackType) {
            case 1: // Tir précis
                return 15;
            case 2: // Tir en cloche
                return 25;
            case 3: // Tir rapide
                return 20;
            case 4: // Tir multiple
                return 35;
            default:
                System.out.println("Type d'attaque non reconnu. Aucun dégât infligé.");
                return 0;
        }
    }



    // Exemple de méthode pour calculer les dégâts en fonction du type d'attaque
    private int calculateStandardAttackDamage(int attackType) {
        switch (attackType) {
            case 1: // Coup de poing
                return 10;
            case 2: // Attaque puissante
                return 20;
            case 3: // Attaque rapide
                return 15;
            case 4: // Attaque spéciale
                return 25;
            default:
                System.out.println("Type d'attaque non reconnu. Aucun dégât infligé.");
                return 0;
        }
    }

    private int calculateHammerAttackDamage(int attackType) {
        switch (attackType) {
            case 1: // Coup de Marteau
                return 20;
            case 2: // Attaque puissante du Marteau
                return 40;
            case 3: // Attaque rapide du Marteau
                return 30;
            case 4: // Attaque spéciale du Marteau
                return 50;
            default:
                System.out.println("Type d'attaque non reconnu. Aucun dégât infligé.");
                return 0;
        }
    }

    public void displayAttackOptions() {
        if (equippedWeapon != null) {
            if (equippedWeapon.getName().equalsIgnoreCase("Marteau")) {
                System.out.println("Choisissez votre type d'attaque : (1: Coup de Marteau, 2: Attaque puissante du Marteau, 3: Attaque rapide du Marteau, 4: Attaque spéciale du Marteau) : ");
            } else if (equippedWeapon.getName().equalsIgnoreCase("Arc")) {
                System.out.println("Choisissez votre type d'attaque : (1: Tir précis, 2: Tir en cloche, 3: Tir rapide, 4: Tir multiple) : ");
            } else if (equippedWeapon.getName().equalsIgnoreCase("Hache")) {
                System.out.println("Choisissez votre type d'attaque : (1: Coup de Hache, 2: Attaque puissante de la Hache, 3: Attaque rapide de la Hache, 4: Attaque tourbillon de la Hache) : ");
            } else {
                System.out.println("Choisissez votre type d'attaque : (1: Coup de poing, 2: Attaque puissante, 3: Attaque rapide, 4: Attaque spéciale) : ");
            }
        } else {
            System.out.println("Choisissez votre type d'attaque : (1: Coup de poing, 2: Attaque puissante, 3: Attaque rapide, 4: Attaque spéciale) : ");
        }
    }

    private int calculateAxeAttackDamage(int attackType) {
        switch (attackType) {
            case 1: // Coup de Hache
                return 25;
            case 2: // Attaque puissante de la Hache
                return 45;
            case 3: // Attaque rapide de la Hache
                return 30;
            case 4: // Attaque tourbillon de la Hache
                return 50;
            default:
                System.out.println("Type d'attaque non reconnu. Aucun dégât infligé.");
                return 0;
        }
    }



    public void heal(int healingAmount) {
        health += healingAmount;
        if (health > maxHealth) {
            health = maxHealth; // Assurez-vous de ne pas dépasser les PV max
        }
        System.out.println("Vous avez regagné " + healingAmount + " points de vie.");
    }

    public void addGold(int amount) {
        if (amount > 0) {
            this.gold += amount;
        } else {
            System.out.println("Montant invalide. L'or ne peut pas être négatif.");
        }
    }
}
