package PotionGroup;

import Player.Player;

public class HypnosisCurePotion extends Potion {

    public HypnosisCurePotion(String name, int healingAmount, int price) {
        super(name, "Restaure " + healingAmount + " PV et guérit l'hypnose", healingAmount, price);
    }

    @Override
    public void use(Player player) {
        System.out.println("Vous utilisez " + getName() + ". " + getDescription());
        player.restoreHealth(getHealingAmount());
        player.cureStatusEffect("hypnose"); // Méthode pour guérir l'effet d'hypnose
    }
}
