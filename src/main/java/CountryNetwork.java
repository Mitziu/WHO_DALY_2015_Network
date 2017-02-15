import org.jgraph.JGraph;
import org.jgrapht.ListenableGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableUndirectedGraph;

import javax.swing.*;

/**
 * Created by Mitziu on 2/14/17.
 */
public class CountryNetwork {

    ListenableGraph graph;
    JGraphModelAdapter adapter;

    public CountryNetwork() {
        graph = new ListenableUndirectedGraph(DefaultEdge.class);
        adapter = new JGraphModelAdapter(graph);

    }

    public void addEdge (String countryA, String countryB) {
        graph.addVertex(countryA);
        graph.addVertex(countryB);
        graph.addEdge(countryA, countryB);
    }

    public void displayGraph () {
        System.out.println(graph.toString());

        JFrame frame = new JFrame();
        frame.setSize(400 , 400);
        JGraph jGraph = new JGraph(new JGraphModelAdapter(graph));
        frame.getContentPane().add(jGraph);
        frame.setVisible(true);
        while(true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        CountryNetwork countryNetwork = new CountryNetwork();
//
//        countryNetwork.addEdge("USA","Canada");
//
//        countryNetwork.addEdge("USA", "Mexico");
//
//
        countryNetwork.displayGraph();
    }

}
