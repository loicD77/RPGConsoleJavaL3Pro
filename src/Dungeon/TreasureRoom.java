package Dungeon;

import java.util.Random;
import java.util.Scanner;
import Player.Player; // Import de la classe Player
import Item.Item; // Import de la classe Item
import PotionGroup.Potion; // Import de la classe Potion
import WeaponGroup.LegendarySword; // Import de la classe LegendarySword
import Obstacle.WoodenShield; // Import de la classe WoodenShield
import ProtectiveClothing.Helmet; // Import de la classe Helmet

public class TreasureRoom extends DungeonPiece {

    private static final String[][] enigmas = {
            {"Quel est le résultat de 2 + 2 ?", "4"},
            {"Quelle est la sortie de System.out.println(\"Hello World\"); ?", "Hello World"},
            {"Quelle est la valeur de x dans l'expression x = 5 + 3 ?", "8"},
            {"Quelle est la méthode pour ajouter un élément à une ArrayList en Java ?", "add"}
    };

    private static final String[] rewards = {
            "Potion de soin",
            "Épée légendaire",
            "Bouclier en bois",
            "Casque de protection"
    };

    // Constructeur avec des arguments
    public TreasureRoom(String name, String description, int level) {
        super(name, description, level);
    }

    // Constructeur par défaut
    public TreasureRoom() {
        this("Salle au Trésor", "Une pièce remplie de trésors étincelants.", 1);
    }

    @Override
    public void enter(Player player) {
        System.out.println("Vous êtes entré dans la salle au trésor !");
        System.out.println("Vous voyez des coffres remplis d'or scintillant et de bijoux étincelants.");

        if (askEnigma()) {
            String rewardName = getRandomReward();
            Item reward = createRewardItem(rewardName);
            if (reward != null) {
                player.addItemToInventory(reward);
                System.out.println("Bravo ! Vous avez résolu l'énigme et gagné : " + rewardName);
            } else {
                System.out.println("La récompense n'est pas valide.");
            }
            player.showInventory();
        } else {
            System.out.println("Dommage, la réponse est incorrecte. Vous ne pouvez pas récupérer de trésor.");
        }
    }

    private boolean askEnigma() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int index = random.nextInt(enigmas.length);
        String question = enigmas[index][0];
        String answer = enigmas[index][1];

        System.out.println("Énigme : " + question);
        System.out.print("Votre réponse : ");
        String playerAnswer = scanner.nextLine();

        return playerAnswer.equalsIgnoreCase(answer);
    }

    private String getRandomReward() {
        Random random = new Random();
        int index = random.nextInt(rewards.length);
        return rewards[index];
    }

    private Item createRewardItem(String rewardName) {
        switch (rewardName) {
            case "Potion de soin":
                return new Potion("Potion de soin", "Restaure des points de vie.", 20, 10);
            case "Épée légendaire":
                return new LegendarySword();
            case "Bouclier en bois":
                return new WoodenShield();
            case "Casque de protection":
                return new Helmet("Casque de protection", "Augmente la défense", 5, 30);
            default:
                return null;
        }
    }

    @Override
    public String asciiArt(Player player) {
        return "     |-------------------|\n"
                + "     |                   |\n"
                + "     |   SALLE AU TRÉSOR |\n"
                + "     |                   |\n"
                + "     |       _.-=-._     |\n"
                + "     |     .'       '.   |\n"
                + "     |    /           \\  |\n"
                + "     |   |  *  *  *   |  |\n"
                + "     |    \\         /   |\n"
                + "     |     '._._._.'    |\n"
                + "     |                   |\n"
                + "     |___________________|\n"
                + "Personnage : " + player.getAsciiFace() + "\n";
    }
}
