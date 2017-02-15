import org.jgraph.JGraph;
import org.jgrapht.DirectedGraph;
import org.jgrapht.ListenableGraph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.ext.GmlExporter;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableUndirectedGraph;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by Mitziu on 2/14/17.
 */
public class CountryNetwork {

    UndirectedGraph graph;
    //JGraphModelAdapter adapter;

    public CountryNetwork() {
        graph = new ListenableUndirectedGraph(DefaultEdge.class);
        //adapter = new JGraphModelAdapter(graph);

    }

    public void addEdge (String countryA, String countryB) {
        graph.addVertex(countryA);
        graph.addVertex(countryB);
        graph.addEdge(countryA, countryB);
    }

    public void displayGraph (String output_file) {
        System.out.println(graph.toString());

//        JFrame frame = new JFrame();
//        frame.setSize(400 , 400);
//        JGraph jGraph = new JGraph(new JGraphModelAdapter(graph));
//        frame.getContentPane().add(jGraph);
//        frame.setVisible(true);
//        while(true) {
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        GmlExporter gmlExporter = new GmlExporter();
        gmlExporter.setPrintLabels(GmlExporter.PRINT_VERTEX_LABELS);
        Writer fileWriter = null;
        try {
            fileWriter = new BufferedWriter(new FileWriter(((output_file + ".gml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        gmlExporter.export(fileWriter, graph);

    }

}
