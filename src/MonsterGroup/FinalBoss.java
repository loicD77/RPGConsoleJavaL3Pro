package MonsterGroup;

import MonsterOriginal.Monster;

public class FinalBoss extends Monster {
    public FinalBoss() {
        super("Boss Final", "Créature ultime", 200, 30, 5, 10, 100, 200); // Ajoutez une valeur pour experiencePoints
    }

    @Override
    public String asciiArt() {
        return "  B  \n" +
                " /|\\ \n" +
                " / \\ \n";
    }

    @Override
    protected int specialAttack() {
        System.out.println("Le Boss Final lance une attaque dévastatrice !");
        return getBaseDamage() * 5; // Attaque spéciale dévastatrice
    }
}
