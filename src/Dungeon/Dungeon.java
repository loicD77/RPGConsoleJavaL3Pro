package Dungeon;

import WeaponOriginal.ProtectionStore;
import WeaponOriginal.WeaponStore;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dungeon {
    private List<DungeonPiece> pieces;

    public Dungeon() {
        pieces = new ArrayList<>();
        // Ajout des pièces du donjon avec les arguments nécessaires
        pieces.add(new WeaponStore("Magasin d'Armes", "Un endroit pour acheter des armes", 1));
        pieces.add(new ProtectionStore("Magasin de Protection", "Un endroit pour acheter des protections", 1));
        pieces.add(new TreasureRoom("Salle au Trésor", "Un endroit pour découvrir des trésors", 1));
        // Ajoute d'autres pièces selon tes besoins
    }

    public void enterDungeon(Player player, Scanner scanner) {
        boolean inDungeon = true;

        while (inDungeon) {
            System.out.println("Vous êtes dans le donjon. Que souhaitez-vous faire ?");
            System.out.println("1. Visiter le magasin d'armes");
            System.out.println("2. Visiter le magasin de protection");
            System.out.println("3. Entrer dans la salle au trésor");
            System.out.println("4. Quitter le donjon");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    ((WeaponStore) pieces.get(0)).visitWeaponStore(player, scanner);
                    break;
                case 2:
                    ((ProtectionStore) pieces.get(1)).visitProtectionStore(player, scanner);
                    break;
                case 3:
                    pieces.get(2).enter(player); // Appelle la méthode enter de TreasureRoom
                    break;
                case 4:
                    System.out.println("Vous quittez le donjon.");
                    inDungeon = false; // Sortir de la boucle
                    break;
                default:
                    System.out.println("Choix invalide.");
                    break;
            }
        }
    }
}
