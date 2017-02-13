import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mitziu on 2/12/17.
 */
public class Main {

    public static List<Disease> diseases;
    public static List<String> countries;

    public static final String FILE_NAME = "WHODALEY2015_CLEANUP.csv";

    public static void main(String[] args) {
        diseases = new ArrayList<>();
        countries = new ArrayList<>();

        readFile();
    }

    public static void readFile () {

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            loadCountries(splitLine(br.readLine()));

            String line;

            while ( (line = br.readLine()) != null ) {
                loadDiseases(splitLine(line));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Loads the countries into the list
     * @param countriesArray Array of countries
     */
    public static void loadCountries (String[] countriesArray) {
        Integer index = 0;
        for(String country: countriesArray) {
            if (country.isEmpty()) continue;

            countries.add(index++, country);
        }
    }

    public static void loadDiseases (String[] diseaseInfo) {
        Disease disease = new Disease(diseaseInfo[0], diseaseInfo[1], diseaseInfo[2], diseaseInfo[3]);

        for(int i = 4; i < diseaseInfo.length; i++) {
            disease.putBurden(countries.get(i - 4), Double.valueOf(diseaseInfo[i]));
        }

        diseases.add(disease);
    }

    /**
     * Splits the line by commas while avoiding commas in between quotes
     * @param line Line to be split
     * @return Line split by commas and avoiding commas in between quotes
     */
    public static String[] splitLine (String line) {
        return line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
    }

}