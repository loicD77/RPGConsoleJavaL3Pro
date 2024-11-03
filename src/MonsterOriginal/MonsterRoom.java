package MonsterOriginal;

import Item.Item;
import Obstacle.StoneObstacle; // Import de StoneObstacle depuis le package Obstacle
import Obstacle.WoodenBarrier; // Import de WoodenBarrier depuis le package Obstacle
import MonsterGroup.Zombie; // Import de Zombie depuis le package MonsterGroup
import MonsterGroup.Cyclops; // Import de Cyclops
import Entity.GameEntity; // Import de GameEntity
import Player.Player; // Import de Player
import MonsterOriginal.Monster;
import Dungeon.DungeonPiece;
import PotionGroup.Potion;
import ProtectiveClothing.ProtectionItem;
import WeaponOriginal.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MonsterRoom extends DungeonPiece {
    private List<GameEntity> entities; // Liste d'entités (obstacles et monstres)
    private Random random; // Générateur aléatoire

    // Constructeur qui accepte des arguments
    public MonsterRoom(String name, String description, int level) {
        super(name, description, level);
        entities = new ArrayList<>();
        random = new Random();

        // Ajoutez quelques obstacles à la liste
        entities.add(new StoneObstacle("Pierre massive", 20));
        entities.add(new WoodenBarrier("Barrière en bois", 15));
    }

    // Constructeur par défaut
    public MonsterRoom() {
        this("Salle des Monstres", "Cette salle est remplie de cris de monstres affamés. L'odeur de la chair pourrie est omniprésente.", 2);
    }

    @Override
    public void enter(Player player) {
        System.out.println("Vous êtes entré dans la salle des monstres !");

        // Créez des monstres en fonction du niveau du joueur
        int playerLevel = player.getLevel(); // Récupérer le niveau du joueur
        entities.add(new Zombie(playerLevel)); // Créez le zombie avec le niveau du joueur
        entities.add(new Cyclops(playerLevel)); // Cyclope avec un constructeur approprié

        // Mélanger les entités pour un ordre aléatoire
        shuffleEntities();

        // Combattre les entités aléatoires
        for (GameEntity entity : entities) {
            if (entity instanceof Obstacle) {
                handleObstacle(player, (Obstacle) entity);
            } else if (entity instanceof Monster) {
                handleMonster(player, (Monster) entity);
            }
        }

        // Afficher l'état du joueur après la rencontre
        player.displayStatus();
    }

    private void handleObstacle(Player player, Obstacle obstacle) {
        System.out.println("Vous rencontrez un obstacle : " + obstacle.getName() + " (PV: " + obstacle.getHealth() + ")");

        Scanner scanner = new Scanner(System.in);
        while (!obstacle.isDestroyed()) {
            System.out.println("Que voulez-vous faire ? (1: Attaquer, 2: Fuire)");
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.println("Choisissez votre type d'attaque : (1: Coup de poing, 2: Attaque puissante, 3: Attaque rapide, 4: Attaque spéciale) : ");
                int attackType = scanner.nextInt();
                player.attack(obstacle, attackType); // Attaque l'obstacle sans affecter de valeur à une variable
                System.out.println("Vous avez attaqué " + obstacle.getName() + ". PV restants : " + obstacle.getHealth());
                if (obstacle.isDestroyed()) {
                    System.out.println("Vous avez détruit l'obstacle : " + obstacle.getName());
                    int reward = random.nextInt(10) + 1; // Gain aléatoire en or
                    player.addGold(reward);
                    System.out.println("Vous avez gagné " + reward + " pièces d'or.");
                }
            } else if (choice == 2) {
                System.out.println("Vous avez fui !");
                return;
            }
        }
    }

    private void handleMonster(Player player, Monster monster) {
        System.out.println("Un " + monster.getName() + " apparaît !");

        boolean resting = false;
        int restTurns = 0;

        while (player.isAlive() && monster.isAlive()) {
            if (resting) {
                restTurns--;
                player.restoreHealth(50);
                System.out.println(player.getName() + " se repose et regagne 50 points de vie. Points de vie actuels : " + player.getHealth() + "/" + player.getMaxHealth());
                if (restTurns == 0) {
                    resting = false;
                }
            } else {
                System.out.println("Que voulez-vous faire ? (1: Attaquer, 2: Fuire, 3: Se reposer, 4: Utiliser un objet de l'inventaire)");
                Scanner scanner = new Scanner(System.in);
                int choice = scanner.nextInt();

                if (choice == 1) {
                    // Appeler l'option d'attaque ici
                    player.displayAttackOptions();
                    int attackType = scanner.nextInt();
                    player.attack(monster, attackType); // Attaque le monstre

                } else if (choice == 2) {
                    System.out.println("Vous avez fui !");
                    return;

                } else if (choice == 3) {
                    resting = true;
                    restTurns = 2;
                    System.out.println(player.getName() + " commence à se reposer pour 2 tours et regagnera des PV.");

                } else if (choice == 4) {
                    System.out.println("Vous fouillez dans votre inventaire.");
                    player.displayInventory();
                    System.out.println("Que voulez-vous utiliser ? (Entrez le nom de l'objet, ou tapez 'annuler' pour revenir en arrière)");
                    scanner.nextLine();  // Consommer la ligne
                    String itemChoice = scanner.nextLine();
                    if (!itemChoice.equalsIgnoreCase("annuler")) {
                        Item itemToUse = player.getInventory().findItemByName(itemChoice);
                        if (itemToUse != null) {
                            if (itemToUse instanceof Weapon) {
                                player.equipWeapon(itemChoice);
                            } else if (itemToUse instanceof ProtectionItem) {
                                player.equipProtectionItem((ProtectionItem) itemToUse);
                            } else if (itemToUse instanceof Potion) {
                                ((Potion) itemToUse).use(player);

                                // Récupérer l'index de l'objet dans l'inventaire
                                int itemIndex = player.getInventory().getItems().indexOf(itemToUse);

                                // Si l'objet est trouvé, supprimer par index
                                if (itemIndex != -1) {
                                    player.getInventory().dropItem(itemIndex);
                                } else {
                                    System.out.println("Erreur : Impossible de trouver l'objet dans l'inventaire.");
                                }
                            }
                        } else {
                            System.out.println("L'objet n'est pas dans votre inventaire.");
                        }
                    }

                } else {
                    System.out.println("Choix invalide.");
                }
            }

            if (!monster.isAlive()) {
                System.out.println("Vous avez vaincu " + monster.getName() + " !");
                player.gainExperience(monster.getExperiencePoints());
                player.addGold(monster.getGold());
                break;
            }

            // Le monstre attaque
            monster.attack(player);
            if (!player.isAlive()) {
                System.out.println("Vous avez été vaincu par " + monster.getName() + "...");
                break;
            }
        }
    }


    private void shuffleEntities() {
        // Mélanger les entités pour un ordre aléatoire
        for (int i = entities.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            // Échange d'entités
            GameEntity temp = entities.get(i);
            entities.set(i, entities.get(j));
            entities.set(j, temp);
        }
    }

    @Override
    public String asciiArt(Player player) {
        return "     |-------------------|\n" +
                "     |                   |\n" +
                "     |   SALLE DES       |\n" +
                "     |    MONSTRES       |\n" +
                "     |                   |\n" +
                "     |     /\\__/\\       |\n" +
                "     |    |        |     |\n" +
                "     |    |  O O   |     |\n" +
                "     |    |   ^^   |     |\n" +
                "     |    |_________|     |\n" +
                "     |                   |\n" +
                "     |___________________|\n" +
                "Personnage : " + player.getAsciiFace() + "\n" +
                "Nom : " + player.getName() + "\n" +
                "Niveau : " + player.getLevel() + "\n" +
                "PV : " + player.getHealth() + "/" + player.getMaxHealth() + "\n" +
                "Or : " + player.getGold() + "\n";
    }
}
