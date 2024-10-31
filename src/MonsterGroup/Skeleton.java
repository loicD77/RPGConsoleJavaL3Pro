package MonsterGroup;
import MonsterOriginal.Monster;


public class Skeleton extends Monster {
    public Skeleton(int level) {
        // Ajoutez une valeur pour specialAttackChance et experiencePoints
        super("Squelette", "Mort-vivant", 25 + (level * 5), 4 + level, level, 10, 20, 12);
        // Ajustement de la santé et des dégâts selon le niveau
    }

    @Override
    public String asciiArt() {
        return "  💀  \n" +
                " /|\\ \n" +
                " / \\ \n";
    }

    @Override
    protected int specialAttack() {
        System.out.println("Le Squelette tire une flèche mortelle !");
        return getBaseDamage() * 2; // Utilisez getBaseDamage() pour les dégâts doublés
    }
}
