import org.jgraph.JGraph;
import org.jgrapht.DirectedGraph;
import org.jgrapht.ListenableGraph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.ext.GmlExporter;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.ListenableUndirectedGraph;
import org.jgrapht.graph.ListenableUndirectedWeightedGraph;
import org.jgrapht.graph.SimpleWeightedGraph;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Mitziu on 2/14/17.
 */
public class CountryNetwork {

    UndirectedGraph graph;

    public CountryNetwork() {
        graph = new SimpleWeightedGraph(DefaultEdge.class);
    }

    public void addVertex(String Country) {
        graph.addVertex(Country);
    }

    public void addEdge (String countryA, String countryB, Double distance) {
        graph.addVertex(countryA);
        graph.addVertex(countryB);
        graph.addEdge(countryA, countryB, distance);
    }

    public void generateGMLFile(String output_file) {

        GmlExporter gmlExporter = new GmlExporter();
        gmlExporter.setPrintLabels(GmlExporter.PRINT_VERTEX_LABELS);

        Writer fileWriter = null;
        try {
            fileWriter = new BufferedWriter(new FileWriter(((output_file + ".gml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        gmlExporter.export(fileWriter, graph);

        System.out.println("Exported to gml file");

    }

    public Set<Double> getEdges (String CountryA, String CountryB) {
        return graph.getAllEdges(CountryA, CountryB);
    }
}
