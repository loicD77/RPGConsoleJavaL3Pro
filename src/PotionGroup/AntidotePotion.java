package PotionGroup; // Déclarez que cette classe appartient au package PotionGroup

import PotionGroup.Potion; // Remplacez "autrepackage" par le package réel où la classe Potion est située

public class AntidotePotion extends Potion {
    public AntidotePotion(String name, String description, int healingAmount, int price) {
        super(name, description, healingAmount, price); // Appelle le constructeur de la classe parent avec tous les paramètres
        // D'autres initialisations si nécessaires
    }
}
