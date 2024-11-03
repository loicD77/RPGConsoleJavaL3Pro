package Player;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

// Imports des autres packages du projet
import WeaponOriginal.Weapon;
import MainFiles.GameMap; // Assurez-vous d'importer la classe GameMap
import MainFiles.Main; // Import pour la classe Main


import ProtectiveClothing.ProtectionItem;
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

    private Weapon equippedWeapon; // Arme équipée
    private ProtectionItem equippedProtection; // Équipement de protection générique

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

    private int experiencePoints; // Points d'expérience accumulés
    private int experienceToNextLevel; // Points nécessaires pour passer au niveau suivant

    private int playerX; // Position du joueur en X
    private int playerY; // Position du joueur en Y
    private Scanner scanner = new Scanner(System.in);

    public Player(String name, String asciiFace) {
        this.name = name;
        this.asciiFace = asciiFace;
        this.maxHealth = 100;
        this.health = maxHealth;
        this.equippedWeapon = null;
        this.equippedProtection = null;
        this.experience = 0;
        this.level = 1;
        this.actionHistory = new ArrayList<>();
        this.strength = 10;
        this.agility = 10;
        this.intelligence = 10;
        this.defense = 10;
        this.inventory = new Inventory();
        this.unlockedDungeonPieces = new ArrayList<>();
        this.gold = 1000;
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
        System.out.println("Objet de protection équipé : " + (equippedProtection != null ? equippedProtection.getName() : "Aucun objet de protection équipé."));
        System.out.println("========================");
        System.out.println("Apparence du Personnage :");
        System.out.println("    " + asciiFace);
        System.out.println("    /|\\ ");
        System.out.println("     |  ");
        System.out.println("    / \\");
        System.out.println("========================");
    }

    public void equipWeapon(String weaponName) {
        for (Item item : inventory.getItems()) {
            if (item instanceof Weapon && item.getName().equals(weaponName)) {
                equippedWeapon = (Weapon) item;
                System.out.println("Vous avez équipé " + equippedWeapon.getName() + ".");
                return; // Quitte la méthode après avoir équipé l'arme
            }
        }
        System.out.println("L'arme " + weaponName + " n'est pas dans votre inventaire.");
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

    // Mise à jour de la méthode pour équiper un objet de protection
    public void equipProtectionItem(ProtectionItem item) {
        if (item != null && inventory.contains(item)) {
            if (equippedProtection != null) {
                // Retirer la défense de l'élément de protection actuel
                decreaseDefense(equippedProtection.getDefense());
                System.out.println("Vous avez retiré : " + equippedProtection.getName() + ". Défense diminuée de " + equippedProtection.getDefense());
            }
            equippedProtection = item;
            increaseDefense(item.getDefense());
            System.out.println("Vous avez équipé : " + item.getName() + ". Défense augmentée de " + item.getDefense());
        } else {
            System.out.println("Cet objet de protection n'est pas dans votre inventaire.");
        }
    }


    public void decreaseDefense(int amount) {
        if (amount > 0) {
            defense -= amount;
            if (defense < 0) {
                defense = 0; // Assure que la défense ne soit jamais négative
            }
            System.out.println("Défense diminuée de " + amount + ". Défense actuelle : " + defense);
        }
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void increaseDefense(int amount) {
        if (amount > 0) {
            defense += amount;
            System.out.println("Défense augmentée de " + amount + ". Défense actuelle : " + defense);
        }
    }

    public void addItemToInventory(Item item) {
        boolean isAdded = inventory.addItem(item);
        if (isAdded) {
            System.out.println("Vous avez ajouté " + item.getName() + " à votre inventaire.");
        } else {
            System.out.println("L'inventaire est plein, impossible d'ajouter cet objet.");
        }
    }

    public int getLevel() {
        return level;
    }

    public int getGold() {
        return gold;
    }

    public void addGold(int amount) {
        if (amount > 0) {
            gold += amount;
            System.out.println("Vous avez gagné " + amount + " pièces d'or. Total d'or : " + gold);
        }
    }

    public void gainExperience(int amount) {
        if (amount > 0) {
            experience += amount;
            System.out.println("Vous avez gagné " + amount + " points d'expérience. Total d'expérience : " + experience);
            // Logique pour passer au niveau suivant, si nécessaire
        }
    }



    public boolean spendGold(int amount) {
        if (amount > 0 && gold >= amount) {
            gold -= amount;
            return true;
        } else {
            System.out.println("Vous n'avez pas assez d'or.");
            return false;
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

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

    public void takeDamage(int damage) {
        int defenseReduction = defense / 2; // Réduction des dégâts grâce à la défense
        int reducedDamage = damage - defenseReduction;
        reducedDamage = Math.max(reducedDamage, 1); // Assure un minimum de 1 dégât
        health -= reducedDamage;
        health = Math.max(health, 0); // Empêche la santé de descendre en dessous de 0

        System.out.println(name + " a subi " + reducedDamage + " points de dégâts. " +
                "Réduction de " + defenseReduction + " grâce à la défense. " +
                "Santé actuelle : " + health);
    }

    public void heal(int healingAmount) {
        if (healingAmount > 0) {
            health += healingAmount;
            if (health > maxHealth) {
                health = maxHealth;
            }
            System.out.println("Vous avez regagné " + healingAmount + " points de vie. Santé actuelle : " + health + "/" + maxHealth);
        } else {
            System.out.println("Montant de soin invalide. La restauration de santé ne peut pas être négative.");
        }
    }

    public boolean isAlive() {
        return health > 0;
    }

    public String getAsciiFace() {
        return asciiFace;
    }

    public void attack(Attackable target, int attackType) {
        int damage = (equippedWeapon != null) ? equippedWeapon.calculateAttackDamage(attackType) : calculateStandardAttackDamage(attackType);
        target.takeDamage(damage);
        System.out.println(name + " a attaqué " + target.getName() + " et a infligé " + damage + " points de dégâts.");
    }

    public void displayAttackOptions() {
        if (equippedWeapon != null) {
            System.out.println("Choisissez votre type d'attaque :");
            // Add different attack options based on weapon type
            System.out.println("1: Attaque standard");
            System.out.println("2: Attaque puissante");
            System.out.println("3: Attaque rapide");
            System.out.println("4: Attaque spéciale");
        } else {
            System.out.println("Choisissez votre type d'attaque :");
            System.out.println("1: Coup de poing");
            System.out.println("2: Attaque puissante");
            System.out.println("3: Attaque rapide");
            System.out.println("4: Attaque spéciale");
        }
    }

    public void useItem(String itemName) {
        Item itemToUse = inventory.findItemByName(itemName);
        if (itemToUse != null) {
            if (itemToUse instanceof Weapon) {
                equipWeapon(itemToUse.getName());
                System.out.println("Vous avez équipé l'arme : " + itemToUse.getName());
            } else if (itemToUse instanceof Potion) {
                Potion potion = (Potion) itemToUse;
                potion.use(this);
                System.out.println("Vous avez utilisé la potion : " + itemName);
            } else if (itemToUse instanceof ProtectionItem) {
                equipProtectionItem((ProtectionItem) itemToUse);
                System.out.println("Vous avez équipé l'objet de protection : " + itemName);
            } else {
                System.out.println("Cet objet ne peut pas être utilisé.");
            }
        } else {
            System.out.println("L'objet " + itemName + " n'est pas dans votre inventaire.");
        }
    }

    public void restoreHealth(int amount) {
        if (amount > 0) {
            health += amount;
            if (health > maxHealth) {
                health = maxHealth;
            }
            System.out.println("Vous avez regagné " + amount + " points de vie. Santé actuelle : " + health + "/" + maxHealth);
        } else {
            System.out.println("Montant de soin invalide. La restauration de santé ne peut pas être négative.");
        }
    }

    public void cureStatusEffect(String effect) {
        StatusEffect effectToRemove = null;
        for (StatusEffect status : activeEffects) {
            if (status.getName().equalsIgnoreCase(effect)) {
                effectToRemove = status;
                break;
            }
        }
        if (effectToRemove != null) {
            activeEffects.remove(effectToRemove);
            System.out.println("L'effet de " + effect + " a été soigné !");
        } else {
            System.out.println("Aucun effet de " + effect + " à soigner.");
        }
    }


    public void displayInventory() {
        Scanner scanner = new Scanner(System.in);

        if (inventory.isEmpty()) {
            System.out.println("Votre inventaire est vide.");
            return;
        }

        while (true) {
            System.out.println("\nVotre inventaire :");
            int i = 1;
            for (Item item : inventory.getItems()) {
                System.out.println(i + ". " + item.getName() + ": " + item.getDescription());
                i++;
            }
            System.out.println("Nombre d'éléments dans l'inventaire : " + inventory.getItemCount());
            System.out.println("Total d'objets: " + inventory.size() + "/" + inventory.maxInventorySize());

            System.out.println("\nOptions :");
            System.out.println("1. Équiper un objet");
            System.out.println("2. Jeter un objet");
            System.out.println("3. Quitter la gestion de l'inventaire");

            System.out.print("Que voulez-vous faire ? (1, 2, 3) : ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Pour éviter les erreurs de lecture

            switch (choice) {
                case 1:
                    equipItem(scanner);
                    break;
                case 2:
                    discardItem(scanner);
                    break;
                case 3:
                    return;  // Quitter la gestion de l'inventaire
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }

    private void discardItem(Scanner scanner) {
        System.out.print("Entrez le numéro de l'objet à jeter : ");
        int itemNumber = scanner.nextInt();
        scanner.nextLine();  // Pour éviter les erreurs de lecture

        if (itemNumber < 1 || itemNumber > inventory.getItemCount()) {
            System.out.println("Numéro d'objet invalide.");
            return;
        }

        // Utiliser itemNumber pour accéder directement à l'objet à jeter
        Item itemToDiscard = inventory.getItems().get(itemNumber - 1);

        // Si l'objet à jeter est l'élément de protection actuellement équipé
        if (itemToDiscard instanceof ProtectionItem && itemToDiscard == equippedProtection) {
            decreaseDefense(((ProtectionItem) itemToDiscard).getDefense());
            System.out.println("Vous avez retiré : " + itemToDiscard.getName() + ". Défense diminuée de " + ((ProtectionItem) itemToDiscard).getDefense());
            equippedProtection = null; // Retirer l'équipement actuellement équipé
        }

        inventory.dropItem(itemNumber - 1);  // Retirer l'objet de l'inventaire en utilisant son index
        System.out.println("Vous avez jeté l'objet : " + itemToDiscard.getName());
    }


    private void equipItem(Scanner scanner) {
        System.out.print("Entrez le numéro de l'objet à équiper : ");
        int itemNumber = scanner.nextInt();
        scanner.nextLine();  // Pour éviter les erreurs de lecture

        if (itemNumber < 1 || itemNumber > inventory.getItemCount()) {
            System.out.println("Numéro d'objet invalide.");
            return;
        }

        Item itemToEquip = inventory.getItems().get(itemNumber - 1);

        if (itemToEquip instanceof Weapon) {
            equipWeapon(itemToEquip.getName());
        } else if (itemToEquip instanceof ProtectionItem) {
            equipProtectionItem((ProtectionItem) itemToEquip);
        } else {
            System.out.println("Cet objet ne peut pas être équipé.");
        }
    }

    public void gameLoop(GameMap map, Scanner scanner) {
        boolean isPlaying = true;

        while (isPlaying) {
            System.out.println("=== Menu Principal ===");
            System.out.println("1: Explorer");
            System.out.println("2: Afficher l'inventaire");
            System.out.println("3: Afficher le statut");
            System.out.println("4: Quitter");
            System.out.print("Choisissez une option : ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Pour vider le buffer

            switch (choice) {
                case 1:
                    // Appel à l'exploration via GameMap
                    Main.exploreGameMap(this, map, scanner);
                    break;
                case 2:
                    // Appeler la méthode pour afficher l'inventaire
                    displayInventory();
                    break;
                case 3:
                    // Appeler la méthode pour afficher le statut
                    displayStatus();
                    break;
                case 4:
                    System.out.println("Vous quittez le jeu. À bientôt !");
                    isPlaying = false;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
                    break;
            }
        }
    }



}
