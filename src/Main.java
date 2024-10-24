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

        // Afficher les visages ASCII disponibles
        displayAsciiFaces();

        // Demander au joueur de choisir un visage ASCII
        System.out.print("Entrez le numéro de votre choix : ");
        int faceChoice = Integer.parseInt(scanner.nextLine());

        // Assigner le visage ASCII en fonction du choix
        String playerAsciiFace;
        switch (faceChoice) {
            case 1:
                playerAsciiFace = "^_^";
                break;
            case 2:
                playerAsciiFace = "O_O";
                break;
            case 3:
                playerAsciiFace = "T_T";
                break;
            case 4:
                playerAsciiFace = ">_<";
                break;
            case 5:
                playerAsciiFace = "U_U";
                break;
            default:
                playerAsciiFace = "^_^"; // Valeur par défaut si choix invalide
                System.out.println("Choix invalide, visage par défaut utilisé.");
        }

        // Créer le joueur avec le nom et le visage ASCII saisis
        Player player = new Player(playerName, playerAsciiFace);

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

    private static void displayAsciiFaces() {
        System.out.println("Choisissez un visage ASCII pour votre personnage :");
        System.out.println("1.  ^_^  ");
        System.out.println("2.  O_O  ");
        System.out.println("3.  T_T  ");
        System.out.println("4.  >_<  ");
        System.out.println("5.  U_U   ");
    }

    private static void showMenu() {
        System.out.println("Que voulez-vous faire ?");
        System.out.println("1. Explorer le donjon");
        System.out.println("2. Visiter le magasin d'armes");
        System.out.println("3. Quitter le jeu");
    }

    private static int getUserInput(Scanner scanner) {
        System.out.print("Entrez votre choix : ");
        return Integer.parseInt(scanner.nextLine());
    }

    private static void enterDungeon(Player player, List<DungeonPiece> dungeonPieces, Scanner scanner) {
        System.out.println("Choisissez une pièce à explorer :");
        for (int i = 0; i < dungeonPieces.size(); i++) {
            System.out.println((i + 1) + ". " + dungeonPieces.get(i).getDescription());
        }
        System.out.print("Entrez le numéro de la pièce : ");
        int pieceChoice = getUserInput(scanner) - 1;

        if (pieceChoice >= 0 && pieceChoice < dungeonPieces.size()) {
            DungeonPiece chosenPiece = dungeonPieces.get(pieceChoice);
            System.out.println(chosenPiece.asciiArt());
            // Ici, tu peux ajouter une logique pour gérer ce qui se passe dans chaque pièce.
            if (chosenPiece instanceof MonsterRoom) {
                exploreMonsterRoom(player, scanner);
            } else if (chosenPiece instanceof TreasureRoom) {
                enterTreasureRoom(player);
            }
        } else {
            System.out.println("Choix de pièce invalide.");
        }
    }

    private static void exploreMonsterRoom(Player player, Scanner scanner) {
        System.out.println("Vous êtes dans une salle remplie de monstres !");
        MonsterGenerator monsterGenerator = new MonsterGenerator();
        Monster monster = monsterGenerator.generateMonster(player.getLevel());
        System.out.println("Un " + monster.getName() + " apparaît !");

        // Logique de combat ici
        while (player.isAlive() && monster.isAlive()) {
            System.out.print("Que voulez-vous faire ? (1: Attaquer, 2: Fuir) : ");
            int action = getUserInput(scanner);
            if (action == 1) {
                int damage = player.attack();
                monster.takeDamage(damage); // Infliger des dégâts au monstre
                if (monster.isAlive()) {
                    int monsterDamage = monster.attack();
                    player.takeDamage(monsterDamage); // Infliger des dégâts au joueur
                }
            } else if (action == 2) {
                System.out.println("Vous avez fui le combat !");
                break;
            } else {
                System.out.println("Choix invalide. Vous perdez votre tour.");
            }
        }

        if (!player.isAlive()) {
            System.out.println("Vous êtes mort. Game Over !");
        } else if (!monster.isAlive()) {
            System.out.println("Vous avez vaincu le " + monster.getName() + " !");
            player.gainExperience(50); // Gagner de l'expérience après la victoire

            // Gagner de l'argent après avoir tué le monstre
            int goldGained = monster.getGold();
            player.addGold(goldGained); // Ajoute de l'or au joueur

            // Message pour indiquer combien d'argent a été gagné
            System.out.println("Vous avez gagné " + goldGained + " pièces d'or en tuant le " + monster.getName() + " !");
        }
    }

    private static void enterTreasureRoom(Player player) {
        System.out.println("Vous êtes entré dans une salle au trésor !");
        // Logique pour récupérer le trésor ici
        Item treasure = new Item("Potion de soin", "Restaure 20 PV");
        player.addItemToInventory(treasure);
        System.out.println("Vous avez trouvé " + treasure.getName() + " !");
    }

    private static void visitWeaponStore(Player player, Scanner scanner) {
        WeaponStore store = new WeaponStore(); // Création d'une instance de WeaponStore
        store.visit(player); // Appel de la méthode visit pour que le joueur entre dans le magasin
    }

}
