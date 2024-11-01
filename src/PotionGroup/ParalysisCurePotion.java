package PotionGroup;

import Player.Player;

public class ParalysisCurePotion extends Potion {

    public ParalysisCurePotion(String name, int healingAmount, int price) {
        super(name, "Restaure " + healingAmount + " PV et guérit la paralysie", healingAmount, price);
    }

    @Override
    public void use(Player player) {
        System.out.println("Vous utilisez " + getName() + ". " + getDescription());
        player.restoreHealth(getHealingAmount());
        player.cureStatusEffect("paralysie"); // Méthode pour guérir la paralysie
    }
}
