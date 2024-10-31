package PotionGroup;

public class HypnosisCurePotion extends Potion {
    public HypnosisCurePotion(String name, int healingAmount, int price) {
        // Appelle le constructeur de la super classe avec le nom, une description, le montant de soin et le prix
        super(name, "Restaure " + healingAmount + " PV et guérit l'hypnose", healingAmount, price);
    }
}
