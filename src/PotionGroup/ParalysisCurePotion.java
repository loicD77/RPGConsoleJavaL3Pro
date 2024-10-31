package PotionGroup;
import PotionGroup.Potion;


public class ParalysisCurePotion extends Potion {
    public ParalysisCurePotion(String name, int healingAmount, int price) {
        // Appelle le constructeur de la super classe avec le nom, une description, le montant de soin et le prix
        super(name, "Restaure " + healingAmount + " PV et guérit la paralysie", healingAmount, price);
        // Initialisations spécifiques à ParalysisCurePotion si nécessaire
    }
}
