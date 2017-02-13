import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Mitziu on 2/12/17.
 */
public class Disease {

    private String type;
    private String subType;
    private String specificTypeOfDisease;
    private String nameOfDisease;

    private Map<String, Double> burdenByCountry;

    public Disease (String type, String subType, String specificTypeOfDisease, String nameOfDisease) {
        this.type = type;
        this.subType = subType;
        this.specificTypeOfDisease = specificTypeOfDisease;
        this.nameOfDisease = nameOfDisease;

        burdenByCountry = new TreeMap<>();
    }

    public void putBurden (String country, Double value) {
        burdenByCountry.put(country, value);
    }

    public Double getBurden (String country) {
        return burdenByCountry.get(country);
    }

    public Map<String, Double> getAllCountries () {
        return burdenByCountry;
    }
}
