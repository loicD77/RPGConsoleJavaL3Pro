package WeaponOriginal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProtectionStore extends DungeonPiece {

    private List<ProtectionItem> protectionItems;

    public ProtectionStore(String name, String description, int requiredLevel) {
        super(name, description, requiredLevel);
        // Initialiser la liste des protections disponibles dans le magasin
        protectionItems = new ArrayList<>();
        protectionItems.add(new Armor("Armure légère", "Une armure offrant une protection de base.", 5, 100));
        protectionItems.add(new Shield("Bouclier en bois", "Un bouclier simple mais robuste.", 3, 75));
    }

    @Override
    public void enter(Player player) {
        Scanner scanner = new Scanner(System.in);
        visitProtectionStore(player, scanner);
    }

    public void visitProtectionStore(Player player, Scanner scanner) {
        boolean inStore = true;

        while (inStore) {
            // Afficher l'art ASCII du magasin de protection avec le visage du joueur
            System.out.println(asciiArt(player));

            showProtectionItems();

            System.out.print("Choisissez une option (numéro) : ");
            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Pour éviter les problèmes d'entrée
                if (choice < 1 || choice > protectionItems.size() + 1) {
                    System.out.println("Choix invalide.");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre.");
                scanner.nextLine(); // Pour vider le buffer du scanner
                continue;
            }

            if (choice == protectionItems.size() + 1) {  // Quitter le magasin
                System.out.println("Vous quittez le magasin de protection.");
                inStore = false;
                continue;
            }

            ProtectionItem selectedItem = protectionItems.get(choice - 1);
            int price = selectedItem.getPrice(); // Remplace getCost() par getPrice()

            // Vérifier si le joueur a assez d'or pour acheter l'objet
            if (player.getGold() >= price) {
                player.spendGold(price);
                if (player.getInventory().addItem(selectedItem)) {
                    System.out.println("Vous avez acheté et ajouté " + selectedItem.getName() + " à votre inventaire !");
                } else {
                    System.out.println("Votre inventaire est plein, vous ne pouvez pas acheter cet objet de protection !");
                    manageFullInventory(player, scanner);  // Gérer la vente ou l'abandon d'objet
                }
            } else {
                System.out.println("Vous n'avez pas assez d'or pour acheter cet objet de protection !");
            }

            player.displayStatus();
        }

        // Retour à la carte après avoir quitté le magasin
        System.out.println("Vous êtes de retour sur la carte.");
    }

    public void showProtectionItems() {
        System.out.println("Bienvenue au magasin de protection !");
        int i = 1;
        for (ProtectionItem item : protectionItems) {
            System.out.println(i + ". " + item.getDescription() + " - Coût : " + item.getPrice() + " pièces d'or"); // Remplace getCost() par getPrice()
            i++;
        }
        System.out.println(i + ". Quitter le magasin");
    }

    private void manageFullInventory(Player player, Scanner scanner) {
        System.out.println("Votre inventaire est plein. Que voulez-vous faire ?");
        System.out.println("1. Vendre un objet");
        System.out.println("2. Abandonner un objet");
        System.out.println("3. Quitter le magasin");

        int action;
        try {
            action = scanner.nextInt();
            scanner.nextLine(); // Pour éviter les problèmes d'entrée
            switch (action) {
                case 1:
                    // Appel de la méthode pour vendre un objet
                    sellItem(player, scanner);
                    break;
                case 2:
                    // Appel de la méthode pour abandonner un objet
                    dropItem(player, scanner);
                    break;
                case 3:
                    System.out.println("Vous quittez le magasin de protection.");
                    return; // Quitter la méthode
                default:
                    System.out.println("Action invalide.");
            }
        } catch (Exception e) {
            System.out.println("Entrée invalide. Veuillez entrer un nombre.");
            scanner.nextLine(); // Pour vider le buffer du scanner
        }
    }

    private void sellItem(Player player, Scanner scanner) {
        // Implémenter la logique pour vendre un objet ici
        // Afficher les objets dans l'inventaire et permettre au joueur de choisir un objet à vendre
    }

    private void dropItem(Player player, Scanner scanner) {
        // Implémenter la logique pour abandonner un objet ici
        // Afficher les objets dans l'inventaire et permettre au joueur de choisir un objet à abandonner
    }

    // Implémentation de la méthode asciiArt() de DungeonPiece
    @Override
    public String asciiArt(Player player) {
        String asciiFace = player.getAsciiFace();
        return "      _______________________      \n" +
                "     |                       |     \n" +
                "     |   Magasin de Protection |   \n" +
                "     |_______________________|     \n" +
                "     |                       |     \n" +
                "     |          O            |     \n" +
                "     |         /|\\           |     \n" +
                "     |         / \\           |     \n" +
                "     |      [========]       |     \n" +
                "     |     |  Armure  |      |     \n" +
                "     |     | Bouclier |      |     \n" +
                "     |      [========]       |     \n" +
                "     |                       |     \n" +
                "     |    Personnage :       |     \n" +
                "     |         " + asciiFace + "           |     \n" +
                "     |         /|\\           |     \n" +
                "     |         / \\           |     \n" +
                "     |_______________________|     \n";
    }

}
