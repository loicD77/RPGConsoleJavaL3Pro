import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MonsterGenerator {
    private Random random = new Random();
    private List<Monster> availableMonsters = new ArrayList<>();

    public MonsterGenerator() {
        // Initialiser la liste des monstres disponibles
        availableMonsters.add(new Skeleton());
        availableMonsters.add(new Goblin());
        availableMonsters.add(new Ogre());
        availableMonsters.add(new Dragon());
        availableMonsters.add(new Zombie());
    }

    public Monster generateMonster(int playerLevel) {
        int monsterIndex = random.nextInt(availableMonsters.size());
        Monster baseMonster = availableMonsters.get(monsterIndex);

        // Création du monstre avec le niveau du joueur
        if (baseMonster instanceof Skeleton) {
            return new Skeleton(); // Adapté pour votre logique
        } else if (baseMonster instanceof Goblin) {
            return new Goblin();
        } else if (baseMonster instanceof Ogre) {
            return new Ogre();
        } else if (baseMonster instanceof Dragon) {
            return new Dragon();
        } else if (baseMonster instanceof Zombie) {
            return new Zombie();
        } else {
            return null;
        }
    }
}
