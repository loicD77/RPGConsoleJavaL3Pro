import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MonsterGenerator monsterGenerator = new MonsterGenerator();

        // Demander le nom du personnage
        System.out.print("Entrez le nom de votre personnage : ");
        String playerName = scanner.nextLine();
        Player player = new Player(playerName); // Créez le joueur avec le nom saisi

        // Afficher l'ASCII art du personnage
        System.out.println(player.asciiArt());

        System.out.println("Bienvenue dans le donjon !");

        // Créer une liste de pièces du donjon
        List<DungeonPiece> dungeonPieces = new ArrayList<>();
        dungeonPieces.add(new MonsterRoom());
        dungeonPieces.add(new TreasureRoom()); // Exemple d'une autre pièce
        // Ajouter d'autres pièces selon les besoins

        while (true) {
            // Afficher les informations du personnage
            player.displayStatus();
            showMenu();

            int action = getUserInput(scanner);

            switch (action) {
                case 1:
                    enterDungeon(player, dungeonPieces, scanner);
                    break;
                case 2:
                    visitWeaponStore(player, scanner);
                    break;
                case 3:
                    System.out.println("Merci d'avoir joué !");
                    scanner.close();
                    return; // Quitter le jeu
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }

    private static void enterDungeon(Player player, List<DungeonPiece> dungeonPieces, Scanner scanner) {
        System.out.println("Choisissez une pièce à explorer :");
        for (int i = 0; i < dungeonPieces.size(); i++) {
            System.out.println((i + 1) + ". " + dungeonPieces.get(i).description());
        }
        System.out.print("Entrez le numéro de la pièce : ");
        int pieceChoice = getUserInput(scanner) - 1;

        if (pieceChoice >= 0 && pieceChoice < dungeonPieces.size()) {
            DungeonPiece chosenPiece = dungeonPieces.get(pieceChoice);
            System.out.println(chosenPiece.asciiArt());
            // Ici, tu peux ajouter une logique pour gérer ce qui se passe dans chaque pièce.
            if (chosenPiece instanceof MonsterRoom) {
                exploreMonsterRoom(player, scanner); // Passer scanner ici
            } else if (chosenPiece instanceof TreasureRoom) {
                enterTreasureRoom(player);
            }
            // Ajouter d'autres conditions pour d'autres pièces
        } else {
            System.out.println("Choix de pièce invalide.");
        }
    }

    private static void exploreMonsterRoom(Player player, Scanner scanner) {
        MonsterGenerator monsterGenerator = new MonsterGenerator();
        Monster monster = monsterGenerator.generateMonster(player.getLevel());
        System.out.println("Vous tombez sur un monstre : ");
        System.out.println(monster.asciiArt());

        // Boucle de combat
        combat(player, monster, scanner); // Passer scanner ici
    }

    private static void enterTreasureRoom(Player player) {
        System.out.println("Vous entrez dans une pièce au trésor ! Vous trouvez de l'or et des objets !");
        // Ajouter la logique pour le trésor ici (ex. : augmenter l'or, objets, etc.)
    }

    private static void combat(Player player, Monster monster, Scanner scanner) {
        while (monster.isAlive() && player.isAlive()) {
            System.out.println("Monstre : " + monster.getName() + " (PV : " + monster.getHealth() + ")");
            System.out.println("Que souhaitez-vous faire ?");
            System.out.println("1. Attaquer");
            System.out.println("2. Défendre");
            System.out.print("Choisissez une action : ");
            int combatAction = getUserInput(scanner);

            switch (combatAction) {
                case 1:
                    // Attaque
                    int damageDealt = player.attack();
                    monster.takeDamage(damageDealt);
                    System.out.println("Vous infligez " + damageDealt + " dégâts au " + monster.getName() + "!");
                    break;
                case 2:
                    // Défense (optionnel)
                    System.out.println("Vous vous défendez ! Réduction des dégâts à venir.");
                    break;
                default:
                    System.out.println("Action invalide.");
            }

            // Si le monstre est toujours vivant, il attaque
            if (monster.isAlive()) {
                int damageTaken = monster.attack();
                player.takeDamage(damageTaken);
                System.out.println(monster.getName() + " vous inflige " + damageTaken + " dégâts !");
            }
        }

        // Vérifier si le joueur ou le monstre est vaincu
        if (!player.isAlive()) {
            System.out.println("Vous êtes vaincu... Game Over !");
            scanner.close();
        } else {
            System.out.println("Vous avez vaincu " + monster.getName() + " !");
        }
    }

    private static void showMenu() {
        System.out.println("Que souhaitez-vous faire ?");
        System.out.println("1. Explorer le donjon");
        System.out.println("2. Aller au magasin d'armes");
        System.out.println("3. Quitter le jeu");
        System.out.print("Choisissez une action : ");
    }

    private static int getUserInput(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Veuillez entrer un nombre valide : ");
            }
        }
    }

    private static void visitWeaponStore(Player player, Scanner scanner) {
        WeaponStore weaponStore = new WeaponStore();
        weaponStore.visit(player);
    }
}
