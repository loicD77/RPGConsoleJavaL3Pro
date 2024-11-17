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
    }

    // Constructeur par défaut
    public MonsterRoom() {
        this("Salle des Monstres", "Cette salle est remplie de cris de monstres affamés. L'odeur de la chair pourrie est omniprésente.", 2);
    }

    @Override
    public void enter(Player player) {
        System.out.println("Vous êtes entré dans la salle des monstres !");
        player.setEscaped(false); // Réinitialiser l'état de fuite du joueur

        // Créer des entités aléatoires pour la salle
        entities.clear(); // Effacer les entités existantes pour éviter de les accumuler à chaque entrée

        // Ajouter un ou deux obstacles aléatoires
        if (random.nextBoolean()) {
            entities.add(new StoneObstacle("Pierre massive", 20));
        }
        if (random.nextBoolean()) {
            entities.add(new WoodenBarrier("Barrière en bois", 15));
        }

        // Ajouter un ou deux monstres aléatoires en fonction du niveau du joueur
        List<Monster> possibleMonsters = List.of(
                new Zombie(player.getLevel()),
                new Cyclops(player.getLevel()),
                new Ogre(player.getLevel()),
                new Skeleton(player.getLevel()),
                new Dragon(player.getLevel()),
                new Troll(player.getLevel()),
                new Goblin(player.getLevel())
        );

        int numberOfMonsters = random.nextInt(2) + 1;
        for (int i = 0; i < numberOfMonsters; i++) {
            entities.add(possibleMonsters.get(random.nextInt(possibleMonsters.size())));
        }

        // Mélanger les entités pour un ordre aléatoire
        shuffleEntities();

        // Combattre les entités aléatoires
        for (GameEntity entity : entities) {
            if (player.hasEscaped()) {
                break; // Quitte la salle si le joueur a réussi à fuir
            }
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

        while (!obstacle.isDestroyed() && !player.hasEscaped()) {
            if (player.getHealth() <= 0) {
                System.out.println("Le joueur est mort. Le jeu est terminé.");
                System.exit(0); // Arrête le programme si le joueur est mort
            }
            if (resting) {
                restTurns--;
                player.restoreHealth(20);
                System.out.println(player.getName() + " se repose et regagne 20 points de vie. Points de vie actuels : " + player.getHealth() + "/" + player.getMaxHealth());
                if (restTurns == 0) {
                    resting = false; // Fin du repos
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
                    if (attemptEscape()) {
                        System.out.println("Ouf ! Vous avez enfin quitté cette terrible salle des monstres !");
                        player.setEscaped(true);
                        return;
                    } else {
                        System.out.println("La fuite a échoué, vous tombez sur un autre obstacle ou monstre !");
                        // L'obstacle ne peut pas attaquer le joueur, donc il n'y a aucune attaque à ce moment-là
                    }
                } else if (choice == 3) {
                    resting = true;
                    restTurns = 2;
                    System.out.println(player.getName() + " commence à se reposer pour 2 tours et regagnera des PV.");
                } else if (choice == 4) {
                    System.out.println("Vous fouillez dans votre inventaire.");
                    player.displayInventory();
                } else {
                    System.out.println("Choix invalide. Veuillez réessayer.");
                }
            }
        }
    }


    private void handleMonster(Player player, Monster monster) {
        System.out.println("Un " + monster.getName() + " apparaît !");

        Scanner scanner = new Scanner(System.in);
        boolean playerTurn = true;

        while (player.isAlive() && monster.isAlive() && !player.hasEscaped()) {
            if (player.getHealth() <= 0) {
                System.out.println("Le joueur est mort. Le jeu est terminé.");
                System.exit(0); // Arrête le programme si le joueur est mort
            }

            if (playerTurn) {
                // Le joueur effectue une action
                System.out.println("Que voulez-vous faire ? (1: Attaquer, 2: Fuire, 3: Se reposer, 4: Utiliser un objet de l'inventaire)");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1: // Attaquer
                        player.displayAttackOptions();
                        System.out.print("Choisissez votre type d'attaque : ");
                        int attackType = scanner.nextInt();
                        player.attack(monster, attackType);
                        break;

                    case 2: // Fuir
                        if (attemptEscape()) {
                            System.out.println("Vous avez réussi à fuir !");
                            player.setEscaped(true);
                            return;
                        } else {
                            System.out.println("La fuite a échoué !");
                        }
                        break;

                    case 3: // Se reposer
                        player.rest();
                        break;

                    case 4: // Utiliser un objet
                        System.out.println("Vous fouillez dans votre inventaire.");
                        player.displayInventory();
                        break;

                    default:
                        System.out.println("Choix invalide.");
                        continue;
                }
                playerTurn = false; // Fin du tour du joueur, passe au monstre
            } else {
                // Le monstre attaque
                monster.attack(player);
                playerTurn = true; // Fin du tour du monstre, passe au joueur
            }

            // Vérifier si l'un des deux est mort après chaque attaque
            if (!monster.isAlive()) {
                System.out.println("Vous avez vaincu " + monster.getName() + " !");
                player.gainExperience(monster.getExperiencePoints());
                player.addGold(monster.getGold());
                break;
            } else if (!player.isAlive()) {
                System.out.println("Vous avez été vaincu...");
                System.exit(0); // Arrêter le programme si le joueur est mort
            }
        }
    }

    private boolean attemptEscape() {
        int chance = random.nextInt(100);
        return chance < 50; // 50% de chance de réussir la fuite
    }

    private void shuffleEntities() {
        // Mélanger les entités pour un ordre aléatoire
        for (int i = entities.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
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
