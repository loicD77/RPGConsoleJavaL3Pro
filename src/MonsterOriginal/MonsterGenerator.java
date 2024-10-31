package MonsterOriginal;

import MonsterGroup.Skeleton;
import MonsterGroup.Goblin;
import MonsterGroup.Ogre;
import MonsterGroup.Dragon;
import MonsterGroup.Zombie;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MonsterGenerator {
    private Random random = new Random();
    private List<Monster> availableMonsters = new ArrayList<>();

    public MonsterGenerator() {
        // Initialiser la liste des monstres disponibles avec un niveau par défaut
        availableMonsters.add(new Skeleton(1));
        availableMonsters.add(new Goblin(1));
        availableMonsters.add(new Ogre(1));
        availableMonsters.add(new Dragon(1));
        availableMonsters.add(new Zombie(1));
    }

    public Monster generateMonster(int playerLevel) {
        int monsterIndex = random.nextInt(availableMonsters.size());
        Monster baseMonster = availableMonsters.get(monsterIndex);

        // Crée un monstre basé sur le niveau du joueur
        return createMonsterWithLevel(baseMonster, playerLevel);
    }

    private Monster createMonsterWithLevel(Monster baseMonster, int playerLevel) {
        int adjustedLevel = playerLevel + random.nextInt(3); // Ajout d'un petit aléatoire pour diversifier les niveaux
        if (baseMonster instanceof Skeleton) {
            return new Skeleton(adjustedLevel);
        } else if (baseMonster instanceof Goblin) {
            return new Goblin(adjustedLevel);
        } else if (baseMonster instanceof Ogre) {
            return new Ogre(adjustedLevel);
        } else if (baseMonster instanceof Dragon) {
            return new Dragon(adjustedLevel);
        } else if (baseMonster instanceof Zombie) {
            return new Zombie(adjustedLevel);
        } else {
            return null; // Si rien ne correspond
        }
    }
}
