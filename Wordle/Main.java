import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> answers = createWordleAnswers();
         ArrayList<String> alphabet = new ArrayList<String>(Arrays.asList("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"));
        ArrayList<String> allWords = createWords();

        Scanner s = new Scanner(System.in);

        String[] words = {"     ", "     ", "     ", "     ", "     ", "     "};
        String[] colors = {"     ", "     ", "     ", "     ", "     ", "     "};
        String guess = "";
        String answer = answers.get((int)(Math.random() * answers.size()));
        int guesses = 6;

        System.out.println("--------------------------");
        printRestBoard(guesses);
        System.out.println(alphabet);

        while (guesses > 0) {
            System.out.print("\nPick a word: ");
            guess = s.nextLine().toLowerCase();

            if (!allWords.contains(guess)) {
                System.out.println("Invalid answer");
            }

            else {
                colors[6-guesses] = checkWord(guess, answer);
                words[6-guesses] = guess;
                alphabet = removeLetters(alphabet, guess, answer);
                guesses--;
                printBoard(colors,words,guesses);
                printRestBoard(guesses);
                System.out.println(alphabet);
            }

            if (guess.equals(answer)) {
                guesses=0;
            }
            
        }
        s.close();
        System.out.println((guess.equals(answer)) ? ("You won!!") : ("You lost. The word was " + answer + "."));

    }

    public static ArrayList<String> createWordleAnswers() {
        ArrayList<String> wordleAnswers = new ArrayList<String>();
        try {
            Scanner s = new Scanner(new File("src/main/java/answers.txt"));
            while (s.hasNext()) {
                String answer = s.nextLine().trim().toLowerCase();
                wordleAnswers.add(answer);
            }
            s.close();
        } catch (Exception e) {
            System.out.println("file not found 1");
        }
        return wordleAnswers;
    }

    public static ArrayList<String> createWords() {
        ArrayList<String> words = new ArrayList<String>();
        try {
            Scanner s = new Scanner(new File("src/main/java/words.txt"));
            while (s.hasNext()) {
                String word = s.nextLine().trim().toLowerCase();
                words.add(word);
            }
            s.close();
        } catch (Exception e) {
            System.out.println("file not found 2");
        }
        return words;
    }

    public static void printBoard(String[] arr1, String[] arr2, int num) {
        System.out.println("--------------------------");
        for (int i=0; i<6-num; i++) {
            System.out.print("|");
            for (int j=0; j<5; j++) {
                String colors = arr1[i];
                int emojiIndex = j; 
                int codePoint = colors.codePoints().toArray()[emojiIndex];
                String singleEmoji = new String(Character.toChars(codePoint));
                System.out.print(singleEmoji + " " + arr2[i].substring(j,j+1) + "|"); 
            }
            System.out.println("\n--------------------------");
        }
    }

    public static void printRestBoard(int num) {
        for (int i = 0; i < num; i++) {
            System.out.println("|    |    |    |    |    |");
            System.out.println("--------------------------");
        }
    }

    public static String checkWord(String word, String ans) {
        String[] arr = new String[5];
        int[] used = new int[5];

        for (int i = 0; i < 5; i++) {
            if (word.charAt(i) == ans.charAt(i)) {
                arr[i] = "🟩";
                used[i] = 1;
            } else arr[i] = "⬛";
        }

        for (int i = 0; i < 5; i++) {
            if (arr[i].equals("⬛")) {
                for (int j = 0; j < 5; j++) {
                    if (word.charAt(i) == ans.charAt(j) && used[j] == 0) {
                        arr[i] = "🟨";
                        used[j] = 1;
                        break;
                    }
                }
            }
        }

        String result = "";
        for (String square : arr) result += square;
        return result;
    }

    public static ArrayList<String> removeLetters(ArrayList<String> arr, String word, String answer) {
        for (int i = 0; i < word.length(); i++) {
            String letter = word.substring(i, i + 1);
            if (!answer.contains(letter)) {
                arr.remove(letter);
            }
        }
        return arr;
    }
}
