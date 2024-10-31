package WeaponOriginal;

import MonsterOriginal.Monster;
import MonsterOriginal.MonsterGenerator;

public class StrongMonsterRoom extends DungeonPiece {
    public StrongMonsterRoom() {
        super("Salle des Monstres Forts", "Une pièce avec des monstres puissants.", 5); // Exemple avec un niveau requis de 5
    }

    @Override
    public void enter(Player player) {
        System.out.println("Vous êtes entré dans une salle avec des monstres puissants !");
        MonsterGenerator generator = new MonsterGenerator();
        Monster strongMonster = generator.generateMonster(player.getLevel()); // Génère un monstre basé sur le niveau du joueur

        // Logique de combat avec le monstre
        while (strongMonster.isAlive() && player.isAlive()) {
            // Calcul des dégâts du joueur
            int damageDealt = player.calculateDamage(1); // Ajoute un argument ici
            strongMonster.takeDamage(damageDealt);
            System.out.println("Vous infligez " + damageDealt + " points de dégâts à " + strongMonster.getName());

            // Vérifie si le monstre est mort
            if (!strongMonster.isAlive()) {
                System.out.println("Vous avez vaincu " + strongMonster.getName() + " !");
                player.gainExperience(strongMonster.getExperiencePoints());
                return;
            }

            // Le monstre attaque
            int monsterDamage = strongMonster.attack();
            player.takeDamage(monsterDamage);
            System.out.println(strongMonster.getName() + " vous attaque et inflige " + monsterDamage + " points de dégâts !");
        }

        if (!player.isAlive()) {
            System.out.println("Vous êtes tombé au combat !");
        }
    }

    @Override
    public String asciiArt(Player player) {
        return "   M   \n" +
                "  /|\\  \n" +
                "  / \\\\  \n" +
                "Personnage : " + player.getAsciiFace() + "\n";
    }
}
