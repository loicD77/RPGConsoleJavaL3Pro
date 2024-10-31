// Classe GameMap mise à jour
import MonsterOriginal.MonsterRoom;
import WeaponOriginal.PharmacyStore;
import WeaponOriginal.ProtectionStore;
import WeaponOriginal.WeaponStore;

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
                {'▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓'},
                {'▓', ' ', 'W', ' ', '|', ' ', 'P', ' ', '|', ' ', 'T', ' ', ' ', ' ', '▓'},
                {'▓', '_', '_', '_', '|', '_', '_', '_', '|', '_', '_', '_', '_', '_', '▓'},
                {'▓', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', '▓'},
                {'▓', ' ', 'S', ' ', '|', ' ', 'M', ' ', '|', ' ', 'B', ' ', ' ', ' ', '▓'},
                {'▓', '_', '_', '_', '|', '_', '_', '_', '|', '_', '_', '_', '_', '_', '▓'},
                {'▓', ' ', ' ', ' ', '|', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' ', ' ', '▓'},
                {'▓', 'R', ' ', '|', 'D', ' ', '|', 'X', ' ', '|', ' ', ' ', ' ', ' ', '▓'},
                {'▓', '_', '_', '|', '_', '_', '|', '_', '_', '|', '_', '_', '_', '_', '▓'},
                {'▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓'}};

        pieces = new HashMap<>();
        pieces.put('W', new WeaponStore("Magasin d'Armes", "Un endroit pour acheter des armes.", 1));
        pieces.put('P', new PharmacyStore("Pharmacie", "Un endroit pour acheter des potions.", 1));
        pieces.put('B', new BossRoom("Salle du Boss", "Une pièce sombre où se cache le boss final.", 1));
        pieces.put('T', new TreasureRoom("Salle du Trésor", "Un endroit pour gagner des récompenses.", 1));
        pieces.put('M', new MonsterRoom("Salle des Monstres", "Des créatures dangereuses rôdent ici.", 2));
        pieces.put('R', new ProtectionStore("Magasin de Protection", "Achète des armures pour te protéger.", 1));

        playerX = 1;  // Position de départ du joueur
        playerY = 1;  // Position de départ du joueur
    }

    public void displayMap() {
        player.displayStatus();
        System.out.println("\nCarte du Donjon :");
        for (int i = 0; i < layout.length; i++) {
            for (int j = 0; j < layout[i].length; j++) {
                if (i == playerX && j == playerY) {
                    System.out.print(" 🎮  "); // Utilisez un symbole distinct pour le joueur
                } else {
                    System.out.print(" " + layout[i][j] + " "); // Ajouter des espaces pour élargir la carte
                }
            }
            System.out.println();
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
