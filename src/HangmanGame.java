import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HangmanGame {
    private static final String[] WORDS = {"kot", "pies", "dom", "samochód", "programowanie"};
    private static final int MAX_ATTEMPTS = 7;

    private String word;
    private List<Character> guessedLetters;
    private int remainingAttempts;

    public HangmanGame() {
        guessedLetters = new ArrayList<>();
        reset();
    }

    public void reset() {
        Random random = new Random();
        word = WORDS[random.nextInt(WORDS.length)];
        guessedLetters.clear();
        remainingAttempts = MAX_ATTEMPTS;
    }

    public boolean checkGuess(String guess) {
        if (guess.length() != 1) {
            return false;
        }

        char letter = guess.charAt(0);
        letter = Character.toLowerCase(letter);

        if (guessedLetters.contains(letter)) {
            return false;
        }

        guessedLetters.add(letter);

        boolean correctGuess = false;
        for (char c : word.toCharArray()) {
            if (c == letter) {
                correctGuess = true;
            }
        }

        if (!correctGuess) {
            remainingAttempts--;
        }

        return correctGuess;
    }

    public String getVisibleWord() {
        StringBuilder visibleWord = new StringBuilder();
        for (char c : word.toCharArray()) {
            if (guessedLetters.contains(c)) {
                visibleWord.append(c);
            } else {
                visibleWord.append("_");
            }
            visibleWord.append(" ");
        }
        return visibleWord.toString();
    }

    public boolean isGameWon() {
        for (char c : word.toCharArray()) {
            if (!guessedLetters.contains(c)) {
                return false;
            }
        }
        return true;
    }

    public boolean isGameLost() {
        return remainingAttempts == 0;
    }

    public String getWord() {
        return word;
    }

    public int getRemainingAttempts() {
        return remainingAttempts;
    }

    public char getHint() {
        for (char c : word.toCharArray()) {
            if (!guessedLetters.contains(c)) {
                return c;
            }
        }
        return ' ';
    }
}
