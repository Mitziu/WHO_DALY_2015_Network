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
        //graph = new ListenableUndirectedGraph(DefaultEdge.class);
        //graph = new ListenableUndirectedWeightedGraph(DefaultWeightedEdge.class);
        graph = new SimpleWeightedGraph(DefaultEdge.class);
    }

    public void addVertex(String Country) {
        graph.addVertex(Country);
    }

    public void addEdge (String countryA, String countryB) {
        graph.addVertex(countryA);
        graph.addVertex(countryB);
        graph.addEdge(countryA, countryB);
        //graph.addEdge(countryA, countryB, 1.0);

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

    public Set<DefaultEdge> getEdges () {
        return graph.edgeSet();
    }

    public Double weight(DefaultWeightedEdge e) {
        return graph.getEdgeWeight(e);
    }

    public static void main(String[] args) {
        UndirectedGraph g = new ListenableUndirectedWeightedGraph(DefaultWeightedEdge.class);
        g.addVertex("A");
        g.addVertex("b");
        g.addEdge("A","b",1.0);
        g.addEdge("A", "b");

        Set<Double> e = g.getAllEdges("A", "b");
        g.getAllEdges("A","c");

        System.out.println(e);

        System.out.println(g.toString());
    }
}
