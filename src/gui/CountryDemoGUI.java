package gui;

import javax.swing.*;

public class CountryDemoGUI {

    public static void main(String[] args) {

        WordBoard wordBoard = new WordBoard();
        wordBoard.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        wordBoard.frame.setSize(1000, 300);
        wordBoard.frame.setResizable(false);
        wordBoard.frame.setVisible(true);
    }
}
