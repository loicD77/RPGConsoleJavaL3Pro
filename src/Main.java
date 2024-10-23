import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Demander le nom du personnage
        System.out.print("Entrez le nom de votre personnage : ");
        String playerName = scanner.nextLine();
        Player player = new Player(playerName); // Créez le joueur avec le nom saisi
        MonsterGenerator monsterGenerator = new MonsterGenerator();

        System.out.println("Bienvenue dans le donjon !");
        while (true) {
            System.out.println("Que souhaitez-vous faire ?");
            System.out.println("1. Explorer le donjon");
            System.out.println("2. Aller au magasin d'armes");
            System.out.println("3. Quitter le jeu");
            System.out.print("Choisissez une action : ");
            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    // Explorer le donjon et rencontrer un monstre
                    Monster monster = monsterGenerator.generateMonster(player.getLevel());
                    System.out.println("Vous tombez sur un monstre : ");
                    System.out.println(monster.asciiArt());
                    System.out.println("Monstre : " + monster.getName() + " (PV : " + monster.getHealth() + ")");

                    // Afficher le personnage
                    System.out.println("Votre personnage : ");
                    System.out.println(player.asciiArt());
                    System.out.println("Santé : " + player.getHealth() + "/" + player.getMaxHealth());

                    // Boucle de combat
                    while (monster.isAlive() && player.isAlive()) {
                        System.out.println("Que souhaitez-vous faire ?");
                        System.out.println("1. Attaquer");
                        System.out.println("2. Défendre");
                        System.out.print("Choisissez une action : ");
                        int combatAction = scanner.nextInt();

                        switch (combatAction) {
                            case 1:
                                // Attaque
                                int damageDealt = player.attack();
                                monster.takeDamage(damageDealt);
                                System.out.println("Vous infligez " + damageDealt + " dégâts au " + monster.getName() + "!");
                                System.out.println(monster.getName() + " (PV restants : " + monster.getHealth() + ")");
                                break;
                            case 2:
                                // Défense (optionnel)
                                System.out.println("Vous vous défendez !");
                                break;
                            default:
                                System.out.println("Action invalide.");
                        }

                        // Si le monstre est toujours vivant, il attaque
                        if (monster.isAlive()) {
                            int damageTaken = monster.attack();
                            player.takeDamage(damageTaken);
                            System.out.println(monster.getName() + " vous inflige " + damageTaken + " dégâts !");
                            System.out.println("Votre santé : " + player.getHealth() + "/" + player.getMaxHealth());
                        }
                    }

                    // Vérifier si le joueur ou le monstre est vaincu
                    if (!player.isAlive()) {
                        System.out.println("Vous êtes vaincu... Game Over !");
                        return; // Quitter le jeu
                    } else {
                        System.out.println("Vous avez vaincu " + monster.getName() + " !");
                    }
                    break;

                case 2:
                    // Visiter le magasin d'armes
                    WeaponStore weaponStore = new WeaponStore();
                    weaponStore.visit(player);
                    break;

                case 3:
                    System.out.println("Merci d'avoir joué !");
                    return; // Quitter le jeu

                default:
                    System.out.println("Choix invalide.");
            }
        }
    }
}

