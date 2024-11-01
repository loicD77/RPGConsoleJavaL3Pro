package PotionGroup;

import Player.Player; // Importez la classe Player pour pouvoir l'utiliser dans cette classe
import PotionGroup.Potion;

public class AntidotePotion extends Potion {
    public AntidotePotion(String name, String description, int healingAmount, int price) {
        super(name, description, healingAmount, price); // Appelle le constructeur de la classe parente avec tous les paramètres
    }

    @Override
    public void use(Player player) {
        System.out.println("Vous utilisez l'antidote : " + getName());
        player.restoreHealth(getHealingAmount());
        player.cureStatusEffect("poison"); // Guérit l'effet de poison
    }
}
