import java.util.Scanner;

public class WeaponStore {
    public void visit(Player player) {
        Scanner scanner = new Scanner(System.in);

        // Afficher l'art ASCII du magasin et des personnages
        displayStoreArt(player);

        // Afficher les armes disponibles
        showWeapons();

        // Demander à l'utilisateur de choisir une arme
        System.out.print("Choisissez une arme à acheter (numéro) : ");
        int choice = scanner.nextInt();

        Weapon weapon;
        switch (choice) {
            case 1:
                weapon = new Axe();
                break;
            case 2:
                weapon = new Bow();
                break;
            case 3:
                weapon = new Hammer();
                break;
            default:
                System.out.println("Choix invalide.");
                return;
        }

        player.equipWeapon(weapon);

        // Afficher l'état du joueur après l'achat
        player.displayStatus();
    }

    // Méthode pour afficher les armes disponibles
    public void showWeapons() {
        System.out.println("Vous entrez dans une zone secrète et méconnu des monstres de ce TERRIBLE DONJON !!!");
        System.out.println("Bienvenue au magasin d'armes !");
        System.out.println("1. " + new Axe().getDescription());
        System.out.println("2. " + new Bow().getDescription());
        System.out.println("3. " + new Hammer().getDescription());
    }

    // Méthode pour afficher l'art ASCII du magasin et des personnages
    private void displayStoreArt(Player player) {
        System.out.println("      _______________________      ");
        System.out.println("     |                       |     ");
        System.out.println("     |      Maison d'Armes   |     ");
        System.out.println("     |_______________________|     ");
        System.out.println("     |                       |     ");
        System.out.println("     |          O            |     "); // Vendeur
        System.out.println("     |         /|\\           |     "); // Vendeur
        System.out.println("     |         / \\           |     "); // Vendeur
        System.out.println("     |      [========]       |     "); // Comptoir
        System.out.println("     |      |  Hache  |      |     "); // Armes
        System.out.println("     |      |   Arc   |      |     "); // Armes
        System.out.println("     |      | Marteau |      |     "); // Armes
        System.out.println("     |      [========]       |     "); // Fin du comptoir
        System.out.println("     |          O            |     "); // Deuxième personnage
        System.out.println("     |         /|\\           |     "); // Deuxième personnage
        System.out.println("     |         / \\           |     "); // Deuxième personnage
        System.out.println("     |                       |     ");
        System.out.println("     |                       |     ");
        System.out.println("     |                       |     ");
        System.out.println("     |                       |     ");
        System.out.println("     |_______________________|     ");
    }
}
