package MonsterGroup;

import MonsterOriginal.Monster;

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
}
