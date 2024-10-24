import java.util.ArrayList;
import java.util.List;

public abstract class DungeonPiece {
    public abstract void enter(Player player);

    protected String name;
    protected String description; // Ajoutez une description pour la pièce
    protected List<Monster> monsters; // Liste des monstres dans la pièce
    protected Monster boss; // Boss de la pièce
    protected int requiredLevel; // Niveau requis pour entrer

    public DungeonPiece(String name, String description, int requiredLevel) {
        this.name = name;
        this.description = description; // Initialisez la description
        this.requiredLevel = requiredLevel;
        this.monsters = new ArrayList<>(); // Initialisez la liste des monstres
    }


    public void addMonster(Monster monster) {
        monsters.add(monster);
    }

    public String getName() {
        return name; // Méthode pour obtenir le nom de la pièce
    }

    public String getDescription() {
        return description; // Méthode pour obtenir la description
    }

    public List<Monster> getMonsters() {
        return monsters; // Méthode pour obtenir la liste des monstres
    }

    public Monster getBoss() {
        return boss; // Méthode pour obtenir le boss
    }

    public void setBoss(Monster boss) {
        this.boss = boss; // Méthode pour définir le boss
    }

    public int getRequiredLevel() {
        return requiredLevel; // Méthode pour obtenir le niveau requis
    }

    // Méthode pour vérifier si le joueur peut entrer
    public boolean canEnter(Player player) {
        return player.getLevel() >= requiredLevel; // Vérifie si le niveau du joueur est suffisant
    }

    // Méthode pour afficher les monstres dans la pièce
    public void displayMonsters() {
        if (monsters.isEmpty()) {
            System.out.println("Aucun monstre présent dans la salle.");
        } else {
            System.out.println("Monstres présents dans la salle :");
            for (Monster monster : monsters) {
                monster.displayStatus(); // Affiche le statut de chaque monstre
            }
        }
    }

    // Méthode abstraite pour le dessin ASCII
    public abstract String asciiArt(); // Oblige les classes dérivées à fournir leur propre dessin ASCII
}
