import java.util.Scanner;

public class WeaponStore {
    public void visit(Player player) {
        Scanner scanner = new Scanner(System.in);

        // Afficher l'art ASCII du magasin et des personnages
        displayStoreArt(player);

        // Afficher les armes disponibles
        showWeapons();

        // Demander à l'utilisateur de choisir une arme
        System.out.print("Choisissez une arme à équiper (numéro) : ");
        int choice = scanner.nextInt();

        Weapon weapon;
        int cost = 0; // Coût de l'arme choisie

        switch (choice) {
            case 1:
                weapon = new Axe();
                cost = 20; // Coût de l'axe
                break;
            case 2:
                weapon = new Bow();
                cost = 15; // Coût de l'arc
                break;
            case 3:
                weapon = new Hammer();
                cost = 25; // Coût du marteau
                break;
            default:
                System.out.println("Choix invalide.");
                return;
        }

        // Vérifier si le joueur a assez d'or pour acheter l'arme
        if (player.getGold() >= cost) {
            player.spendGold(cost); // Déduire le coût de l'or du joueur
            player.equipWeapon(weapon); // Équipe directement l'arme choisie
            System.out.println("Vous avez équipé " + weapon.getName() + " !");
        } else {
            System.out.println("Vous n'avez pas assez d'or pour acheter cette arme !");
            return;
        }

        // Afficher l'état du joueur après avoir équipé l'arme
        player.displayStatus();
    }

    // Méthode pour afficher les armes disponibles
    public void showWeapons() {
        System.out.println("Bienvenue au magasin d'armes !");
        System.out.println("1. " + new Axe().getDescription() + " - Coût : 20 pièces d'or");
        System.out.println("2. " + new Bow().getDescription() + " - Coût : 15 pièces d'or");
        System.out.println("3. " + new Hammer().getDescription() + " - Coût : 25 pièces d'or");
    }

    // Méthode pour afficher l'art ASCII du magasin et des personnages
    // Méthode pour afficher l'art ASCII du magasin et des personnages
    private void displayStoreArt(Player player) {
        String asciiFace = player.getAsciiFace();  // Obtenir le visage ASCII
        System.out.println("      _______________________      ");
        System.out.println("     |                       |     ");
        System.out.println("     |      Maison d'Armes   |     ");
        System.out.println("     |_______________________|     ");
        System.out.println("     |                       |     ");
        System.out.println("     |          O            |     ");
        System.out.println("     |         /|\\           |     ");
        System.out.println("     |         / \\           |     ");
        System.out.println("     |      [========]       |     ");
        System.out.println("     |      |  Hache  |      |     ");
        System.out.println("     |      |   Arc   |      |     ");
        System.out.println("     |      | Marteau |      |     ");
        System.out.println("     |      [========]       |     ");
        System.out.println("     |                       |     ");
        System.out.println("     |    Personnage :       |     ");
        System.out.println("     |         "+asciiFace +"           |     ");  // Assurer que l'art du visage est bien centré
        System.out.println("     |         /|\\           |     ");
        System.out.println("     |         / \\           |     ");
        System.out.println("     |_______________________|     ");
        System.out.println("Nom : " + player.getName());
        System.out.println("Niveau : " + player.getLevel());
        System.out.println("PV : " + player.getHealth());
        System.out.println("Or : " + player.getGold());

    }


}
