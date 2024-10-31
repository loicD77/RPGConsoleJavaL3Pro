package Dungeon;
import Dungeon.DungeonPiece;
import Player.Player;

public class BossRoom extends DungeonPiece {
    public BossRoom(String name, String description, int requiredLevel) {
        super(name, description, requiredLevel);
    }

    @Override
    public void enter(Player player) {
        System.out.println("Vous entrez dans la " + getName() + " !");
        if (player.getLevel() < getRequiredLevel()) {
            System.out.println("Vous n'avez pas le niveau requis pour entrer !");
            return;
        }
        System.out.println("Le boss vous attend ! Soyez prêt pour un combat difficile !");
        // Logique de combat avec le boss à implémenter ici
    }

    @Override
    public String asciiArt(Player player) {
        return "=== Salle du Boss ===\n" +
                "    |  \n" +
                "   /|\\ \n" +
                "  / | \\ \n" +
                " /  |  \\ \n" +
                "--------------------------\n" +
                "Personnage : " + player.getAsciiFace() + "\n";
    }
}

