import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int countFail = 0;

    public static void main(String[] args) {
        String word = randomWords();
        String result = word.replaceAll(".", "*");
        System.out.println("Guess the word: " + result);

        while (checkForUnknownLetters(result)) {
            System.out.println("You have five tries" + "\n\nEnter a character:");
            if (countFail == 5) {
                System.out.println("Game over! You've used all attempts.");
                System.exit(1);
            }

            String consoleChar = readConsoleLetter();
            String newResult = checkedWordInRound(word, result, consoleChar);

            if (result.equals(newResult)) {
                countFail++;
                System.out.println("Incorrect guess. You have " + (5 - countFail) + " attempts left.");
            } else {
                System.out.println("Good guess! Keep going.");
            }

            result = newResult;

            System.out.println(result);
        }
        System.out.println("Congratulations! You've guessed the word: " + word);
    }


    public static String readConsoleLetter() {
        Scanner scanner = new Scanner(System.in);
        String consoleLetter = scanner.nextLine();
        while (consoleLetter.length() != 1) {
            System.out.println("You typed a word or more than 1 letter. Please enter a single letter:");
            consoleLetter = scanner.nextLine();
        }
        return consoleLetter;
    }

    public static String randomWords() {
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList("Hello", "Alexandr", "David", "Marian"));
        return arrayList.get((int) (Math.random() * arrayList.size()));
    }


    public static boolean checkForUnknownLetters(String result) {
        return result.contains("*");
    }

    public static String checkedWordInRound(String word, String currentResult, String consoleChar) {

        char charToCheck = consoleChar.charAt(0);

        StringBuilder stringBuilder = new StringBuilder(currentResult);

        if (word.contains(consoleChar)) {
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == charToCheck) {
                    stringBuilder.setCharAt(i, charToCheck);
                }
            }
        }
        return stringBuilder.toString();
    }

}