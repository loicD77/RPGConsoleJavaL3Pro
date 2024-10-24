/import java.util.Scanner;

public class PharmacyStore {
    public void visit(Player player) {
        Scanner scanner = new Scanner(System.in);

        // Afficher l'art ASCII de la pharmacie
        displayStoreArt(player);

        // Afficher les potions disponibles
        showPotions();

        // Demander à l'utilisateur de choisir une potion
        System.out.print("Choisissez une potion à acheter (numéro) : ");
        int choice = scanner.nextInt();

        Potion potion;
        int cost = 0; // Coût de la potion choisie

        switch (choice) {
            case 1:
                potion = new HealthPotion();
                cost = 10; // Coût de la potion de soin
                break;
            case 2:
                potion = new AntidotePotion();
                cost = 15; // Coût de la potion contre le poison
                break;
            case 3:
                potion = new ParalysisCurePotion();
                cost = 20; // Coût de la potion contre la paralysie
                break;
            case 4:
                potion = new HypnosisCurePotion();
                cost = 25; // Coût de la potion contre l'hypnose
                break;
            default:
                System.out.println("Choix invalide.");
                return;
        }

        // Vérifier si le joueur a assez d'or pour acheter la potion
        if (player.getGold() >= cost) {
            player.spendGold(cost); // Déduire le coût de l'or du joueur
            player.addPotion(potion); // Ajouter la potion à l'inventaire du joueur
            System.out.println("Vous avez acheté " + potion.getName() + " !");
        } else {
            System.out.println("Vous n'avez pas assez d'or pour acheter cette potion !");
        }

        // Afficher l'état du joueur après l'achat
        player.displayStatus();
    }

    // Méthode pour afficher les potions disponibles
    public void showPotions() {
        System.out.println("Bienvenue à la pharmacie !");
        System.out.println("1. Potion de soin - Coût : 10 pièces d'or");
        System.out.println("2. Antidote (contre le poison) - Coût : 15 pièces d'or");
        System.out.println("3. Potion contre la paralysie - Coût : 20 pièces d'or");
        System.out.println("4. Potion contre l'hypnose - Coût : 25 pièces d'or");
    }

    // Méthode pour afficher l'art ASCII de la pharmacie
    private void displayStoreArt(Player player) {
        String asciiFace = player.getFace(); // Récupérer le visage ASCII choisi par le joueur
        System.out.println("      _______________________      ");
        System.out.println("     |                       |     ");
        System.out.println("     |       La Pharmacie     |     ");
        System.out.println("     |_______________________|     ");
        System.out.println("     |                       |     ");
        System.out.println("     |          O            |     ");
        System.out.println("     |         /|\\           |     ");
        System.out.println("     |         / \\           |     ");
        System.out.println("     |      [========]       |     ");
        System.out.println("     |      |  Potions |     |     ");
        System.out.println("     |      [========]       |     ");
        System.out.println("     |          " + asciiFace + "            |     "); // Afficher le visage choisi
        System.out.println("     |         /|\\           |     ");
        System.out.println("     |         / \\           |     ");
        System.out.println("     |_______________________|     ");
    }
}
