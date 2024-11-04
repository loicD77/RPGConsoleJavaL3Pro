package MainFiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Player.Player; // Import de Player depuis le package Player
import MainFiles.GameMap; // Import de GameMap
import Dungeon.DungeonPiece;
import Item.Inventory; // Import de Inventory depuis le package Item
import MonsterOriginal.MonsterRoom;
import Dungeon.TreasureRoom;

public class Main {
    public static void main(String[] args) {
        // Création d'un scanner pour les entrées utilisateur
        Scanner scanner = new Scanner(System.in);

        // Demander le nom du personnage
        System.out.print("Entrez le nom de votre personnage : ");
        String playerName = scanner.nextLine();

        // Afficher les visages ASCII disponibles
        displayAsciiFaces();

        // Demander au joueur de choisir un visage ASCII
        System.out.print("Entrez le numéro de votre choix : ");
        int faceChoice = Integer.parseInt(scanner.nextLine());

        // Assigner le visage ASCII en fonction du choix
        String playerAsciiFace = selectAsciiFace(faceChoice);

        // Créer le joueur avec le nom et le visage ASCII saisis
        Player player = new Player(playerName, playerAsciiFace);

        // Initialisation de l'inventaire et des pièces du donjon
        Inventory inventory = new Inventory();
        player.setInventory(inventory); // Assurez-vous que le joueur a un inventaire
        List<DungeonPiece> dungeonPieces = createDungeonPieces();

        // Initialisation de la carte de jeu
        GameMap map = new GameMap(player);

        player.gameLoop(map, scanner);
    }

    public static void exploreGameMap(Player player, GameMap map, Scanner scanner) {
        boolean exploring = true; // Contrôle de l'exploration

        while (exploring) {
            map.displayMap(); // Affiche la carte mise à jour




            System.out.println("Déplacez-vous (w/a/s/d pour haut/gauche/bas/droite, q pour quitter) : ");
            String input = scanner.nextLine().toLowerCase(); // Conversion en minuscule pour simplifier la gestion des commandes

            switch (input) {
                case "w":
                    map.movePlayer(player, -1, 0); // Déplacement vers le haut (diminue Y)
                    break;
                case "s":
                    map.movePlayer(player, 1, 0); // Déplacement vers le bas (augmente Y)
                    break;
                case "a":
                    map.movePlayer(player, 0, -1); // Déplacement vers la gauche (diminue X)
                    break;
                case "d":
                    map.movePlayer(player, 0, 1); // Déplacement vers la droite (augmente X)
                    break;
                case "q":
                    exploring = false; // Quitter l'exploration
                    System.out.println("Vous avez quitté le donjon.");
                    break;
                default:
                    System.out.println("Commande non reconnue. Veuillez entrer 'w', 'a', 's', 'd', ou 'q'.");
                    break;


            }
        }
    }

    private static void displayAsciiFaces() {
        System.out.println("Choisissez un visage ASCII pour votre personnage :");
        System.out.println("1.  ^_^  ");
        System.out.println("2.  O_O  ");
        System.out.println("3.  T_T  ");
        System.out.println("4.  >_<  ");
        System.out.println("5.  U_U  ");
    }

    private static String selectAsciiFace(int choice) {
        switch (choice) {
            case 1: return "^_^";
            case 2: return "O_O";
            case 3: return "T_T";
            case 4: return ">_<";
            case 5: return "U_U";
            default:
                System.out.println("Choix invalide, visage par défaut utilisé.");
                return "^_^";
        }
    }

    private static List<DungeonPiece> createDungeonPieces() {
        List<DungeonPiece> dungeonPieces = new ArrayList<>();
        dungeonPieces.add(new MonsterRoom());
        dungeonPieces.add(new TreasureRoom());
        return dungeonPieces;
    }
}