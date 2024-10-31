package WeaponOriginal;

public class SecretStore {
    public void discoverStore(boolean hasSpecialKey) {
        if (hasSpecialKey) {
            System.out.println("Vous avez trouvé un magasin secret caché !");
            // Afficher les armes spéciales
        } else {
            System.out.println("Il semble qu'il y a quelque chose ici, mais vous n'avez pas la clé spéciale.");
        }
    }
}
