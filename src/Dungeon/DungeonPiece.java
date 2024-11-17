package Dungeon;

import MonsterOriginal.Monster;
import Player.Player; // Importez la classe Player du package Player

import java.util.ArrayList;
import java.util.List;

public abstract class DungeonPiece {
    protected String name;
    protected String description; // Description de la pièce
    protected List<Monster> monsters; // Liste des monstres dans la pièce
    protected Monster boss; // Boss de la pièce
    protected int requiredLevel; // Niveau requis pour entrer

    public DungeonPiece(String name, String description, int requiredLevel) {
        this.name = name;
        this.description = description;
        this.requiredLevel = requiredLevel;
        this.monsters = new ArrayList<>();
    }

    public abstract void enter(Player player); // Définir ce que signifie entrer dans chaque pièce

    public void addMonster(Monster monster) {
        monsters.add(monster);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public Monster getBoss() {
        return boss;
    }

    public void setBoss(Monster boss) {
        this.boss = boss;
    }

    public int getRequiredLevel() {
        return requiredLevel;
    }

    public boolean canEnter(Player player) {
        return player.getLevel() >= requiredLevel;
    }

    public void displayMonsters() {
        if (monsters.isEmpty()) {
            System.out.println("Aucun monstre présent dans la salle.");
        } else {
            System.out.println("Monstres présents dans la salle :");
            for (Monster monster : monsters) {
                monster.displayStatus();
            }
        }
    }

    public abstract String asciiArt(Player player);
}