import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class HangmanFrame extends JFrame {
    private HangmanGame game;
    private HangmanDraw hangmanDraw;
    private JTextField guessField;
    private JButton checkButton;
    private JButton hintButton;
    private JButton resetButton;
    private JLabel wordLabel;
    private JLabel statusLabel;
    private Set<Character> guessedLetters;

    public HangmanFrame(HangmanGame game) {
        this.game = game;
        setTitle("Gra w wisielca");
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(600, 400));

        hangmanDraw = new HangmanDraw();
        guessedLetters = new HashSet<>();

        guessField = new JTextField(20);
        guessField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    checkGuess();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        checkButton = new JButton("Sprawdź");
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        hintButton = new JButton("Podpowiedź");
        hintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                useHint();
            }
        });

        resetButton = new JButton("OD NOWA");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

        wordLabel = new JLabel();
        wordLabel.setFont(new Font("Arial", Font.BOLD, 20));

        statusLabel = new JLabel();
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        JPanel inputPanel = new JPanel();
        inputPanel.add(guessField);
        inputPanel.add(checkButton);
        inputPanel.add(hintButton);
        inputPanel.add(resetButton);

        JPanel hangmanPanel = new JPanel(new BorderLayout());
        hangmanPanel.setPreferredSize(new Dimension(200, 400));
        hangmanPanel.add(hangmanDraw, BorderLayout.CENTER);

        add(hangmanPanel, BorderLayout.WEST);
        add(wordLabel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
        add(inputPanel, BorderLayout.NORTH);

        pack();

        updateWordLabel();
        updateStatusLabel();
    }

    private void checkGuess() {
        String guess = guessField.getText().toLowerCase();
        char letter = guess.charAt(0);

        if (guessedLetters.contains(letter)) {
            JOptionPane.showMessageDialog(this, "Ta litera została już wpisana.");
        } else {
            guessedLetters.add(letter);
            boolean correct = game.checkGuess(guess);
            guessField.setText("");
            updateWordLabel();
            updateStatusLabel();

            if (!correct) {
                hangmanDraw.drawNextPart();
            }

            if (correct) {
                if (game.isGameWon()) {
                    JOptionPane.showMessageDialog(this, "Gratulacje! Wygrałeś!");
                    resetGame();
                }
            } else {
                if (game.isGameLost()) {
                    JOptionPane.showMessageDialog(this, "Przegrałeś! Hasło to: " + game.getWord());
                    resetGame();
                }
            }
        }
    }

    private void useHint() {
        char hint = game.getHint();
        game.checkGuess(String.valueOf(hint));
        updateWordLabel();
        updateStatusLabel();

        if (game.isGameWon()) {
            JOptionPane.showMessageDialog(this, "Gratulacje! Wygrałeś!");
            resetGame();
        }
    }

    private void resetGame() {
        game.reset();
        hangmanDraw.reset();
        guessedLetters.clear();
        updateWordLabel();
        updateStatusLabel();
    }

    private void updateWordLabel() {
        String word = game.getVisibleWord();
        wordLabel.setText(word);
    }

    private void updateStatusLabel() {
        String status = "Pozostałe próby: " + game.getRemainingAttempts();
        statusLabel.setText(status);
    }
}
