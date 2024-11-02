package Dungeon;

import Dungeon.DungeonPiece;
import MonsterOriginal.FinalBoss; // Import de la classe FinalBoss
import Player.Player;
import java.util.Scanner;

public class BossRoom extends DungeonPiece {
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

        // Logique de combat entre le joueur et le boss
        while (finalBoss.isAlive() && player.isAlive()) {
            System.out.println("\n=== Combat contre le Boss Final ===");
            System.out.println("Santé du Boss : " + finalBoss.getHealth() + "/" + finalBoss.getMaxHealth());
            player.displayStatus();
            System.out.println("\nQue voulez-vous faire ?");
            System.out.println("1: Attaquer");
            System.out.println("2: Utiliser un objet de l'inventaire");
            System.out.println("3: Fuire (impossible pendant le combat contre le boss)");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // Attaque du joueur
                    player.displayAttackOptions();
                    int attackType = scanner.nextInt();
                    scanner.nextLine();
                    player.attack(finalBoss, attackType);
                    break;
                case 2:
                    // Utiliser un objet de l'inventaire
                    System.out.println("Votre inventaire :");
                    player.showInventory();
                    System.out.print("Que voulez-vous utiliser ? (Entrez le nom de l'objet, ou tapez 'annuler' pour revenir en arrière) ");
                    String itemName = scanner.nextLine();
                    if (!itemName.equalsIgnoreCase("annuler")) {
                        player.useItem(itemName);
                    }
                    break;
                case 3:
                    System.out.println("Vous ne pouvez pas fuir ce combat !");
                    break;
                default:
                    System.out.println("Choix invalide.");
                    break;
            }

            // Si le boss est toujours en vie, il attaque
            if (finalBoss.isAlive()) {
                finalBoss.attack(player);
            }
        }

        // Résultat du combat
        if (!finalBoss.isAlive()) {
            System.out.println("Félicitations ! Vous avez vaincu le Boss Final !");
            player.gainExperience(finalBoss.getExperiencePoints());
            player.addGold(finalBoss.getGold());
        } else if (!player.isAlive()) {
            System.out.println("Vous avez été vaincu par le Boss Final... Le jeu est terminé.");
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
