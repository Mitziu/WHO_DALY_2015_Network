import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Set;


public class GraphValidator {

    private GeographicalInformation geographicalInformation;
    private Set<DefaultEdge> edgesInNetwork;

    Map<String, EdgeCounter> mapByContinents;
    Map<String, EdgeCounter> mapByRegions;

    private Integer ctr = 0;

    public GraphValidator(GeographicalInformation geographicalInformation, CountryNetwork countryNetwork, List<Country> countries) {

        mapByContinents = new HashMap<>();
        mapByRegions = new HashMap<>();
        this.geographicalInformation = geographicalInformation;

        for(int i = 0; i < countries.size(); i++) {
            for(int j = i; j < countries.size(); j++) {
                String countryA = countries.get(i).getNAME();
                String countryB = countries.get(j).getNAME();

                Set<Double> edges = countryNetwork.getEdges(countryA, countryB);
                if (edges == null) {
                    continue;
                } else {
                    addEdge(countryA, countryB, edges);
                }

            }
        }

        System.out.println("# of edges: " + ctr);

        DecimalFormat dc = new DecimalFormat("#.####");

        System.out.println("Continents: ");
        for(Map.Entry<String, EdgeCounter> entry: mapByContinents.entrySet()) {
            System.out.println("\tContinent: " + entry.getKey());
            System.out.println("\t\tWithin Average: " + dc.format(entry.getValue().getAverageWithIn()));
            System.out.println("\t\tOutside Average: " + dc.format(entry.getValue().getAverageOutside()));
            System.out.println("\t\tRatio (WITHIN / OUTSIDE) : " + dc.format(entry.getValue().getRatio()));
        }

        System.out.println("\n\nRegions: ");
        for(Map.Entry<String, EdgeCounter> entry: mapByRegions.entrySet()) {
            System.out.println("\tRegions: " + entry.getKey());
            System.out.println("\t\tWithin Average: " + dc.format(entry.getValue().getAverageWithIn()));
            System.out.println("\t\tOutside Average: " + dc.format(entry.getValue().getAverageOutside()));
            System.out.println("\t\tRatio (WITHIN / OUTSIDE) : " + dc.format(entry.getValue().getRatio()));
        }

    }

    private void addEdge (String countryA, String countryB, Set<Double> edges) {
        Iterator<Double> iterator = edges.iterator();

        if (!iterator.hasNext()) {
            return;
        }

        Double distance = iterator.next();

        addEdgeToMap(countryA, countryB, distance);
        addEdgeToMap(countryB, countryA, distance);
        ctr++;
    }

    private void addEdgeToMap (String countryA, String countryB, Double length) {

        String continentA = geographicalInformation.getContient(countryA);
        String continentB = geographicalInformation.getContient(countryB);

        String regionA = geographicalInformation.getRegion(countryA);
        String regionB = geographicalInformation.getRegion(countryB);

        if (continentA.equals(continentB)) {
            if (mapByContinents.containsKey(continentA)) {
                mapByContinents.get(continentA).addWithIn(length);
            } else {
                mapByContinents.put(continentA, new EdgeCounter());
                mapByContinents.get(continentA).addWithIn(length);
            }
        } else {
            if (mapByContinents.containsKey(continentA)) {
                mapByContinents.get(continentA).addOutside(length);
            } else {
                mapByContinents.put(continentA, new EdgeCounter());
                mapByContinents.get(continentA).addOutside(length);
            }
        }

        if (regionA.equals(regionB)) {
            if (mapByRegions.containsKey(regionA)) {
                mapByRegions.get(regionA).addWithIn(length);
            } else {
                mapByRegions.put(regionA, new EdgeCounter());
                mapByRegions.get(regionA).addWithIn(length);
            }
        } else {
            if (mapByRegions.containsKey(regionA)) {
                mapByRegions.get(regionA).addOutside(length);
            } else {
                mapByRegions.put(regionA, new EdgeCounter());
                mapByRegions.get(regionA).addOutside(length);
            }
        }
    }

    private class EdgeCounter {

        private List<Double> withIn;
        private List<Double> outside;

        public EdgeCounter () {
            withIn = new ArrayList<>();
            outside = new ArrayList<>();
        }

        public void addWithIn (Double length) {
            withIn.add(length);
        }

        public void addOutside (Double length) {
            outside.add(length);
        }

        public Double getAverageWithIn () {
            OptionalDouble d = withIn.stream()
                    .mapToDouble(a -> a)
                    .average();

            if (d.isPresent()) {
                return d.getAsDouble();
            } else {
                return 0.0;
            }
        }

        public Double getAverageOutside () {
            OptionalDouble d = outside.stream()
                    .mapToDouble(a -> a)
                    .average();

            if (d.isPresent()) {
                return d.getAsDouble();
            } else {
                return 0.0;
            }
        }

        public Double getRatio () {
            return (getAverageWithIn() / getAverageOutside());
        }

    }

}










