package Dungeon;

import Dungeon.DungeonPiece;
import MonsterOriginal.FinalBoss; // Import de la classe FinalBoss
import Player.Player;
import java.util.Scanner;

public class BossRoom extends DungeonPiece { // Classe pour la salle du BOSS FINAL du RPG
    private FinalBoss finalBoss;
    private boolean bossRoomEntered = false;

    public BossRoom(String name, String description, int requiredLevel) {
        super(name, description, requiredLevel);
        this.finalBoss = new FinalBoss(); // Créer une instance du boss final
    }

    @Override
    public void enter(Player player) {
        System.out.println("Tentative d'entrée dans la salle du Boss...");
        System.out.println("Niveau actuel du joueur : " + player.getLevel());
        System.out.println("Niveau requis pour entrer : " + getRequiredLevel());

        // Vérifie si le joueur a déjà essayé d'entrer
        if (bossRoomEntered) {
            System.out.println("Vous avez déjà tenté d'entrer dans cette salle.");
            return;
        }
        bossRoomEntered = true;

        // Vérifie le niveau requis
        if (player.getLevel() < getRequiredLevel()) {
            System.out.println("Vous êtes encore au niveau " + player.getLevel() + ", atteignez le niveau " + getRequiredLevel() + " qui est la limite minimale pour affronter le boss de ce RPG !!");
            return;
        }

        // Si le joueur a le niveau requis, continuer
        System.out.println("Vous entrez dans la " + getName() + " !");
        System.out.println("Le boss vous attend ! Soyez prêt pour un combat difficile !");
        Scanner scanner = new Scanner(System.in);

        boolean resting = false;
        int restTurns = 0;
        boolean playerTurn = true; // Indique que le joueur commence

        // Logique de combat entre le joueur et le boss
        while (finalBoss.isAlive() && player.isAlive()) {
            if (playerTurn) {
                if (resting) {
                    restTurns--;
                    player.restoreHealth(20);
                    System.out.println(player.getName() + " se repose et regagne 20 points de vie. Points de vie actuels : " + player.getHealth() + "/" + player.getMaxHealth());
                    if (restTurns == 0) {
                        resting = false; // Fin du repos
                    }
                } else {
                    System.out.println("\n=== Combat contre le Boss Final ===");
                    System.out.println("Santé du Boss : " + finalBoss.getHealth() + "/" + finalBoss.getMaxHealth());
                    player.displayStatus();
                    System.out.println("\nQue voulez-vous faire ? (1: Attaquer, 2: Fuire, 3: Se reposer, 4: Utiliser un objet de l'inventaire)");
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1: // Attaquer
                            player.displayAttackOptions();
                            System.out.print("Choisissez votre type d'attaque : ");
                            int attackType = scanner.nextInt();
                            scanner.nextLine();
                            player.attack(finalBoss, attackType);
                            break;

                        case 2: // Fuir
                            System.out.println("Vous ne pouvez pas fuir ce combat !");
                            continue;

                        case 3: // Se reposer
                            resting = true;
                            restTurns = 2;
                            System.out.println(player.getName() + " commence à se reposer pour 2 tours et regagnera des PV.");
                            break;

                        case 4: // Utiliser un objet
                            System.out.println("Votre inventaire :");
                            player.displayInventory();
                            System.out.println("Que voulez-vous utiliser ? (Entrez le nom de l'objet, ou tapez 'annuler' pour revenir en arrière) ");
                            String itemName = scanner.nextLine();
                            if (!itemName.equalsIgnoreCase("annuler")) {
                                player.useItem(itemName);
                            }
                            break;

                        default:
                            System.out.println("Choix invalide.");
                            continue;
                    }
                }
                playerTurn = false; // Fin du tour du joueur, passe au boss
            } else {
                // Le boss attaque
                finalBoss.attack(player);
                playerTurn = true; // Fin du tour du boss, passe au joueur
            }

            // Vérifier si l'un des deux est mort après chaque attaque
            if (!finalBoss.isAlive()) {
                System.out.println("Félicitations ! Vous avez vaincu le Boss Final !");
                player.gainExperience(finalBoss.getExperiencePoints());
                player.addGold(finalBoss.getGold());
                break;
            } else if (!player.isAlive()) {
                System.out.println("Vous avez été vaincu par le Boss Final... Le jeu est terminé.");
                System.exit(0); // Arrêter le programme si le joueur est mort
            }
        }
    }

    @Override
    public String asciiArt(Player player) {
        return "=== Salle du Boss ===\n" +
                "    |  \n" +
                "   /|\\ \n" +
                "  / | \\ \n" +
                " /  |  \\ \n" +
                "--------------------------\n" +
                "Personnage : " + player.getAsciiFace() + "\n";
    }
}
