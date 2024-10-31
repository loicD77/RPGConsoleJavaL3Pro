package WeaponOriginal;

import Dungeon.DungeonPiece;
import Player.Player;
import ProtectiveClothing.ProtectionItem;
import ProtectiveClothing.Armor;
import ProtectiveClothing.Shield;


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
            System.out.println(asciiArt(player));
            showProtectionItems();

            System.out.print("Choisissez une option (numéro) : ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice < 1 || choice > protectionItems.size() + 1) {
                System.out.println("Choix invalide.");
                continue;
            }

            if (choice == protectionItems.size() + 1) {
                System.out.println("Vous quittez le magasin de protection.");
                inStore = false;
                continue;
            }

            ProtectionItem selectedItem = protectionItems.get(choice - 1);
            int price = selectedItem.getPrice();

            if (player.getGold() >= price) {
                player.spendGold(price);
                if (player.getInventory().addItem(selectedItem)) {
                    System.out.println("Vous avez acheté et ajouté " + selectedItem.getName() + " à votre inventaire !");
                } else {
                    System.out.println("Votre inventaire est plein, vous ne pouvez pas acheter cet objet de protection !");
                    manageFullInventory(player, scanner);
                }
            } else {
                System.out.println("Vous n'avez pas assez d'or pour acheter cet objet de protection !");
            }

            player.displayStatus();
        }
    }

    public void showProtectionItems() {
        System.out.println("Bienvenue au magasin de protection !");
        int i = 1;
        for (ProtectionItem item : protectionItems) {
            System.out.println(i + ". " + item.getDescription() + " - Coût : " + item.getPrice() + " pièces d'or");
            i++;
        }
        System.out.println(i + ". Quitter le magasin");
    }

    private void manageFullInventory(Player player, Scanner scanner) {
        System.out.println("Votre inventaire est plein. Que voulez-vous faire ?");
        System.out.println("1. Vendre un objet");
        System.out.println("2. Abandonner un objet");
        System.out.println("3. Quitter le magasin");

        int action = scanner.nextInt();
        scanner.nextLine();
        switch (action) {
            case 1:
                sellItem(player, scanner);
                break;
            case 2:
                dropItem(player, scanner);
                break;
            case 3:
                System.out.println("Vous quittez le magasin de protection.");
                return;
            default:
                System.out.println("Action invalide.");
        }
    }

    private void sellItem(Player player, Scanner scanner) {
        // Implémentez la logique de vente ici
    }

    private void dropItem(Player player, Scanner scanner) {
        // Implémentez la logique pour abandonner un objet ici
    }

    @Override
    public String asciiArt(Player player) {
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
                "     |         " + player.getAsciiFace() + "           |     \n" +
                "     |         /|\\           |     \n" +
                "     |         / \\           |     \n" +
                "     |_______________________|     \n";
    }
}