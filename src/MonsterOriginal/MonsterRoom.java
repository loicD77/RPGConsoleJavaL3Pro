package MonsterOriginal;

import Item.Item;
import MonsterGroup.*;
import Obstacle.StoneObstacle; // Import de StoneObstacle depuis le package Obstacle
import Obstacle.WoodenBarrier; // Import de WoodenBarrier depuis le package Obstacle
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
        entities.add(new Ogre(playerLevel)); // Cyclope avec un constructeur approprié
        entities.add(new Skeleton(playerLevel)); // Cyclope avec un constructeur approprié
        entities.add(new Dragon(playerLevel)); // Cyclope avec un constructeur approprié
        entities.add(new Troll(playerLevel));
        entities.add(new Goblin(playerLevel));

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
        boolean resting = false;
        int restTurns = 0;

        while (!obstacle.isDestroyed()) {
            if (resting) {
                restTurns--;
                player.restoreHealth(50);
                System.out.println(player.getName() + " se repose et regagne 50 points de vie. Points de vie actuels : " + player.getHealth() + "/" + player.getMaxHealth());
                if (restTurns == 0) {
                    resting = false;
                }
            } else {
                System.out.println("Que voulez-vous faire ? (1: Attaquer, 2: Fuire, 3: Se reposer, 4: Utiliser un objet de l'inventaire)");
                int choice = scanner.nextInt();

                if (choice == 1) {
                    // Affiche les options d'attaque disponibles selon l'arme équipée
                    player.displayAttackOptions();

                    System.out.print("Choisissez votre type d'attaque : ");
                    int attackType = scanner.nextInt();
                    player.attack(obstacle, attackType); // Attaque l'obstacle avec la valeur choisie
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

                } else if (choice == 3) {
                    resting = true;
                    restTurns = 2;
                    System.out.println(player.getName() + " commence à se reposer pour 2 tours et regagnera des PV.");

                }  else if (choice == 4) {
                System.out.println("Vous fouillez dans votre inventaire.");
                player.displayInventory();
                // Suppression de la partie où l'utilisateur doit entrer un objet.
            } else {
                    System.out.println("Choix invalide. Veuillez réessayer.");
                }
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
                    // Affiche les options d'attaque disponibles selon l'arme équipée
                    player.displayAttackOptions();

                    System.out.print("Choisissez votre type d'attaque : ");
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
                // Suppression de la partie où l'utilisateur doit entrer un objet.
            }
            else {
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
