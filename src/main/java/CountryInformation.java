import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mitziu on 2/16/17.
 */
public class CountryInformation {

    public static List<String> countriesFromCountryCSV;

    public static void main(String[] args) {
        countriesFromCountryCSV = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("all.csv"))) {

            countriesFromCountryCSV.add(br.readLine().split(",")[0]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void displayMissingCountries() {

    }

}
