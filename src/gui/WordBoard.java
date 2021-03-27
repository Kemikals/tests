package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class WordBoard {

    public final JFrame frame;
    private final Font myFont;
    private final Word word;
    private final ArrayList<Character> wordLetters;
    private final ArrayList<String> words, corrects;
    private final JTextField guess, score, firstHint, secondHint, wordMixed;
    private JButton check;
    private int scoreBank, wordIndex;

    WordBoard() {
        scoreBank = 0;
        firstHint = new JTextField();
        secondHint = new JTextField();
        score = new JTextField();
        guess = new JTextField();
        wordMixed = new JTextField();
        word = new Word();
        frame = new JFrame();
        wordLetters = new ArrayList<>();
        words = new ArrayList<>();
        corrects = new ArrayList<>();
        myFont = new Font(Font.MONOSPACED, Font.BOLD, 20);
        frame.getContentPane().add(createTitle(), BorderLayout.NORTH);
        data();
        closingAction();
        addAndStyleComponents(frame);
    }

    private void closingAction() {
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent winEvt) {
                BufferedWriter writer;
                try {

                    File correctsWords = new File("corrects.txt");
                    writer = new BufferedWriter(new FileWriter(correctsWords));

                    writer.append("*****************************************************\n" + "YOU HAVE GUESSED : [")
                            .append(String.valueOf(corrects.size())).append("] COUNTRIES NAMES CORRECTS\n")
                            .append("YOUR SCORE IS : [").append(String.valueOf(scoreBank))
                            .append("] POINTS\n").append("REPORT DATE : ")
                            .append(String.valueOf(LocalDate.now()))
                            .append("\n")
                            .append("*****************************************************\n");

                    for (String correct : corrects) {
                        writer.append(correct).append("\n");
                    }
                    writer.close();
                    Desktop.getDesktop().open(correctsWords);
                    frame.dispose();

                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("error in opining results file ");
                }

            }

        });
    }


    private void data() {
        try {
            String line;
            BufferedReader reader = new BufferedReader(new FileReader("src/gui/countries.txt"));

            while ((line = reader.readLine()) != null) {
                words.add(line);
            }
            Collections.shuffle(words);

        } catch (Exception x) {
            System.out.println("File Not Found");
        }

    }

    private void addAndStyleComponents(JFrame frame) {
        JPanel container = new JPanel();
        container.setLayout(new GridLayout(6, 2, 20, 5));

        textAndLabelInput(container, "THE FIRST LETTER IS -->", firstHint);
        textAndLabelInput(container, "THE SECOND LETTER IS -->", secondHint);
        container.add(label("LETTERS OF THIS WORD NOT-IN ORDER ARE:"));
        container.add(wordMixed);
        textAndLabelInput(container, "WHAT IS THE ABOVE COUNTRY NAME :", guess);
        createGameButton(container, nextButton(), checkButton());
        textAndLabelInput(container, "YOUR SCORE NOW IS :", score);

        setSettings(firstHint, secondHint, score, guess, wordMixed);
        frame.getContentPane().add(container, BorderLayout.CENTER);

    }

    //setting my top store title on the frame
    private JPanel createTitle() {
        JPanel box = new JPanel();
        box.add(label("COUNTRY NAME GUESSING GAME"));
        return box;
    }

    private JLabel label(String name) {
        JLabel label = new JLabel(name);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(myFont);
        return label;
    }

    private void textAndLabelInput(JPanel container, String labelName, JTextField textField) {
        container.add(label(labelName));
        container.add(textField);
    }

    private void createGameButton(JPanel container, JButton button1, JButton button2) {
        container.add(button1);
        container.add(button2);
    }

    //button to get check the guess from user input
    private JButton checkButton() {
        check = new JButton("CHECK MY GUESS");
        check.setHorizontalAlignment(SwingConstants.CENTER);
        check.setFocusable(false);
        check.setEnabled(false);
        check.setFont(myFont);
        check.addActionListener(c -> checkGuessAction());
        return check;
    }

    //button to get the next word to guess from the shuffled list
    private JButton nextButton() {
        JButton next1 = new JButton("NEXT WORD");
        next1.setFocusable(false);
        next1.setHorizontalAlignment(SwingConstants.CENTER);
        next1.setFont(myFont);
        next1.addActionListener(next -> nextWordAction());
        return next1;
    }

    //setting the JTextFields for each of their Fonts,Alignment ..
    private void setSettings(JTextField... textField) {
        for (JTextField text : textField) {
            text.setFont(myFont);
            text.setHorizontalAlignment(SwingConstants.CENTER);
            text.setEditable(false);
        }
    }

    //handle check the guess action
    private void checkGuessAction() {
        if (guess.getText().equalsIgnoreCase(words.get(wordIndex))) {
            scoreBank = scoreBank + 1;
            JOptionPane.showMessageDialog(frame, "GOOD JOB YOU GOT THIS RIGHT ,KEEP IT UP!"
                    + "\nCLICK [OK] TO GUESS ANOTHER WORD"
                    + "\n-OR-"
                    + "\nIF YOU NO LONGER WANT TO PLAY "
                    + "CLICK [OK] THEN JUST CLOSE/EXIT THE GAME FROM THE (X) ON TOP");
            score.setText(String.valueOf(scoreBank));
            corrects.add(words.get(wordIndex).toUpperCase());

        } else {
            JOptionPane.showMessageDialog(frame, "WRONG GUESS , THE WORD WAS : " +
                    "[" + words.get(word.getSelectedWord()).toUpperCase() + "]\n"
                    + "CLICK [OK] TO GUESS ANOTHER WORD"
                    + "\n-OR-"
                    + "\nIF YOU NO LONGER WANT TO PLAY "
                    + "\nCLICK [OK] THEN JUST CLOSE/EXIT THE GAME FROM THE (X) ON TOP");
        }
        nextWordAction();
    }

    //handle next word to guess action
    private void nextWordAction() {
        check.setEnabled(true);
        guess.setText(null);
        guess.setEditable(true);
        word.setSelectedWord(wordIndex + 1);

        try {
            for (int c = 0; c < words.get(word.getSelectedWord()).length(); c++) {
                wordLetters.add(words.get(word.getSelectedWord()).charAt(c));
            }
            Collections.shuffle(wordLetters);

            wordMixed.setText(wordLetters.toString().toUpperCase());

            firstHint.setText(word.firstLetter(words));

            secondHint.setText(word.secondLetter(words));

            wordIndex++;

            wordLetters.clear();
        } catch (Exception xx) {
            JOptionPane.showMessageDialog(null, "OUT OF WORDS TRY AGAIN LATER ");
            closingAction();
        }
    }


}
