package MonsterOriginal;
import MainFiles.GameEntity; // Assurez-vous que l'emplacement est correct
import Interface.Attackable; // Assurez-vous que l'interface Attackable est bien importée

public class Obstacle extends GameEntity implements Attackable {
    private int health;

    public Obstacle(String name, int health) {
        super(name);
        this.health = health;
    }


    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public boolean isDestroyed() {
        return health <= 0;
    }

    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
    }
}
