import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class GraphValidator {

    private GeographicalInformation geographicalInformation;
    private Set<DefaultEdge> edgesInNetwork;

    private Map<String, EdgeCounter> validateByContinent;
    private Map<String, EdgeCounter> validatedByRegion;


    public GraphValidator(GeographicalInformation geographicalInformation, Set<DefaultEdge> edgesInNetwork) {
        this.geographicalInformation = geographicalInformation;
        this.edgesInNetwork = edgesInNetwork;

        validateByContinent = new HashMap<>();
        validatedByRegion = new HashMap<>();

        Iterator<DefaultEdge> iterator = edgesInNetwork.iterator();
        Integer numEdges = 0;
//
//        System.out.println(iterator.next().toString());
        while (iterator.hasNext()) {
            String edge = iterator.next().toString();

            String countryA = edge.split(":")[0];
            String countryB = edge.split(":")[1];

            countryA = countryA.substring(1, countryA.length() - 1);
            countryB = countryB.substring(1, countryB.length() - 1);

            addEdge(countryA, countryB);
            addEdge(countryB, countryA);
            numEdges ++;
        }

        System.out.println("Number of Edges: " + numEdges);
        System.out.println("By Regions: ");
        for (Map.Entry<String, EdgeCounter> entry: validatedByRegion.entrySet()) {
            System.out.println("Region: " + entry.getKey());
            System.out.println("\tWithin: " + entry.getValue().getWithInGroup());
            System.out.println("\tOutside: " + entry.getValue().getOutsideGroup());
            System.out.println("\tRatio: " + entry.getValue().getRatio());
        }

        System.out.println("\n\nBy Continent: ");
        for (Map.Entry<String, EdgeCounter> entry: validateByContinent.entrySet()) {
            System.out.println("Continent: " + entry.getKey());
            System.out.println("\tWithin: " + entry.getValue().getWithInGroup());
            System.out.println("\tOutside: " + entry.getValue().getOutsideGroup());
            System.out.println("\tRatio: " + entry.getValue().getRatio());
        }

    }

    private void addEdge (String countryA, String countryB) {
        String continentA = geographicalInformation.getContient(countryA);
        String regionA = geographicalInformation.getRegion(countryA);

        String continentB = geographicalInformation.getContient(countryB);
        String regionB = geographicalInformation.getRegion(countryB);

        if (continentA.equals(continentB)) {
           if (validateByContinent.containsKey(continentA)) {
               validateByContinent.get(continentA).addWithInGroup();
           } else {
               validateByContinent.put(continentA, new EdgeCounter());
               validateByContinent.get(continentA).addWithInGroup();
           }
        } else {
            if (validateByContinent.containsKey(continentA)) {
                validateByContinent.get(continentA).addOutsideGroup();
            } else {
                validateByContinent.put(continentA, new EdgeCounter());
                validateByContinent.get(continentA).addOutsideGroup();
            }
        }

        if (regionA.equals(regionB)) {
            if (validatedByRegion.containsKey(regionA)) {
                validatedByRegion.get(regionA).addWithInGroup();
            } else {
                validatedByRegion.put(regionA, new EdgeCounter());
                validatedByRegion.get(regionA).addWithInGroup();
            }
        } else {
            if (validatedByRegion.containsKey(regionA)) {
                validatedByRegion.get(regionA).addOutsideGroup();
            } else {
                validatedByRegion.put(regionA, new EdgeCounter());
                validatedByRegion.get(regionA).addOutsideGroup();
            }
        }

    }

    private class EdgeCounter {
        private Integer withInGroup;
        private Integer outsideGroup;

        public EdgeCounter () {
            withInGroup = 0;
            outsideGroup = 0;
        }

        public void addWithInGroup () {
            withInGroup++;
        }

        public void addOutsideGroup () {
            outsideGroup++;
        }

        public Integer getWithInGroup() {
            return this.withInGroup;
        }

        public Integer getOutsideGroup() {
            return this.outsideGroup;
        }

        public Double getRatio () {
            return (Double.valueOf(withInGroup) / Double.valueOf(outsideGroup));
        }
    }

}
