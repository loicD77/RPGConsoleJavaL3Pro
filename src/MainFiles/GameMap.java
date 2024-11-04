package MainFiles;

import MonsterOriginal.MonsterRoom;
import WeaponOriginal.PharmacyStore;
import WeaponOriginal.ProtectionStore;
import WeaponOriginal.WeaponStore;
import WeaponOriginal.SecretStore; // Import de SecretStore
import Dungeon.DungeonPiece; // Import de DungeonPiece depuis le package Dungeon
import Player.Player;
import Dungeon.TreasureRoom; // Ajout de l'import pour TreasureRoom
import Dungeon.BossRoom;
import Dungeon.DiceRoom; // Import de DiceRoom
import Dungeon.JavaQuizRoom; // Import de JavaQuizRoom

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GameMap {
    private char[][] layout;
    private Map<Character, DungeonPiece> pieces;
    private int playerX, playerY;
    private Random random;
    private Player player;

    public GameMap(Player player) {
        this.player = player;
        random = new Random();
        layout = new char[][]{
                {'▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓'},
                {'▓', ' ', 'W', ' ', '|', ' ', 'P', ' ', '|', ' ', 'T', ' ', '|', ' ', 'R', ' ', '|', ' ', '▓'},
                {'▓', '_', '_', '_', '|', '_', '_', '_', '|', '_', '_', '_', '|', '_', '_', '_', '|', '_', '▓'},
                {'▓', ' ', ' ', ' ', '|', ' ', 'S', ' ', '|', ' ', 'M', ' ', '|', ' ', 'B', ' ', '|', ' ', '▓'},
                {'▓', '_', '_', '_', '|', '_', '_', '_', '|', '_', '_', '_', '|', '_', '_', '_', '|', '_', '▓'},
                {'▓', ' ', 'D', ' ', '|', ' ', 'X', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', '▓'},
                {'▓', '_', '_', '_', '|', '_', '_', '_', '|', '_', '_', '_', '|', '_', '_', '_', '|', '_', '▓'},
                {'▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓'}
        };

        pieces = new HashMap<>();
        pieces.put('W', new WeaponStore("Magasin d'Armes", "Un endroit pour acheter des armes.", 1));
        pieces.put('P', new PharmacyStore("Pharmacie", "Un endroit pour acheter des potions.", 1));
        pieces.put('B', new BossRoom("Salle du Boss", "Une pièce sombre où se cache le boss final.", 5));
        pieces.put('T', new TreasureRoom("Salle du Trésor", "Un endroit pour gagner des récompenses.", 1));
        pieces.put('M', new MonsterRoom("Salle des Monstres", "Des créatures dangereuses rôdent ici.", 1));
        pieces.put('R', new ProtectionStore("Magasin de Protection", "Achète des armures pour te protéger.", 1));
        pieces.put('S', new SecretStore("Magasin Secret", "Un endroit secret pour acheter des objets originals", 1));
        pieces.put('D', new DiceRoom("Salle du Dé", "Une salle où le joueur lance un dé à 6 faces.", 1));
        pieces.put('X', new JavaQuizRoom("Salle de Quiz Java", "Répondez à des questions Java pour gagner des pièces.", 1));

        playerX = 1;  // Position de départ du joueur
        playerY = 1;  // Position de départ du joueur
    }

    public void displayMap() {
        player.displayStatus();
        System.out.println("\nCarte du Donjon :");

        // Légende simplifiée avec utilisation de caractères standards
        String[] legend = {
                "+-------------------------+",
                "|       Légende :         |",
                "+-------------------------+",
                "| W : Magasin d'Armes     |",
                "| P : Pharmacie           |",
                "| T : Salle du Trésor     |",
                "| M : Salle des Monstres  |",
                "| B : Salle du Boss       |",
                "| R : Magasin Protection  |",
                "| S : Magasin Secret      |",
                "| D : Salle du Dé         |",
                "| X : Quiz Java           |",
                "+-------------------------+"
        };

        int maxRows = Math.max(layout.length, legend.length);

        // Parcourir chaque ligne pour l'affichage de la carte et de la légende
        for (int i = 0; i < maxRows; i++) {
            StringBuilder line = new StringBuilder();

            // Afficher la ligne de la carte si elle existe
            if (i < layout.length) {
                for (int j = 0; j < layout[i].length; j++) {
                    if (i == playerX && j == playerY) {
                        line.append("🎮 "); // Utilisez un symbole distinct pour le joueur
                    } else {
                        line.append(layout[i][j]).append(" ");
                    }
                }
            } else {
                line.append(" ".repeat(layout[0].length * 2)); // Remplissage si la ligne n'existe pas
            }

            // Ajouter des espaces pour séparer la carte de la légende
            while (line.length() < 40) { // Ajustez cette valeur pour gérer la largeur de l'affichage
                line.append(" ");
            }

            // Affiche la légende si la ligne correspondante existe
            if (i < legend.length) {
                line.append("  ").append(legend[i]);
            }

            System.out.println(line);
        }
    }

    public void movePlayer(Player player, int dx, int dy) {
        int newX = playerX + dx;
        int newY = playerY + dy;

        // Vérification des limites pour ne pas aller dans un espace vide
        if (newX < 0 || newX >= layout.length || newY < 0 || newY >= layout[0].length || layout[newX][newY] == '▓') {
            System.out.println("Vous ne pouvez pas vous déplacer dans cette direction.");
            return;
        }

        playerX = newX;
        playerY = newY;
        char pieceSymbol = layout[newX][newY];
        if (pieces.containsKey(pieceSymbol)) {
            enterPiece(pieceSymbol, player); // Utilisation du joueur actuel
        }
    }

    public void enterPiece(char pieceSymbol, Player player) {
        DungeonPiece piece = pieces.get(pieceSymbol);
        if (piece != null) {
            System.out.println("Vous entrez dans : " + piece.getDescription());
            piece.enter(player);
        } else {
            System.out.println("Il n'y a rien ici.");
        }
    }
}
