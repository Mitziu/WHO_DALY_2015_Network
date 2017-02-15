import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static List<Country> countryInformation;
    public static CountryNetwork countryNetwork;

    //public static final String FILE_NAME = "WHODALEY2015_CLEANUP.csv";
    public static String FILE_NAME;
    public static Double COSINE_VALUE;


    public static void main(String[] args) {
        getInput();

        countryInformation = new ArrayList<>();
        countryNetwork = new CountryNetwork();
        readFile();
        System.out.println("");
        createCountryNetwork();
        countryNetwork.displayGraph();

    }

    public static void getInput () {
        Scanner scanner = new Scanner(System.in);
        System.out.print("File Name: ");
        FILE_NAME = scanner.nextLine();

        System.out.print("Cosine Value: ");
        COSINE_VALUE = scanner.nextDouble();
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
            countryInformation.add(index, new Country(country));
            index++;
        }
    }

    public static void loadDiseases (String[] diseaseInfo) {
        for(int i = 4; i < diseaseInfo.length; i++) {
            countryInformation.get(i - 4).addDisease(diseaseInfo[3], Double.valueOf(diseaseInfo[i]));
        }
    }

    private static void createCountryNetwork () {
        for(int i = 0; i < countryInformation.size(); i++) {
            for(int j = i + 1; j < countryInformation.size(); j++) {
                if(getCosineSimilarity(countryInformation.get(i), countryInformation.get(j)) > COSINE_VALUE) {
                    countryNetwork.addEdge(countryInformation.get(i).getNAME(), countryInformation.get(j).getNAME());
                }
            }
        }
    }

    private static Double getCosineSimilarity (Country A, Country B) {
        Double sqrtDenA = 0.0;
        Double sqrtDenB = 0.0;
        Double numerator = 0.0;
        for(Map.Entry<String, Double> set : A.getAllBurden().entrySet()) {
            Double a = A.getBurden(set.getKey());
            Double b = B.getBurden(set.getKey());

            numerator += (a * b);
            sqrtDenA += Math.pow(a, 2);
            sqrtDenB += Math.pow(b, 2);
        }

        return ((numerator)/(Math.sqrt(sqrtDenA) * Math.sqrt(sqrtDenB)));
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












