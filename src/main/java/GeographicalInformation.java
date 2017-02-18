import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GeographicalInformation {

    private Map<String, String> continentInformation;
    private Map<String, String> regionInformation;
    private List<String> countries;

    public GeographicalInformation() {
        continentInformation = new HashMap<>();
        regionInformation = new HashMap<>();
        countries = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader("all.csv"))) {
            String line;

            while (  (line = br.readLine()) != null ) {

                String countryName = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1)[0];
                String continent = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1)[1];
                String region = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1)[2];

                continentInformation.put(countryName, continent);
                regionInformation.put(countryName, region);
                countries.add(countryName);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getContient (String countryName) {
        //ADDED because error with CSV file
        if (countryName.equals("Côte d'Ivoire")) {
            return "Africa";
        }
        return continentInformation.get(countryName);
    }

    public List<String> getAllCountries () {
        return this.countries;
    }

    public String getRegion (String countryName) {
        //ADDED because error with CSV file
        if (countryName.equals("Côte d'Ivoire")) {
            return "Western Africa";
        }

        return regionInformation.get(countryName);
    }


}
