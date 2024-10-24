public class Boss extends Monster {
    private int experiencePoints; // Points d'expérience à gagner en tuant ce boss

    public Boss(int playerLevel) {
        // Ajoutez un paramètre pour l'or, par exemple 50
        super("Boss", "Le boss final",
                150 + (playerLevel * 20), // maxHealth
                20 + playerLevel, // damage
                playerLevel, // level
                15, // specialAttackChance
                100 + (playerLevel * 10), // experiencePoints
                50); // Or donné par le Boss
        this.experiencePoints = 100 + (playerLevel * 10); // Points d'expérience à gagner
    }

    @Override
    public String asciiArt() {
        return "   B   \n" +
                "  /|\\  \n" +
                "  / \\  \n";
    }

    @Override
    protected int specialAttack() {
        System.out.println("Le Boss lance une attaque dévastatrice !");
        return getBaseDamage() * 3; // Exemple d'attaque spéciale
    }
}
