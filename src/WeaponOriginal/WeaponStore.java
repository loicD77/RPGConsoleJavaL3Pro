package WeaponOriginal;

import Dungeon.DungeonPiece;
import Player.Player;
import WeaponGroup.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WeaponStore extends DungeonPiece {

    private List<Weapon> weapons;

    public WeaponStore(String name, String description, int requiredLevel) {
        super(name, description, requiredLevel);
        // Initialiser la liste des armes disponibles dans le magasin
        weapons = new ArrayList<>();
        initializeWeapons();
    }

    private void initializeWeapons() {
        // Utiliser des classes concrètes au lieu de classes abstraites
        weapons.add(new EpeeHero());  // Classe concrète héritant de Sword
        weapons.add(new LameSombre());  // Classe concrète héritant de Sword
        weapons.add(new SabreLumiere());  // Classe concrète héritant de Sword
        weapons.add(new EpeeDragon());  // Classe concrète héritant de Sword

        weapons.add(new HacheDeGuerre());  // Classe concrète héritant de Axe
        weapons.add(new HacheFeu());  // Classe concrète héritant de Axe
        weapons.add(new HacheGelee());  // Classe concrète héritant de Axe
        weapons.add(new HacheBerserker());  // Classe concrète héritant de Axe
    }

    @Override
    public void enter(Player player) {
        Scanner scanner = new Scanner(System.in);
        visitWeaponStore(player, scanner);
    }

    public void visitWeaponStore(Player player, Scanner scanner) {
        boolean inStore = true;

        while (inStore) {
            // Afficher l'art ASCII de la maison d'armes avec le visage du joueur
            System.out.println(asciiArt(player));

            showWeapons();

            System.out.print("Choisissez une option (numéro) : ");
            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Pour éviter les problèmes d'entrée
                if (choice < 1 || choice > weapons.size() + 1) {
                    System.out.println("Choix invalide.");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre.");
                scanner.nextLine(); // Pour vider le buffer du scanner
                continue;
            }

            if (choice == weapons.size() + 1) {  // Quitter le magasin
                System.out.println("Vous quittez le magasin d'armes.");
                inStore = false;
                continue;
            }

            Weapon selectedWeapon = weapons.get(choice - 1);
            int cost = selectedWeapon.getPrice();

            // Vérifier si le joueur a assez d'or pour acheter l'arme
            if (player.getGold() >= cost) {
                player.spendGold(cost);
                if (player.getInventory().addItem(selectedWeapon)) {
                    System.out.println("Vous avez acheté et ajouté " + selectedWeapon.getName() + " à votre inventaire !");
                } else {
                    System.out.println("Votre inventaire est plein, vous ne pouvez pas acheter cette arme !");
                    manageFullInventory(player, scanner);  // Gérer la vente ou l'abandon d'objet
                }
            } else {
                System.out.println("Vous n'avez pas assez d'or pour acheter cette arme !");
            }

            player.displayStatus();
        }

        // Retour à la carte après avoir quitté le magasin
        System.out.println("Vous êtes de retour sur la carte.");
    }


    public void showWeapons() {
        System.out.println("Bienvenue au magasin d'armes !");
        int i = 1;
        for (Weapon weapon : weapons) {
            System.out.println(i + ". " + weapon.getDescription() + " - Coût : " + weapon.getPrice() + " pièces d'or");
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
                    System.out.println("Vous quittez le magasin d'armes.");
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
                "     |      Maison d'Armes   |     \n" +
                "     |_______________________|     \n" +
                "     |                       |     \n" +
                "     |          O            |     \n" +
                "     |         /|\\           |     \n" +
                "     |         / \\           |     \n" +
                "     |      [========]       |     \n" +
                "     |      |  Hache  |      |     \n" +
                "     |      |   Arc   |      |     \n" +
                "     |      | Marteau |      |     \n" +
                "     |      [========]       |     \n" +
                "     |                       |     \n" +
                "     |    Personnage :       |     \n" +
                "     |         " + asciiFace + "           |     \n" +
                "     |         /|\\           |     \n" +
                "     |         / \\           |     \n" +
                "     |_______________________|     \n";
    }
}
