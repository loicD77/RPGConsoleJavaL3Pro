public class Item {
    private String name;
    private String description; // Ajouter un attribut pour la description

    public Item(String name, String description) {
        this.name = name;
        this.description = description; // Initialiser la description
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description; // Méthode pour obtenir la description
    }

    public void use(Player player) {
        // Logique pour utiliser l'objet
        System.out.println(player.getName() + " utilise " + name);
    }
}
