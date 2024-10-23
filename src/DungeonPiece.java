public abstract class DungeonPiece {
    // Méthode abstraite pour obtenir l'art ASCII de la pièce
    public abstract String asciiArt();

    // Méthode pour obtenir une description de la pièce
    public String description() {
        return "Une pièce du donjon.";
    }
}
