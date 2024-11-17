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
        player.setEscaped(false); // Réinitialiser l'état de fuite du joueur

        // Créez des monstres en fonction du niveau du joueur
        int playerLevel = player.getLevel(); // Récupérer le niveau du joueur
        entities.clear(); // Effacer les entités existantes pour éviter de les accumuler à chaque entrée
        entities.add(new Zombie(playerLevel)); // Créez le zombie avec le niveau du joueur
        entities.add(new Cyclops(playerLevel)); // Cyclope avec un constructeur approprié
        entities.add(new Ogre(playerLevel)); // Ogre avec un constructeur approprié
        entities.add(new Skeleton(playerLevel)); // Squelette avec un constructeur approprié
        entities.add(new Dragon(playerLevel)); // Dragon avec un constructeur approprié
        entities.add(new Troll(playerLevel));
        entities.add(new Goblin(playerLevel));

        // Ajouter quelques obstacles
        entities.add(new StoneObstacle("Pierre massive", 20));
        entities.add(new WoodenBarrier("Barrière en bois", 15));

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

    }



    private void handleObstacle(Player player, Obstacle obstacle) {
        System.out.println("Vous rencontrez un obstacle : " + obstacle.getName() + " (PV: " + obstacle.getHealth() + ")");

        Scanner scanner = new Scanner(System.in);
        boolean resting = false;
        int restTurns = 0;

        while (!obstacle.isDestroyed() && !player.hasEscaped()) {
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
        boolean resting = false;
        int restTurns = 0;

        while (player.isAlive() && monster.isAlive() && !player.hasEscaped()) {
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

                switch (choice) {
                    case 1: // Attaquer
                        player.displayAttackOptions();
                        System.out.print("Choisissez votre type d'attaque : ");
                        int attackType = scanner.nextInt();
                        player.attack(monster, attackType);
                        System.out.println("Vous avez attaqué " + monster.getName() + ". PV restants : " + monster.getHealth());
                        break;

                    case 2: // Fuir
                        if (attemptEscape()) {
                            System.out.println("Ouf ! Vous avez enfin quitté cette terrible salle des monstres !");
                            player.setEscaped(true);
                            return;
                        } else {
                            System.out.println("La fuite a échoué, le monstre vous attaque !");
                        }
                        break;

                    case 3: // Se reposer
                        resting = true;
                        restTurns = 2;
                        System.out.println(player.getName() + " commence à se reposer pour 2 tours et regagnera des PV.");
                        break;

                    case 4: // Utiliser un objet de l'inventaire
                        System.out.println("Vous fouillez dans votre inventaire.");
                        player.displayInventory();
                        break;

                    default:
                        System.out.println("Choix invalide.");
                        break;
                }
            }

            // Le monstre attaque une seule fois après l'action du joueur, si le joueur n'a pas fui
            if (!player.hasEscaped() && monster.isAlive()) {
                monster.attack(player);

                // Vérifier si le joueur est encore en vie après l'attaque
                if (!player.isAlive()) {
                    System.out.println("Vous avez été vaincu par " + monster.getName() + "...");
                    return;
                }
            }
        }

        if (!monster.isAlive()) {
            System.out.println("Vous avez vaincu " + monster.getName() + " !");
            player.gainExperience(monster.getExperiencePoints());
            player.addGold(monster.getGold());
        }
    }


    private boolean attemptEscape() {
        int chance = random.nextInt(100);
        return chance < 50; // 50% de chance de réussir la fuite
    }

    private GameEntity getRandomEntity() {
        if (entities.isEmpty()) {
            return null;
        }
        int index = random.nextInt(entities.size());
        return entities.remove(index);
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
