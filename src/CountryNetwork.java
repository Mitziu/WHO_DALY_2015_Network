
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountryNetwork {

    private Map<String, Map<String, List<Disease>>> countryNetwork;

    public CountryNetwork () {
        countryNetwork = new HashMap<>();
    }

    public void addConnection (String countryA, String countryB, Disease disease) {
        addToNetwork(countryA, countryB, disease);
        addToNetwork(countryB, countryA, disease);
    }

    private void addToNetwork (String A, String B, Disease disease) {
        if (countryNetwork.containsKey(A)) {
            if (countryNetwork.containsKey(B)) {
                countryNetwork.get(A).get(B).add(disease);
            } else {
                countryNetwork.get(A).put(B, new ArrayList<>());
                countryNetwork.get(A).get(B).add(disease);
            }
        } else {
            countryNetwork.put(A, new HashMap<>());
            countryNetwork.get(A).put(B, new ArrayList<>());
            countryNetwork.get(A).get(B).add(disease);
        }
    }

    public static void main(String[] args) {
        CountryNetwork countryNetwork = new CountryNetwork();
        Disease a = new Disease("Type","SubType","Specific","Name");

        countryNetwork.addConnection("USA", "Canada", a);
        System.out.println("");
    }
}
