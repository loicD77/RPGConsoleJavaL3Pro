public class Zombie extends Monster {
    // Variable pour stocker si le Zombie est en mode "Résurrection"
    private boolean hasResurrected;

    public Zombie(int playerLevel) {
        super("Zombie", "Mort-vivant", 30, 5, playerLevel, 10, 15, 12); // Ajoutez une valeur pour experiencePoints
        this.hasResurrected = false; // Le Zombie n'a pas encore utilisé sa résurrection
    }

    @Override
    public String asciiArt() {
        return "  Z  \n" +
                " /|\\ \n" +
                " / \\ \n";
    }

    // Attaque spéciale du Zombie : une morsure
    @Override
    protected int specialAttack() {
        System.out.println("Le Zombie mord férocement avec ses dents pourries !");
        return getBaseDamage() * 2; // Attaque spéciale : morsure qui inflige des dégâts doublés
    }

    // Le Zombie peut se "ressusciter" une fois après avoir été tué
    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);

        if (!isAlive() && !hasResurrected) {
            resurrect();
            hasResurrected = true; // Le Zombie ne peut se ressusciter qu'une seule fois
        }

        if (!isAlive()) {
            System.out.println("Le Zombie s'effondre, enfin vaincu !");
        } else {
            System.out.println("Le Zombie gémit et se traîne encore... toujours en vie !");
        }
    }

    // Méthode de résurrection pour le Zombie
    private void resurrect() {
        int resurrectHealth = getBaseHealth() / 2; // Le Zombie revient à la vie avec 50% de sa santé de base
        health = resurrectHealth;
        System.out.println("Le Zombie se relève de ses cendres ! Il regagne " + resurrectHealth + " points de vie !");
    }

    @Override
    public void displayStatus() {
        super.displayStatus();
        System.out.println("Résurrection possible : " + (!hasResurrected ? "Oui" : "Non"));
    }
}
