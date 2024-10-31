public class Zombie extends Monster {
    private boolean hasResurrected;

    public Zombie(int playerLevel) {
        super("Zombie", "Mort-vivant", 30, 5, playerLevel, 10, 15, 12);
        this.hasResurrected = false;
    }

    @Override
    public String asciiArt() {
        return "  Z  \n" +
                " /|\\ \n" +
                " / \\ \n";
    }

    @Override
    protected int specialAttack() {
        System.out.println("Le Zombie mord férocement avec ses dents pourries !");
        return getBaseDamage() * 2;
    }

    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);
        if (!isAlive() && !hasResurrected) {
            resurrect();
            hasResurrected = true;
        }
        if (!isAlive()) {
            System.out.println("Le Zombie s'effondre, enfin vaincu !");
        } else {
            System.out.println("Le Zombie gémit et se traîne encore... toujours en vie !");
        }
    }

    private void resurrect() {
        int resurrectHealth = getBaseHealth() / 2;
        health = resurrectHealth;
        System.out.println("Le Zombie se relève de ses cendres ! Il regagne " + resurrectHealth + " points de vie !");
    }

    @Override
    public void attack(Player player) {
        super.attack(player);
        if (Math.random() < 0.5) {
            System.out.println("Le zombie a empoisonné le joueur !");
        }
    }

    @Override
    public void displayStatus() {
        super.displayStatus();
        System.out.println("Résurrection possible : " + (!hasResurrected ? "Oui" : "Non"));
    }
}
