package WeaponOriginal;

import Dungeon.DungeonPiece;
import Player.Player;
import ProtectiveClothing.ProtectionItem;
import ProtectiveClothing.Armor;
import ProtectiveClothing.Shield;
import ProtectiveClothing.Helmet;
import ProtectiveClothing.Gloves;
import ProtectiveClothing.Boots;
import ProtectiveClothing.Pants;



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
        protectionItems.add(new Armor("Armure de fer", "Une armure résistante offrant une meilleure protection.", 10, 200));
        protectionItems.add(new Shield("Bouclier de fer", "Un bouclier solide qui réduit grandement les dégâts.", 8, 150));
        protectionItems.add(new Helmet("Casque de chevalier", "Un casque robuste protégeant la tête des attaques.", 6, 120));
        protectionItems.add(new Gloves("Gants en cuir renforcé", "Des gants qui améliorent la défense et l'agilité.", 4, 50));
        protectionItems.add(new Boots("Bottes de combat", "Des bottes résistantes offrant une protection et une meilleure mobilité.", 5, 100));
        protectionItems.add(new Pants("Jambières en métal", "Des jambières offrant une bonne protection pour les jambes.", 7, 130));
        protectionItems.add(new Shield("Bouclier magique", "Un bouclier enchanté réduisant les dégâts magiques.", 10, 300));
        protectionItems.add(new Armor("Armure en mythril", "Une armure légère et extrêmement résistante, connue pour ses propriétés protectrices.", 15, 500));

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

            int choice = -1;
            boolean validInput = false;

            // Boucle pour s'assurer d'obtenir un choix valide
            while (!validInput) {
                System.out.print("Choisissez une option (numéro) : ");
                String input = scanner.nextLine();

                try {
                    choice = Integer.parseInt(input);
                    if (choice >= 1 && choice <= protectionItems.size() + 1) {
                        validInput = true; // Saisie correcte
                    } else {
                        System.out.println("Choix invalide, veuillez entrer un numéro entre 1 et " + (protectionItems.size() + 1) + ".");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Saisie incorrecte, veuillez entrer un nombre valide.");
                }
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
        boolean validInput = false;
        int action = -1;

        while (!validInput) {
            System.out.println("Votre inventaire est plein. Que voulez-vous faire ?");
            System.out.println("1. Vendre un objet");
            System.out.println("2. Abandonner un objet");
            System.out.println("3. Quitter le magasin");
            System.out.print("Choisissez une option : ");

            String input = scanner.nextLine();

            try {
                action = Integer.parseInt(input);
                if (action >= 1 && action <= 3) {
                    validInput = true; // Saisie correcte
                } else {
                    System.out.println("Action invalide, veuillez entrer un nombre entre 1 et 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Saisie incorrecte, veuillez entrer un nombre valide.");
            }
        }

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
                "     |     |  Casque  |      |     \n" +
                "     |     |  Gants   |      |     \n" +
                "     |     |  Bottes  |      |     \n" +
                "     |     | Pantalon |      |     \n" +
                "     |      [========]       |     \n" +
                "     |                       |     \n" +
                "     |    Personnage :       |     \n" +
                "     |         " + player.getAsciiFace() + "           |     \n" +
                "     |         /|\\           |     \n" +
                "     |         / \\           |     \n" +
                "     |_______________________|     \n";
    }

}
