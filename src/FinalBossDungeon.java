public class FinalBossDungeon extends DungeonPiece {
    public FinalBossDungeon() {
        super("Salle du Boss Final", "Un endroit sombre et terrifiant où le boss ultime attend.", 5);
        // Ajoutez des monstres à la salle
        addMonster(new StrongMonster()); // Exemple de monstre puissant
        // Définissez le boss final
        setBoss(new FinalBoss());
    }

    @Override
    public String asciiArt() {
        return "=== Salle du Boss Final ===\n" +
                "    |  \n" +
                "   /|\\ \n" +
                "  / | \\ \n" +
                " /  |  \\ \n" +
                "--------------------------\n";
    }

    @Override
    public void enter(Player player) {
        System.out.println("Vous entrez dans la " + getName() + " !");
        // Vérifiez si le joueur a le niveau requis
        if (player.getLevel() < getRequiredLevel()) {
            System.out.println("Vous n'avez pas le niveau requis pour entrer !");
            return; // Le joueur ne peut pas entrer
        }
        // Si le joueur peut entrer, affichez les monstres et le boss
        System.out.println("Préparez-vous à affronter les monstres !");
        // Afficher les monstres présents
        displayMonsters(); // Affiche les monstres de la salle
        // Afficher le boss
        System.out.println("Le boss final vous attend !");
        getBoss().displayStatus();
    }
}
