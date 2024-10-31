package WeaponOriginal;

import MonsterOriginal.Monster;

import java.util.Scanner;

public class Bank {
    private int playerMoney;

    public Bank(int initialMoney) {
        this.playerMoney = initialMoney;
    }

    public void workForMoney() {
        Scanner scanner = new Scanner(System.in);
        int questionDifficulty = (int) (Math.random() * 3) + 1; // 1: facile, 2: moyen, 3: difficile
        String question = "";
        int reward = 0;

        switch (questionDifficulty) {
            case 1:
                question = "Quel est 2 + 2 ?"; // Facile
                reward = 1;
                break;
            case 2:
                question = "Quelle est la capitale de la France ?"; // Moyen
                reward = 3;
                break;
            case 3:
                question = "Quel est le nombre premier le plus bas ?"; // Difficile
                reward = 5;
                break;
        }

        System.out.println("Répondez à la question pour gagner " + reward + " pièce(s) d'or : " + question);
        String answer = scanner.nextLine();

        if (isCorrectAnswer(questionDifficulty, answer)) {
            playerMoney += reward;
            System.out.println("Bonne réponse ! Vous gagnez " + reward + " pièce(s) d'or.");
        } else {
            System.out.println("Mauvaise réponse ! Pas de pièces d'or pour vous.");
        }
    }

    private boolean isCorrectAnswer(int difficulty, String answer) {
        switch (difficulty) {
            case 1:
                return answer.equals("4");
            case 2:
                return answer.equalsIgnoreCase("Paris");
            case 3:
                return answer.equals("2");
            default:
                return false;
        }
    }

    public void challengeBankBoss(Player player) {
        Monster bankBoss = new BankBoss(); // Créez une classe BankBoss
        System.out.println("Vous affrontez le boss de la banque !");
        // Ajoutez ici la logique de combat
    }
}
