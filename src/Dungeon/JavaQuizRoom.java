package Dungeon;


import Dungeon.DungeonPiece; // Import pour DungeonPiece
import Player.Player; // Import pour Player
import java.util.Random; // Import pour Random
import java.util.Scanner; // Import pour Scanner



public class JavaQuizRoom extends DungeonPiece {
    private String[] questions;
    private String[] answers;
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
                "Qu'est-ce qu'une exception?",
                "Qu'est-ce qu'une collection en Java?",
                "Comment créer une liste en Java?",
                "Qu'est-ce que le mot-clé static?",
                "Qu'est-ce que le mot-clé final?",
                "Qu'est-ce que la JVM?",
                "Qu'est-ce qu'une variable locale?",
                "Qu'est-ce qu'une variable d'instance?",
                "Qu'est-ce que l'encapsulation?",
                "Quelle est la différence entre public et private?",
                "Qu'est-ce qu'un tableau en Java?"
        };
        answers = new String[] {
                "Une classe est un plan pour créer des objets.",
                "Un objet est une instance d'une classe.",
                "Un constructeur est une méthode spéciale pour initialiser un objet.",
                "L'héritage permet de créer une nouvelle classe à partir d'une classe existante.",
                "Une interface est un contrat que les classes peuvent implémenter.",
                "La polymorphie permet de traiter des objets de manière interchangeable.",
                "Type retour + nom méthode + ().",
                "La surcharge de méthode est la définition de plusieurs méthodes avec le même nom mais des paramètres différents.",
                "== compare les références, equals() compare le contenu.",
                "Une exception est un problème qui survient pendant l'exécution d'un programme.",
                "Une collection est un objet qui regroupe plusieurs éléments.",
                "List<String> maListe = new ArrayList<>();",
                "static signifie que l'élément appartient à la classe et non à une instance.",
                "final empêche la modification de la variable, méthode ou classe.",
                "La JVM (Java Virtual Machine) exécute le bytecode Java.",
                "Une variable locale est déclarée dans une méthode.",
                "Une variable d'instance est une variable qui appartient à une instance de la classe.",
                "L'encapsulation consiste à restreindre l'accès direct aux données de l'objet.",
                "public signifie accessible à tous, private signifie accessible uniquement dans la classe.",
                "Un tableau est une structure de données qui contient des éléments du même type."
        };
    }

    @Override
    public void enter(Player player) {
        System.out.println("Vous entrez dans la salle du quiz Java. Répondez correctement aux questions pour gagner des pièces d'or !");
        boolean answering = true;

        while (answering) {
            System.out.println("Entrez 'q' pour quitter la salle à tout moment.");
            int questionIndex = random.nextInt(questions.length);
            System.out.println(questions[questionIndex]);
            System.out.print("Votre réponse : ");
            String playerAnswer = scanner.nextLine();

            if (playerAnswer.equalsIgnoreCase("q")) {
                System.out.println("Vous avez quitté la salle du quiz.");
                answering = false;
            } else if (playerAnswer.equalsIgnoreCase(answers[questionIndex])) {
                player.addGold(5);
                System.out.println("Bonne réponse ! Vous gagnez 5 pièces d'or.");
                answering = false; // Fin si bonne réponse
            } else {
                System.out.println("Mauvaise réponse. Essayez une autre question.");
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
