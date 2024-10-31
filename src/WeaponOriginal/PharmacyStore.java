package WeaponOriginal;

import PotionGroup.HealthPotion;
import PotionGroup.HypnosisCurePotion;

import java.util.Scanner;

public class PharmacyStore extends DungeonPiece {

    public PharmacyStore(String name, String description, int level) {
        super(name, description, level);
    }

    // Implémentation de la méthode asciiArt() de DungeonPiece
    @Override
    public String asciiArt(Player player) {
        return "      _______________________      \n" +
                "     |                       |     \n" +
                "     |       La Pharmacie    |     \n" +
                "     |_______________________|     \n" +
                "     |                       |     \n" +
                "     |          O            |     \n" +
                "     |         /|\\           |     \n" +
                "     |         / \\           |     \n" +
                "     |      [========]       |     \n" +
                "     |      |  Potions |     |     \n" +
                "     |      [========]       |     \n" +
                "     |                       |     \n" +
                "     |    Personnage :       |     \n" +
                "     |         " + player.getAsciiFace() + "           |     \n" +
                "     |         /|\\           |     \n" +
                "     |         / \\           |     \n" +
                "     |_______________________|     \n";
    }

    // Méthode pour entrer dans la pharmacie
    @Override
    public void enter(Player player) {
        Scanner scanner = new Scanner(System.in);
        visitPharmacy(player, scanner);
    }

    public void visitPharmacy(Player player, Scanner scanner) {
        boolean keepShopping = true;

        while (keepShopping) {
            // Afficher l'art ASCII de la pharmacie avec le visage du joueur
            System.out.println(asciiArt(player));

            // Afficher les options d'achat
            showPotions();

            System.out.print("Choisissez une potion à acheter (numéro) : ");
            int choice = scanner.nextInt();

            Potion potion = null; // Initialiser la potion ici
            int cost = 0; // Coût de la potion choisie

            switch (choice) {
                case 1:
                    potion = new HealthPotion("Potion de Soin", 50, 10);
                    cost = 10;
                    break;
                case 2:
                    potion = new AntidotePotion("Antidote", "Ça soigne", 50, 15);
                    cost = 15;
                    break;
                case 3:
                    potion = new ParalysisCurePotion("Potion de Paralysie", 20, 20);
                    cost = 20;
                    break;
                case 4:
                    potion = new HypnosisCurePotion("Potion d'Hypnose", 25, 25);
                    cost = 25;
                    break;
                case 5: // Option pour quitter
                    System.out.println("Vous quittez la pharmacie.");
                    keepShopping = false; // Sortir de la boucle
                    continue; // Continuer à la prochaine itération pour sortir de la méthode
                default:
                    System.out.println("Choix invalide.");
                    continue; // Recommencer la boucle pour demander à nouveau
            }

            if (potion != null) { // Vérifier si la potion a été créée
                if (player.getGold() >= cost) {
                    player.spendGold(cost);

                    // Ajouter la potion à l'inventaire du joueur
                    if (player.getInventory().addItem(potion)) {
                        System.out.println("Vous avez acheté " + potion.getName() + " !");
                    } else {
                        System.out.println("Votre inventaire est plein, vous ne pouvez pas acheter cette potion !");
                    }
                } else {
                    System.out.println("Vous n'avez pas assez d'or pour acheter cette potion !");
                }
            }

            // Afficher l'état du joueur après l'achat
            player.displayStatus();
        }
    }

    // Méthode pour afficher les potions disponibles
    public void showPotions() {
        System.out.println("Bienvenue à la pharmacie !");
        System.out.println("1. Potion de soin - Coût : 10 pièces d'or");
        System.out.println("2. Antidote (contre le poison) - Coût : 15 pièces d'or");
        System.out.println("3. Potion contre la paralysie - Coût : 20 pièces d'or");
        System.out.println("4. Potion contre l'hypnose - Coût : 25 pièces d'or");
        System.out.println("5. Quitter le magasin");
    }
}
