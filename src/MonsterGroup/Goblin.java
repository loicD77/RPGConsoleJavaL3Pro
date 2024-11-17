package MonsterGroup;

import MonsterOriginal.Monster;
import Player.Player;

public class Goblin extends Monster {
    public Goblin(int level) {
        super("Gobelin", "Créature malveillante", 30 + (level * 5), 5 + level, level, 10, 20, 8);
        // maxHealth, damage, level, specialAttackChance, experiencePoints
    }

    @Override
    public String asciiArt() {
        return "   .-.   \n" +
                "  (o o)  \n" +
                "  | O |  \n" +
                "   `-`   \n";
    }

    @Override
    protected int specialAttack() {
        System.out.println("Le Gobelin lance une pierre !");
        return getBaseDamage() * 2; // Attaque spéciale : dégâts doublés
    }

    @Override
    public void attack(Player player) {
        int attackType = (int) (Math.random() * 4) + 1;
        switch (attackType) {
            case 1:
                System.out.println("Le Gobelin lance une pierre acérée sur le joueur !");
                player.takeDamage(getBaseDamage() + 5);
                break;
            case 2:
                System.out.println("Le Gobelin donne un coup de pied vicieux au joueur !");
                player.takeDamage(getBaseDamage());
                break;
            case 3:
                System.out.println("Le Gobelin utilise une attaque sournoise et vole quelques pièces d'or au joueur !");
                player.takeDamage(getBaseDamage() - 2);
                int stolenGold = 5;
                player.spendGold(stolenGold);
                addGold(stolenGold);
                System.out.println("Le Gobelin a volé " + stolenGold + " pièces d'or !");
                break;
            case 4:
                System.out.println("Le Gobelin se cache brièvement, réduisant les chances d'être touché au prochain tour.");
                setEvasionChance(getEvasionChance() + 20); // Augmenter la chance d'esquiver au prochain tour
                break;
            default:
                System.out.println("Le Gobelin semble hésiter...");
                break;
        }
    }

    @Override
    public void displayStatus() {
        super.displayStatus();
        System.out.println("Type d'attaque spéciale : Lancer de pierre");
    }
}
