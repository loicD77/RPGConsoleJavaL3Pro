package PotionGroup;
import Player.Player;

public class HealthPotion extends Potion {
    private int healingAmount;

    public HealthPotion(String name, int healingAmount, int price) {
        super(name, "Restaure " + healingAmount + " PV", healingAmount, price); // Ajoute le prix ici
        this.healingAmount = healingAmount;
    }

    public int getHealingAmount() {
        return healingAmount;
    }

    @Override
    public void use(Player player) {
        System.out.println("Vous utilisez la potion de santé : " + getName());
        player.restoreHealth(healingAmount);
    }

    @Override
    public String asciiArt() {
        return
                "   ,--.\n" +
                        "  /    \\\n" +
                        " |      |\n" +
                        "  \\    /\n" +
                        "   `--'\n"; // ASCII art pour la potion de santé
    }
}
