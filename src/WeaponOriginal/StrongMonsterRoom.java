package WeaponOriginal;

import Dungeon.DungeonPiece; // Assurez-vous que l'emplacement de DungeonPiece est correct.
import Player.Player; // Assurez-vous d'importer Player.
import MonsterOriginal.Monster;
import MonsterGroup.StrongMonster;

import java.util.ArrayList;
import java.util.List;

public class StrongMonsterRoom extends DungeonPiece {

    private List<Monster> monsters;

    public StrongMonsterRoom(String name, String description, int requiredLevel) {
        super(name, description, requiredLevel);
        this.monsters = new ArrayList<>();
        // Ajouter des monstres forts spécifiques à cette pièce
        monsters.add(new StrongMonster("Cyclope", "Un monstre gigantesque avec un seul œil", 100, 25, 5, 20, 50, 20));
        monsters.add(new StrongMonster("Dragon", "Un dragon imposant qui crache du feu", 200, 40, 10, 30, 100, 50));
    }

    @Override
    public void enter(Player player) {
        System.out.println("Vous êtes entré dans la Salle des Monstres Puissants !");
        for (Monster monster : monsters) {
            System.out.println("Un " + monster.getName() + " apparaît !");
            handleMonster(player, monster);
        }
    }

    private void handleMonster(Player player, Monster monster) {
        // Logique de combat contre le monstre
        System.out.println("Le combat commence avec " + monster.getName() + " !");
        while (player.isAlive() && monster.isAlive()) {
            // Logique de combat ici (comme attaque du joueur, attaque du monstre, etc.)
            System.out.println(player.getName() + " attaque le " + monster.getName());
            player.attack(monster, 1); // Appeler une attaque simple
            if (!monster.isAlive()) {
                System.out.println("Vous avez vaincu " + monster.getName() + " !");
                player.gainExperience(monster.getExperiencePoints());
                break;
            }
            monster.attack(player);
            if (!player.isAlive()) {
                System.out.println("Vous avez été vaincu par " + monster.getName() + "...");
                break;
            }
        }
    }

    @Override
    public String asciiArt(Player player) {
        return "   ______    \n" +
                "  |      |   \n" +
                "  | STRONG |  \n" +
                "  | MONSTER |\n" +
                "  | ROOM   | \n" +
                "  |        | \n" +
                "  \\________/ \n" +
                "Personnage : " + player.getAsciiFace() + "\n" +
                "Nom : " + player.getName() + "\n" +
                "Niveau : " + player.getLevel() + "\n" +
                "PV : " + player.getHealth() + "/" + player.getMaxHealth() + "\n" +
                "Or : " + player.getGold() + "\n";
    }
}
