package console;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BufferedReadWriteVersion {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int score = 0;
        int wordCounter = 0;
        String tryAgain;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/console/countries.txt"));
            ArrayList<String> originalOrderList = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                originalOrderList.add(line);
            }
            Collections.shuffle(originalOrderList);
            System.out.println("Welcome To Country name Guessing Game");
            do {

                char c1 = originalOrderList.get(wordCounter).charAt(0);
                char c2 = originalOrderList.get(wordCounter).charAt(1);

                System.out.println("What is the name of the country that \nstart with [" + c1 + "]"
                        + "And 2nd with with [" + c2 + "] ?"
                        + "\n*HINT:this Country name has a: [" + originalOrderList.get(wordCounter).length() + "] Letters");
                System.out.println("THE COUNTRY NAME IS :->");
                String guess = input.nextLine();
                if (guess.equalsIgnoreCase(originalOrderList.get(wordCounter))) {
                    System.out.println("Good Job That is Correct !");
                    score++;

                } else {
                    System.out.println("Wrong Guess , The country name was : [" + originalOrderList.get(wordCounter).toUpperCase() + "]");
                }
                wordCounter++;
                System.out.println("Play Again? y for yes -or- n or just enter for no");
                tryAgain = input.nextLine();
            } while (tryAgain.equalsIgnoreCase("y"));
            System.out.println("YOUR SCORE IS " + score);


        } catch (IOException e) {
            System.out.println("FILE NOT FOUND ");
        } catch (Exception z) {
            input.close();
        }

    }

}
