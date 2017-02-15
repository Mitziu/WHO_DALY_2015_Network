import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mitziu on 2/14/17.
 */
public class Country {

    private final String NAME;
    private Map<String, Double> burdenByDisease;

    public Country (String NAME) {
        this.NAME = NAME;
        burdenByDisease = new HashMap<>();
    }


    public void addDisease (String name, Double value) {
        burdenByDisease.put(name, value);
    }

    public Map<String, Double> getAllBurden () {
        return burdenByDisease;
    }

    public Double getBurden (String diseaseName) {
        return burdenByDisease.get(diseaseName);
    }

    public String getNAME () {
        return this.NAME;
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

    public static void main(String[] args) {
        Country A = new Country("USA");
        Country B = new Country("Canada");

        A.addDisease("DiseaseA", 1.0);
        A.addDisease("DiseaseB", 0.2);
        B.addDisease("DiseaseA",1.0);
        B.addDisease("DiseaseB",1.0);

        System.out.println(getCosineSimilarity(A, B));
    }

}

