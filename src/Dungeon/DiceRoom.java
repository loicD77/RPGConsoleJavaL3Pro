package Dungeon;

import Player.Player;
import java.util.Random;
import java.util.Scanner;
import Dungeon.DungeonPiece; // Import pour DungeonPiece
import Player.Player; // Import pour Player
import java.util.Random; // Import pour Random


// Salle où le joueur lance un dé à 6 faces
public class DiceRoom extends DungeonPiece {
    private Random random;

    public DiceRoom(String name, String description, int difficulty) {
        super(name, description, difficulty);
        random = new Random();
    }

    @Override
    public void enter(Player player) {
        Scanner scanner = new Scanner(System.in);
        boolean inRoom = true;

        System.out.println("Vous entrez dans la salle du dé. Lancez un dé à 6 faces pour tenter votre chance.");

        while (inRoom) {
            System.out.println("Tapez 'l' pour lancer le dé, ou 'q' pour quitter la salle :");
            String input = scanner.nextLine().toLowerCase();

            if (input.equals("q")) {
                System.out.println("Vous avez quitté la salle du dé.");
                inRoom = false;
            } else if (input.equals("l")) {
                int diceResult = random.nextInt(6) + 1;
                System.out.println("Vous avez fait un " + diceResult + ".");
                if (diceResult == 6) {
                    player.addGold(10);
                    System.out.println("Félicitations ! Vous gagnez 10 pièces d'or.");
                } else {
                    player.spendGold(1);
                    System.out.println("Dommage, vous perdez 1 pièce d'or.");
                }
            } else {
                System.out.println("Commande invalide, veuillez entrer 'l' ou 'q'.");
            }
        }
    }

    @Override
    public String asciiArt(Player player) {
        return "     _______________________ \n"
                + "    |                     |\n"
                + "    |     SALLE DU DÉ     |\n"
                + "    |                     |\n"
                + "    |      ________       |\n"
                + "    |     /\\       \\      |\n"
                + "    |    /  \\   O   \\     |\n"
                + "    |   /    \\       \\    |\n"
                + "    |  /______\\_______\\   |\n"
                + "    |                     |\n"
                + "    |  Personnage :       |\n"
                + "    |     " + player.getAsciiFace() + "           |\n"
                + "    |     /|\\           |\n"
                + "    |     / \\           |\n"
                + "    |___________________|\n";
    }
}