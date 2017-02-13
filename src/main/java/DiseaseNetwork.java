import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiseaseNetwork {

    private Map<Disease, Map<Disease, List<String>>> diseaseNetwork;

    public DiseaseNetwork() {
        diseaseNetwork = new HashMap<>();
    }

    public void addConnection (Disease A, Disease B, String country) {
        addToNetwork(A, B, country);
        addToNetwork(B, A, country);
    }

    private void addToNetwork (Disease A, Disease B, String country) {
        if (diseaseNetwork.containsKey(A)) {
            if (diseaseNetwork.containsKey(B)) {
                diseaseNetwork.get(A).get(B).add(country);
            } else {
                diseaseNetwork.get(A).put(B, new ArrayList<>());
                diseaseNetwork.get(A).get(B).add(country);
            }
        } else {
            diseaseNetwork.put(A, new HashMap<>());
            diseaseNetwork.get(A).put(B, new ArrayList<>());
            diseaseNetwork.get(A).get(B).add(country);
        }
    }

    public static void main(String[] args) {
        DiseaseNetwork diseaseNetwork = new DiseaseNetwork();
        Disease a = new Disease("Type", "SubType","specific", "name");
        Disease b = new Disease("TypeB", "SB","SPB","nameb");

        diseaseNetwork.addConnection(a, b, "testCountry");
        System.out.println("");
    }

}
