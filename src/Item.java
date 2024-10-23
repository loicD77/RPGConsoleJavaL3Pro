public class Item {
    private String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void use(Player player) {
        // Logique pour utiliser l'objet
        System.out.println(player.getName() + " utilise " + name);
    }
}
