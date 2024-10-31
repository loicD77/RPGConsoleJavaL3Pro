public class BankBoss extends Monster {
    public BankBoss() {
        super("Boss de la Banque", "Monstre puissant", 100, 15, 0, 20, 30, 25);
    }

    @Override
    public String asciiArt() {
        return "   [B]\n" +
                "  /|_|\n" +
                "  /___\\\n";
    }

    @Override
    protected int specialAttack() {
        System.out.println("Le Boss de la Banque attaque avec son pouvoir magique !");
        return getBaseDamage() * 5;
    }
}
