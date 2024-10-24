import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Dungeon {
    private Map<String, DungeonPiece> pieces;
    private Scanner scanner;
    private Player player;

    public Dungeon(Player player) {
        pieces = new HashMap<>();
        scanner = new Scanner(System.in);
        this.player = player;
        initializeDungeon();
    }

    private void initializeDungeon() {
        pieces.put("entrance", new Entrance());
        pieces.put("monsterRoom", new MonsterRoom());
        pieces.put("treasureRoom", new TreasureRoom());
    }

    public void start() {
        String currentPiece = "entrance"; // Commence à l'entrée
        while (true) {
            DungeonPiece piece = pieces.get(currentPiece);
            System.out.println(piece.asciiArt());
            System.out.println(piece.getDescription()); // Utiliser getDescription() au lieu de description()
            player.displayStatus();
            System.out.println("Où voulez-vous aller ? (saisissez 'sortie' pour quitter)");

            String action = scanner.nextLine();

            // Logique de navigation simple
            if (action.equalsIgnoreCase("monstre")) {
                currentPiece = "monsterRoom";
            } else if (action.equalsIgnoreCase("trésor")) {
                currentPiece = "treasureRoom";
            } else if (action.equalsIgnoreCase("sortie")) {
                System.out.println("Vous quittez le donjon.");
                break;
            } else {
                System.out.println("Choix invalide. Essayez 'monstre', 'trésor' ou 'sortie'.");
            }

            // Gérer les interactions spéciales dans MonsterRoom
            if (currentPiece.equals("monsterRoom")) {
                System.out.println("Un monstre apparaît ! Que voulez-vous faire ?");
                System.out.println("1. Combattre");
                System.out.println("2. Fuir");

                String choice = scanner.nextLine();
                if (choice.equals("1")) {
                    // Logique de combat ici
                    System.out.println("Vous engagez le combat !");
                    // Ici tu peux ajouter la logique de combat
                } else if (choice.equals("2")) {
                    System.out.println("Vous fuyez la salle des monstres.");
                    currentPiece = "entrance"; // Retour à l'entrée ou une autre pièce
                } else {
                    System.out.println("Choix invalide. Vous restez ici.");
                }
            }
        }
    }

}
