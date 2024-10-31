package MonsterOriginal;

import MonsterOriginal.Monster;

public class StrongMonster extends Monster {
    public StrongMonster() {
        super("Monstre Fort", "Un monstre redoutable et puissant.", 80, 15, 10, 3, 20, 12); // Ajout du paramètre "défense"
    }

    @Override
    public String asciiArt() {
        return "   🔥   \n" +
                "  /|\\  \n" +
                "  / \\  \n";
    }

    @Override
    protected int specialAttack() {
        System.out.println("Le Monstre Fort lance une attaque écrasante !");
        return getBaseDamage() * 3; // Attaque spéciale : inflige des dégâts triplés
    }

    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);
        if (!isAlive()) {
            System.out.println("Le Monstre Fort s'effondre, vaincu !");
        } else {
            System.out.println("Le Monstre Fort rugit de colère !");
        }
    }

    @Override
    public void displayStatus() {
        super.displayStatus();
        System.out.println("Dégâts d'attaque : " + getBaseDamage());
        System.out.println("Points d'expérience à gagner : " + getExperiencePoints());
    }
}
