import java.util.Scanner;

public class CharacterCustomization {
    private String hat;
    private String sex;
    private String size;
    private String clothing;

    public void customize() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Choisissez un chapeau (Aucun, Chapeau, Casque) : ");
        hat = scanner.nextLine();

        System.out.print("Choisissez un sexe (Homme, Femme) : ");
        sex = scanner.nextLine();

        System.out.print("Choisissez une taille (Petit, Moyen, Grand) : ");
        size = scanner.nextLine();

        System.out.print("Choisissez des vêtements (Aucun, T-shirt, Armure) : ");
        clothing = scanner.nextLine();
    }

    public String getASCIIArt() {
        StringBuilder art = new StringBuilder();

        // Ajouter le chapeau
        if (hat.equalsIgnoreCase("Chapeau")) {
            art.append("   ____   \n");
        } else if (hat.equalsIgnoreCase("Casque")) {
            art.append("  _______ \n");
        }

        art.append("  O  \n");

        // Ajouter les vêtements selon le choix
        if (clothing.equalsIgnoreCase("T-shirt")) {
            art.append(" /|\\ \n");
        } else if (clothing.equalsIgnoreCase("Armure")) {
            art.append(" /|\\\\ \n");
        } else {
            art.append(" /|\\ \n");
        }

        art.append(" / \\ \n");

        return art.toString();
    }
}
