public class BossRoom extends DungeonPiece {
    public BossRoom() {
        super("Salle du Boss", "Une pièce sombre où se cache le boss final.", 10); // Exemple avec un niveau requis de 10
    }

    @Override
    public void enter(Player player) {
        System.out.println("Vous êtes entré dans la salle du boss !");
        Boss monsterBoss = new Boss(player.getLevel()); // Création d'un boss avec le niveau du joueur

        // Logique de combat
        while (monsterBoss.isAlive() && player.isAlive()) {
            int damageDealt = player.attack();
            monsterBoss.takeDamage(damageDealt);
            System.out.println("Vous infligez " + damageDealt + " points de dégâts au " + monsterBoss.getName() + ".");

            if (!monsterBoss.isAlive()) {
                System.out.println("Vous avez vaincu le boss " + monsterBoss.getName() + " !");
                player.gainExperience(monsterBoss.getExperiencePoints());
                return;
            }

            int bossDamage = monsterBoss.attack();
            player.takeDamage(bossDamage);
            System.out.println("Le " + monsterBoss.getName() + " vous inflige " + bossDamage + " points de dégâts.");
        }

        if (!player.isAlive()) {
            System.out.println("Vous êtes tombé au combat !");
        }
    }

    @Override
    public String asciiArt() {
        return "   B   \n" +
                "  /|\\  \n" +
                "  / \\  \n";
    }
}
