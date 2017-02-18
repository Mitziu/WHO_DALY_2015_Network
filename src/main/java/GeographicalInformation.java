import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class GeographicalInformation {

    private Map<String, String> continentInformation;
    private Map<String, String> regionInformation;


    public GeographicalInformation() {
        continentInformation = new HashMap<>();
        regionInformation = new HashMap<>();

        try(BufferedReader br = new BufferedReader(new FileReader("all.csv"))) {
            String line;

            while (  (line = br.readLine()) != null ) {

                String countryName = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1)[0];
                String continent = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1)[1];
                String region = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1)[2];

                continentInformation.put(countryName, continent);
                regionInformation.put(countryName, region);

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

    public String getRegion (String countryName) {
        //ADDED because error with CSV file
        if (countryName.equals("Côte d'Ivoire")) {
            return "Western Africa";
        }

        return regionInformation.get(countryName);
    }


}
