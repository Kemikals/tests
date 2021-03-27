package console;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CountryDemo {

    public static void main(String[] args) {
        //DATA
        final String ORIGINAL_LIST = "src/console/countries.txt";
        final String LIST_IN_RANDOM = "countriesInRandom.txt";
        try {
            //STORED IN A LIST
            List<String> countryList = Files.lines(Paths.get(ORIGINAL_LIST))
                    .map(String::toUpperCase)
                    .collect(Collectors.toList());
            //SHUFFLE THE LIST TO HAVE IT IN RANDOM ORDER
            Collections.shuffle(countryList);

            countryList.forEach(System.out::println);
            //WRITE THE NEW LIST
            Files.write(Paths.get(LIST_IN_RANDOM), countryList);

        } catch (IOException e) {
            System.out.println("ERROR IN FILE / OR FILE NOT FOUND");
        }
    }
}
