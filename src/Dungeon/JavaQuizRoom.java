package Dungeon;

import Player.Player; // Import pour Player
import java.util.Random; // Import pour Random
import java.util.Scanner; // Import pour Scanner

public class JavaQuizRoom extends DungeonPiece {
    private String[] questions;
    private String[][] options;
    private int[] correctAnswers;
    private Random random;
    private Scanner scanner;

    public JavaQuizRoom(String name, String description, int difficulty) {
        super(name, description, difficulty);
        random = new Random();
        scanner = new Scanner(System.in);
        initializeQuestions();
    }

    private void initializeQuestions() {
        questions = new String[] {
                "Qu'est-ce qu'une classe en Java?",
                "Qu'est-ce qu'un objet en Java?",
                "Qu'est-ce qu'un constructeur?",
                "Qu'est-ce que l'héritage en Java?",
                "Qu'est-ce qu'une interface?",
                "Qu'est-ce que la polymorphie?",
                "Comment déclarer une méthode en Java?",
                "Qu'est-ce que la surcharge de méthode?",
                "Quelle est la différence entre == et equals()?",
                "Qu'est-ce qu'une exception?"
        };

        options = new String[][] {
                { "Une classe est un plan pour créer des objets.", "Une classe est une variable.", "Une classe est un type de données.", "Une classe est une méthode." },
                { "Un objet est une instance d'une classe.", "Un objet est une méthode.", "Un objet est une variable locale.", "Un objet est un fichier." },
                { "Un constructeur est une méthode spéciale pour initialiser un objet.", "Un constructeur est une variable statique.", "Un constructeur est une interface.", "Un constructeur est une collection." },
                { "L'héritage permet de créer une nouvelle classe à partir d'une classe existante.", "L'héritage permet de supprimer une classe.", "L'héritage est utilisé pour créer des objets.", "L'héritage est une méthode pour trier des données." },
                { "Une interface est un contrat que les classes peuvent implémenter.", "Une interface est une méthode privée.", "Une interface est une classe abstraite.", "Une interface est une variable statique." },
                { "La polymorphie permet de traiter des objets de manière interchangeable.", "La polymorphie est utilisée pour trier des tableaux.", "La polymorphie est une méthode pour gérer les exceptions.", "La polymorphie est un type de collection." },
                { "Type retour + nom méthode + ().", "variable + nom méthode + ().", "class + nom méthode + ().", "static + nom méthode + ()." },
                { "La surcharge de méthode est la définition de plusieurs méthodes avec le même nom mais des paramètres différents.", "La surcharge de méthode est l'utilisation de variables globales.", "La surcharge de méthode est la suppression d'une méthode.", "La surcharge de méthode est la création d'une classe." },
                { "== compare les références, equals() compare le contenu.", "== compare les valeurs, equals() compare les types.", "== compare les types, equals() compare les références.", "== compare les objets, equals() compare les méthodes." },
                { "Une exception est un problème qui survient pendant l'exécution d'un programme.", "Une exception est une classe statique.", "Une exception est une méthode de tri.", "Une exception est un type de variable." }
        };

        correctAnswers = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
    }

    @Override
    public void enter(Player player) {
        System.out.println("Vous entrez dans la salle du quiz Java. Répondez correctement aux questions pour gagner des pièces d'or !");
        boolean answering = true;

        while (answering) {
            System.out.println("Entrez 'q' pour quitter la salle à tout moment.");
            int questionIndex = random.nextInt(questions.length);
            System.out.println(questions[questionIndex]);
            for (int i = 0; i < 4; i++) {
                System.out.println((i + 1) + ": " + options[questionIndex][i]);
            }
            System.out.print("Votre réponse (1-4) : ");
            String playerAnswer = scanner.nextLine();

            if (playerAnswer.equalsIgnoreCase("q")) {
                System.out.println("Vous avez quitté la salle du quiz.");
                answering = false;
            } else {
                try {
                    int answerIndex = Integer.parseInt(playerAnswer);
                    if (answerIndex == correctAnswers[questionIndex]) {
                        player.addGold(5);
                        System.out.println("Bonne réponse ! Vous gagnez 5 pièces d'or.");
                        // Bonne réponse, continuer
                        System.out.println("Vous pouvez continuer à répondre ou quitter la salle.");
                    } else {
                        System.out.println("Mauvaise réponse. Essayez une autre question.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrée invalide. Veuillez entrer un nombre entre 1 et 4, ou 'q' pour quitter.");
                }
            }
        }
    }

    @Override
    public String asciiArt(Player player) {
        return "     _______________________ \n"
                + "    |                     |\n"
                + "    |   SALLE DE QUIZ     |\n"
                + "    |       JAVA          |\n"
                + "    |                     |\n"
                + "    |  \\  ~ JAVA ~  /     |\n"
                + "    |   \\  QUIZ   /      |\n"
                + "    |    '-------'       |\n"
                + "    |                     |\n"
                + "    |  Personnage :       |\n"
                + "    |     " + player.getAsciiFace() + "           |\n"
                + "    |     /|\\           |\n"
                + "    |     / \\           |\n"
                + "    |___________________|\n";
    }
}
