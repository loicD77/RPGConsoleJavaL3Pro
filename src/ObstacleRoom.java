public class ObstacleRoom extends DungeonPiece {
    public ObstacleRoom() {
        super("Salle des Obstacles", "Une salle remplie d'obstacles difficiles à surmonter.", 3); // Niveau requis de 3
    }

    @Override
    public void enter(Player player) {
        System.out.println("Vous êtes entré dans une salle avec des obstacles !");
        // Ici, tu pourrais ajouter la logique pour surmonter des obstacles
        // Par exemple, le joueur doit lancer un jet d'agilité pour passer
        int successChance = player.getAgility();
        if (Math.random() * 100 < successChance) {
            System.out.println("Vous avez réussi à surmonter l'obstacle !");
        } else {
            System.out.println("Vous avez échoué à surmonter l'obstacle et subi des dégâts !");
            player.takeDamage(5); // Le joueur subit des dégâts
        }
    }

    @Override
    public String asciiArt() {
        return "=== Salle des Obstacles ===\n" +
                "   _______\n" +
                "  |       |\n" +
                "  |  ***  |\n" +
                "  |_______|\n";
    }
}
