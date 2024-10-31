package Item;

import WeaponOriginal.Weapon;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private ArrayList<Item> items;
    private final int MAX_ITEMS = 64; // Capacité maximale de l'inventaire

    // Constructeur de l'inventaire
    public Inventory() {
        this.items = new ArrayList<>();
    }

    public List<Item> getItems() {
        return items; // Renvoie la liste des objets
    }


    // Méthode pour obtenir un objet par son nom
    public Item getItem(String name) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item; // Renvoie l'objet si trouvé
            }
        }
        return null; // Renvoie null si l'objet n'est pas trouvé
    }

    // Méthode pour ajouter un objet à l'inventaire
    public boolean addItem(Item item) {
        if (items.size() < MAX_ITEMS) {
            items.add(item);
            return true; // Ajout réussi
        }
        System.out.println("L'inventaire est plein !");
        return false; // Échec de l'ajout
    }

    public boolean isEmpty() {
        return items.isEmpty(); // Vérifie si l'inventaire est vide
    }

    // Méthode pour afficher l'inventaire
    public void displayInventory() {
        if (items.isEmpty()) {
            System.out.println("Votre inventaire est vide.");
        } else {
            System.out.println("Inventaire :");
            for (int i = 0; i < items.size(); i++) {
                System.out.println((i + 1) + ". " + items.get(i).getDescription());
            }
        }
    }

    // Méthode pour utiliser un objet de l'inventaire avec un paramètre Player
    public void useItem(int index, Player player) {
        if (index >= 0 && index < items.size()) {
            Item item = items.get(index);
            if (item instanceof Weapon) {
                System.out.println("Vous utilisez l'arme : " + item.getName());
                // Logique pour équiper l'arme (à implémenter dans la classe Player)
            } else if (item instanceof Potion) {
                System.out.println("Vous utilisez la potion : " + item.getName());
                // Appelle use() sur la potion avec le joueur comme argument
                ((Potion) item).use(player); // Envoie l'objet Player à la méthode use
            }
            items.remove(index); // Enlève l'objet utilisé de l'inventaire
        } else {
            System.out.println("Objet non valide !");
        }
    }

    // Méthode pour vendre un objet de l'inventaire
    public void sellItem(int index, Player player) {
        if (index >= 0 && index < items.size()) {
            Item item = items.remove(index);
            System.out.println("Vous avez vendu : " + item.getName());
            player.addMoney(item.getPrice()); // Exemple d'ajout d'argent (à adapter selon ta classe Player)
        } else {
            System.out.println("Objet non valide !");
        }
    }

    // Méthode pour jeter un objet de l'inventaire
    public void dropItem(int index) {
        if (index >= 0 && index < items.size()) {
            Item item = items.remove(index);
            System.out.println("Vous avez jeté : " + item.getName());
        } else {
            System.out.println("Objet non valide !");
        }
    }

    public Item findItemByName(String itemName) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    public void equipItem(Player player, String itemName) {
        Item item = findItemByName(itemName);
        if (item instanceof ProtectionItem) {
            player.equipProtectionItem((ProtectionItem) item);
        } else {
            System.out.println("Cet objet ne peut pas être équipé.");
        }
    }

    public boolean contains(Armor armor) {
        for (Item item : items) { // Supposons que 'items' soit la liste d'objets de l'inventaire
            if (item instanceof Armor && item.equals(armor)) {
                return true;
            }
        }
        return false;
    }


    // Méthode pour vérifier si l'inventaire est plein
    public boolean isFull() {
        return items.size() >= MAX_ITEMS; // Renvoie true si l'inventaire est plein
    }

    // Méthode pour obtenir le nombre d'objets dans l'inventaire
    public int getItemCount() {
        return items.size(); // Renvoie le nombre d'objets dans l'inventaire
    }

    public int  maxInventorySize(){
        return MAX_ITEMS;
    }

    public int size() {
        return items.size();
    }
    public void add(Item item) {
        items.add(item);
    }

}
