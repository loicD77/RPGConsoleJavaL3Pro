package Interface;

public interface Attackable {
    boolean isAlive();
    void takeDamage(int damage);
    String getName(); // Ajout de la méthode getName pour récupérer le nom de la cible
}
