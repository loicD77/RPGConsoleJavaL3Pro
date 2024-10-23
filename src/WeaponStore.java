import java.util.Scanner;

public class WeaponStore {
    public void visit(Player player) {
        Scanner scanner = new Scanner(System.in);
        showWeapons(); // Affiche les armes disponibles

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
    }

    // Changez l'accessibilité de la méthode à public
    public void showWeapons() {
        System.out.println("Bienvenue au magasin d'armes !");
        System.out.println("1. " + new Axe().getDescription());
        System.out.println("2. " + new Bow().getDescription());
        System.out.println("3. " + new Hammer().getDescription());
    }
}
