package console;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class BufferedReadWriteVersion {

    private static final String COUNTRIES_FILE = "resources/countries.txt";

    public static void main(String[] args) {

        List<String> countries = getCountriesFromFile(COUNTRIES_FILE);

        System.out.println("Welcome To Country name Guessing Game");

        Scanner input = new Scanner(System.in);
        int score = 0;

        do {
            String randomCountry = countries.get(ThreadLocalRandom.current().nextInt(countries.size()));
            printQuestion(randomCountry);
            String guess = input.nextLine();

            if (guess.equalsIgnoreCase(randomCountry)) {
                System.out.println("Good Job That is Correct !");
                score++;
            } else {
                System.out.println("Wrong Guess , The country name was : [" + randomCountry.toUpperCase() + "]");
            }

            System.out.println("Play Again? y for yes -or- n or just enter for no");

        } while (input.nextLine().toLowerCase().startsWith("y"));

        System.out.println("YOUR SCORE IS " + score);
    }

    private static List<String> getCountriesFromFile(String path) {
        List<String> originalOrderList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {

            String line;
            while ((line = reader.readLine()) != null) {
                originalOrderList.add(line);
            }
        } catch (IOException e) {
            System.err.println("Unable to read countries file");
            System.exit(-1);
        }

        Collections.shuffle(originalOrderList);

        return originalOrderList;
    }

    private static void printQuestion(String country) {
        System.out.println("What is the name of the country that \nstarts with " + country.substring(0, 2));
        System.out.println("*HINT:this Country name has a: [" + country.length() + "] Letters\n");
        System.out.print("THE COUNTRY NAME IS : ");
    }
}
