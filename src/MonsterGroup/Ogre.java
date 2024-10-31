package MonsterGroup;
import MonsterOriginal.Monster;
public class Ogre extends Monster {

    // Variable pour stocker l'état de rage de l'Ogre
    private boolean enraged;

    public Ogre(int level) {
        super("Ogre", "Une créature massive", 50 + (level * 5), 10 + level, level, 5, 30, 17);
    }

    @Override
    public String asciiArt() {
        return "  O  \n" +
                " /|\\ \n" +
                " / \\ \n";
    }

    @Override
    protected int specialAttack() {
        if (!enraged) {
            System.out.println("L'Ogre s'énerve et entre dans une rage furieuse !");
            enraged = true; // L'Ogre devient enragé après sa première attaque spéciale
        }
        System.out.println("L'Ogre utilise sa force brute enragée !");
        return getBaseDamage() + 10; // L'attaque spéciale devient plus puissante si l'Ogre est enragé
    }

    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);
        if (isAlive()) {
            System.out.println("L'Ogre semble devenir encore plus furieux à mesure qu'il subit des dégâts !");
        } else {
            System.out.println("L'Ogre s'effondre dans un fracas immense !");
        }
    }

    @Override
    public void displayStatus() {
        super.displayStatus();
        System.out.println("État de rage : " + (enraged ? "Enragé" : "Calme"));
    }

    // L'Ogre peut se soigner légèrement s'il est enragé
    public void regenerate() {
        if (enraged) {
            int healAmount = 10;
            health += healAmount;
            health = Math.min(health, maxHealth); // Ne pas dépasser la santé maximale
            System.out.println("L'Ogre enragé se régénère et récupère " + healAmount + " points de vie !");
        }
    }
}
