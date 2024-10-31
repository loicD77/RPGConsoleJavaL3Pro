package Player; // Déclaration correcte du package

// Imports Java standard
import java.util.ArrayList;
import java.util.List;

// Imports des autres packages du projet
import WeaponOriginal.Weapon; // Arme importée depuis le package WeaponOriginal
import ProtectiveClothing.Armor; // Armure importée depuis le package ProtectiveClothing
import ProtectiveClothing.Helmet; // Casque importé depuis le package ProtectiveClothing
import PotionGroup.Shield; // Bouclier importé depuis le package PotionGroup
import ProtectiveClothing.Boots; // Bottes importées depuis le package ProtectiveClothing
import ProtectiveClothing.Pants; // Pantalon importé depuis le package ProtectiveClothing
import ProtectiveClothing.Gloves; // Gants importés depuis le package ProtectiveClothing
import Item.Inventory; // Inventaire importé depuis le package Item
import MonsterOriginal.Monster; // Monstre importé depuis le package MonsterOriginal
import Dungeon.DungeonPiece; // Pièce du donjon depuis le package Dungeon
import PotionGroup.Potion; // Potion importée depuis le package PotionGroup
import PotionGroup.StatusEffect; // Effet de statut importé depuis le package PotionGroup
import MonsterOriginal.Obstacle; // Obstacle importé depuis le package MonsterOriginal
import MonsterGroup.Boss; // Boss importé depuis le package MonsterGroup


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
        // Afficher l'ASCII art du personnage avec le visage choisi
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
        if (item == null) {
            System.out.println("Objet non reconnaissable !");
            return;
        }

        switch (item.getClass().getSimpleName()) {
            case "Helmet":
                equippedHelmet = (Helmet) item;
                equippedItems.addItem(item); // Ajoute le casque à l'inventaire équipé
                break;
            case "Shield":
                equippedShield = (Shield) item;
                equippedItems.addItem(item); // Ajoute le bouclier à l'inventaire équipé
                break;
            case "Armor":
                equippedArmor = (Armor) item;
                equippedItems.addItem(item); // Ajoute l'armure à l'inventaire équipé
                break;
            case "Boots":
                equippedBoots = (Boots) item;
                equippedItems.addItem(item); // Ajoute les bottes à l'inventaire équipé
                break;
            case "Pants":
                equippedPants = (Pants) item;
                equippedItems.addItem(item); // Ajoute le pantalon à l'inventaire équipé
                break;
            case "Gloves":
                equippedGloves = (Gloves) item;
                equippedItems.addItem(item); // Ajoute les gants à l'inventaire équipé
                break;
            default:
                System.out.println("Objet non reconnaissable !");
                return;
        }
        System.out.println("Vous avez équipé " + item.getName() + ".");
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
        return this.maxHealth; // Assurez-vous que la variable `maxHealth` existe dans la classe Player
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
        int totalDamage = damage - defense; // Soustraire la défense
        if (totalDamage < 0) totalDamage = 0; // Pas de dégâts négatifs
        health -= totalDamage;
        if (health < 0) health = 0; // Prévenir que la santé ne devienne négative
        System.out.println(name + " a subi " + totalDamage + " points de dégâts. Santé actuelle : " + health);
    }


    public String getName() {
        return name;
    }


    public void addGold(int amount) {
        if (amount > 0) {
            this.gold += amount;
        } else {
            System.out.println("Montant invalide. L'or ne peut pas être négatif.");
        }
    }

    public boolean spendGold(int amount) {
        if (amount > 0 && amount <= gold) {
            gold -= amount;
            System.out.println(amount + " pièces d'or dépensées.");
            return true;
        } else {
            System.out.println("Montant invalide ou pas assez d'or.");
            return false;
        }
    }

    public void restoreHealth(int amount) {
        if (amount > 0) {
            health += amount;
            if (health > maxHealth) {
                health = maxHealth;
            }
            System.out.println("Vous avez restauré " + amount + " PV. Santé actuelle : " + health + "/" + maxHealth);
        } else {
            System.out.println("Montant invalide. La restauration de santé ne peut pas être négative.");
        }
    }

    public void heal(int amount) {
        if (amount > 0) {
            health += amount;
            System.out.println("Vous avez guéri " + amount + " PV.");
        }
    }

    public void levelUp() {
        level++;
        experience = 0; // Réinitialiser l'expérience à 0 après un level up
        maxHealth += 20;
        health = maxHealth; // Restaure la santé maximale
        strength += 5;
        agility += 5;
        intelligence += 5;
        defense += 5;
        System.out.println("Vous avez atteint le niveau " + level + "! Santé maximale : " + maxHealth);
    }

    public void attack(Obstacle obstacle, int damage) {
        // Logique d'attaque ici
        System.out.println(name + " attaque " + obstacle.getName() + " pour " + damage + " points de dégâts.");
        // Suppose que l'obstacle a une méthode pour prendre des dégâts
        obstacle.takeDamage(damage);
    }

    public int attack(Boss monster, int attackType) {
        // Logique d'attaque
        int damage = calculateDamage(attackType);
        monster.takeDamage(damage);
        return damage; // Assurez-vous que cela retourne le montant des dégâts infligés
    }

    public int calculateDamage(int attackType) {
        int baseDamage = 0;

        // Définissez les dégâts de base pour chaque type d'attaque
        switch (attackType) {
            case 1: // Attaque de base
                baseDamage = 10; // Dégâts pour une attaque de type 1
                break;
            case 2: // Attaque puissante
                baseDamage = 20; // Dégâts pour une attaque de type 2
                break;
            case 3: // Attaque magique
                baseDamage = 15; // Dégâts pour une attaque de type 3
                break;
            default: // Type d'attaque non reconnu
                System.out.println("Type d'attaque non reconnu. Dégâts par défaut de 5.");
                baseDamage = 5; // Dégâts par défaut
                break;
        }

        // Vous pouvez également ajouter des modifications basées sur le niveau du joueur, les armes équipées, etc.
        // Par exemple, ajoutez un bonus de dégâts basé sur le niveau
        int damageBonus = getLevel() / 2; // Bonus de dégâts basé sur le niveau (exemple)
        return baseDamage + damageBonus; // Retournez les dégâts totaux
    }


    public int attack(Monster monster, int attackType) {
        int damage = 0;
        // Logique d'attaque en fonction du type
        // Par exemple :
        switch (attackType) {
            case 1:
                damage = 10; // Attaque normale
                break;
            case 2:
                damage = 15; // Attaque spéciale
                break;
            case 3:
                damage = 5; // Attaque rapide
                break;
            case 4:
                damage = 20; // Attaque puissante
                break;
            default:
                System.out.println("Type d'attaque non reconnu.");
                break;
        }
        monster.takeDamage(damage); // Assurez-vous que le monstre a cette méthode
        return damage; // Retourner les dégâts infligés
    }


    // Méthode pour définir l'inventaire
    public void setInventory(Inventory inventory) {
        this.inventory = inventory; // Définit l'inventaire
    }



    public void addExperience(int amount) {
        if (amount > 0) {
            experience += amount;
            System.out.println("Vous avez gagné " + amount + " points d'expérience.");
            if (experience >= level * 100) { // Condition de montée de niveau
                levelUp();
            }
        } else {
            System.out.println("Montant invalide. L'expérience ne peut pas être négative.");
        }
    }

    public void addActiveEffect(StatusEffect effect) {
        activeEffects.add(effect);
        System.out.println("Effet actif ajouté : " + effect.getDescription());
    }

    public void addMoney(int amount) {
        if (amount > 0) {
            gold += amount;
            System.out.println("Vous avez ajouté " + amount + " pièces. Total actuel : " + gold);
        } else {
            System.out.println("Montant invalide. L'ajout d'argent ne peut pas être négatif.");
        }
    }

    public int getLevel() {
        return level;
    }
    // Méthode pour obtenir l'or du joueur
    public int getGold() {
        return gold;
    }

    // Méthode pour obtenir la santé actuelle
    public int getHealth() {
        return health; // Retourne la santé actuelle
    }


    public void gainExperience(int points) {
        experiencePoints += points; // Ajoute les points d'expérience
        System.out.println("Vous gagnez " + points + " points d'expérience !");

        // Vérifiez si le joueur peut monter de niveau
        while (experiencePoints >= experienceToNextLevel) {
            experiencePoints -= experienceToNextLevel; // Réduit l'expérience par rapport à ce qui est nécessaire pour le niveau suivant
            level++; // Augmente le niveau
            experienceToNextLevel += 50; // Augmente la quantité d'expérience nécessaire pour le niveau suivant
            System.out.println("Vous êtes maintenant au niveau " + level + " !");
        }
    }

    public void increaseDefense(int amount) {
        if (amount > 0) {
            defense += amount;
            System.out.println("Défense augmentée de " + amount + ". Défense actuelle : " + defense);
        } else {
            System.out.println("Montant invalide. L'augmentation de la défense ne peut pas être négative.");
        }
    }


    public boolean isAlive() {
        return health > 0; // ou toute autre logique pour vérifier si le joueur est vivant
    }
    public void removeActiveEffect(StatusEffect effect) {
        activeEffects.remove(effect);
        System.out.println("Effet actif supprimé : " + effect.getDescription());
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


    // Méthode pour une attaque légère
    public int lightAttack(Monster monster) {
        int damage = 10;
        System.out.println("Vous effectuez une attaque légère !");
        monster.takeDamage(damage);
        System.out.println("Vous avez infligé " + damage + " dégâts au monstre !");
        return damage;
    }

    // Méthode pour une attaque puissante
    public int heavyAttack(Monster monster) {
        int damage = 20;
        System.out.println("Vous effectuez une attaque puissante !");
        monster.takeDamage(damage);
        System.out.println("Vous avez infligé " + damage + " dégâts au monstre !");
        return damage;
    }

    // Méthode pour une attaque magique
    public int magicAttack(Monster monster) {
        int damage = 15;
        System.out.println("Vous lancez une attaque magique !");
        monster.takeDamage(damage);
        System.out.println("Vous avez infligé " + damage + " dégâts au monstre !");
        return damage;
    }

    // Méthode pour une attaque rapide
    public int quickAttack(Monster monster) {
        int damage = 5;
        System.out.println("Vous effectuez une attaque rapide !");
        monster.takeDamage(damage);
        System.out.println("Vous avez infligé " + damage + " dégâts au monstre !");
        return damage;
    }

    public void clearActiveEffects() {
        activeEffects.clear();
        System.out.println("Tous les effets actifs ont été supprimés.");
    }


}
