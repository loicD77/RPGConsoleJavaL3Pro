public abstract class Monster {
    protected String name;
    protected int health;
    protected int baseHealth;
    protected int baseDamage;
    protected int damage;
    protected int level;

    public Monster(String name, int baseHealth, int baseDamage, int playerLevel) {
        this.name = name;
        this.baseHealth = baseHealth;
        this.level = playerLevel;
        this.health = baseHealth + (level * 10);
        this.damage = baseDamage + (level);
    }

    public abstract String asciiArt();

    public int attack() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) health = 0;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int getBaseHealth() {
        return baseHealth;
    }

    public int getBaseDamage() {
        return baseDamage;
    }
}
