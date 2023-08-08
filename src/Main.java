import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// Main method
public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        // greetings
        System.out.println("Hey! Let's play a Hangman game!");
        System.out.println("I have a word. Can you guess it?");

        // scanning the file and choosing a random word.
        Scanner fileScanner = new Scanner(new File("/Users/adiletsatylganov/Documents/programming/java_learning/zhukovs/hangman/src/resources/oxford-5000.txt"));
        // player guess scanner
        Scanner keyboard = new Scanner(System.in);

        // create an array list "words"
        List<String> words = new ArrayList<>();
        // put divided words in an ArrayList, named words (реализация интерфейса List).
        while (fileScanner.hasNext()) {
            words.add(fileScanner.nextLine());
        }

        // getting a random word from words(ArrayList)
        Random random = new Random();
        String word = words.get(random.nextInt(words.size()));

        // System.out.println(word);

        List<Character> playerGuesses = new ArrayList<>();

        Integer wrongCount = 0;


        while(true) {
            printHangman(wrongCount);

            if (wrongCount >= 6) {
                System.out.println("You lost!");
                System.out.println("The word was " + word);
                break;
            }

            // print out the word with hyphens
            printWordState(word, playerGuesses);
            if (!getPlayerGuess(keyboard, word, playerGuesses)) {
                wrongCount++;
            }

            if  (printWordState(word, playerGuesses)) {
                break;
            }

            System.out.println("Guess the word: ");
            if (keyboard.nextLine().equals(word)) {
                System.out.println("You win!");
                break;
            } else {
                System.out.println("please try again");
            }
        }
    }

    private static void printHangman(Integer wrongCount) {
        System.out.println("");
        System.out.println("Hangpost.");
        System.out.println(" ----------");
        System.out.println("          |");

        if (wrongCount >= 1) {
            System.out.println("          O");
        }

        if (wrongCount >= 2) {
            System.out.print("         \\ ");
            if (wrongCount >= 3) {
                System.out.println("/");
            } else {
                System.out.println("");
            }
        }

        if (wrongCount >= 4) {
            System.out.println("          |");
        }

        if (wrongCount >= 5) {
            System.out.print("         / ");
        }

        if (wrongCount >= 6) {
            System.out.println("\\");
        } else {
            System.out.println("");
        }
        System.out.println("");
        System.out.println("");
    }

    private static boolean getPlayerGuess(Scanner keyboard, String word, List<Character> playerGuesses) {
        //player guessing loop
        System.out.println("Enter a letter: ");
        String letterGuess = keyboard.nextLine();
        playerGuesses.add(letterGuess.charAt(0));

        return word.contains(letterGuess);
    }

    private static boolean printWordState(String word, List<Character> playerGuesses) {
        System.out.println("Word:");
        int correctCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if (playerGuesses.contains(word.charAt(i))) {
                System.out.print(word.charAt(i));
                correctCount++;
            }
            else {
                System.out.print("-");
            }
        }
        // line-break
        System.out.println();

        return (word.length() == correctCount);
    }
}

