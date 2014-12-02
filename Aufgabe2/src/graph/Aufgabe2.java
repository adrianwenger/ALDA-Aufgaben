package graph;

import SYSimulation.src.sim.SYSimulation;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author philippschultheiss, Adrian Wenger
 */
final class Aufgabe2 {

    private Aufgabe2() {
    }
    /**
     *
     */
    public static final int TEN = 10;

    /**
     * @param args the command line arguments
     */
    public static void main(final String[] args) throws FileNotFoundException, IOException {
        int n = TEN;
        Graph undirectedGraph = new AdjacencyListUndirectedGraph();
        Graph directedGraph = new AdjacencyListDirectedGraph();
        Graph<Integer> scotland = new AdjacencyListUndirectedGraph<>();
        
        //Anziehen im Winter
        anziehen();
        
        //----------------------------------------------------------------------
        for (int i = 1; i < 200; i++) {
            scotland.addVertex(i);
        }

        //Read Taxi-List
        readInTaxi(scotland);
        System.out.println("EdgeList - ScotlandYard:");
        System.out.println(scotland.getEdgeList());

        //Simulation
        SYSimulation sim;
        try {
            sim = new SYSimulation();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        List<Integer> breadthTaxi = GraphTraversion.breadthFirstSearch(scotland, 1);
        List<Integer> depthTaxi = GraphTraversion.depthFirstSearch(scotland, 1);

        //GUI starten
        sim.startSequence("BreadthTaxi");
        //Taxistationen besuchen
        for (Integer v : breadthTaxi) {
            sim.visitStation(v);
        }
        //
        Iterator<Integer> iter = breadthTaxi.iterator();
        Iterator<Integer> iter2 = breadthTaxi.iterator();
        iter2.next();
        while (iter.hasNext()) {
            int i = iter.next();
            if (!iter2.hasNext()) {
                break;
            }
            int j = iter2.next();
            sim.drive(i, j, Color.CYAN);
        }
        sim.stopSequence();
        //----------------------------------------------------------------------
        sim.startSequence("DepthTaxi");
        //Taxistationen besuchen
        for (Integer v : depthTaxi) {
            sim.visitStation(v);
        }

        iter = depthTaxi.iterator();
        iter2 = depthTaxi.iterator();
        iter2.next();
        while (iter.hasNext()) {
            int i = iter.next();
            if (!iter2.hasNext()) {
                break;
            }
            int j = iter2.next();
            sim.drive(i, j, Color.CYAN);
        }
        sim.stopSequence();
        //----------------------------------------------------------------------

        undirectedGraphSearch(undirectedGraph);
        directedGraphSearch(directedGraph);
        
        System.exit(0);
    }

    /*
     *Read in Taxi-Connection
     */
    public static void readInTaxi(Graph graph) throws FileNotFoundException, IOException {
        //Einlesen Taxi-Verbindungen
        FileReader fr = new FileReader("/Users/philippschultheiss/Documents/HTWG/3_Semester/ALDA/ALDA-Aufgaben/Aufgabe2/src/graph/ScotlandYard.txt");
        BufferedReader br = new BufferedReader(fr);
        LineNumberReader in = null;
        in = new LineNumberReader(fr);
        String line;
        while ((line = in.readLine()) != null) {
            String[] sf = line.split(" ");
            if (sf.length == 3) {
                if (sf[2].equals("Taxi")) {
                    int vertexOne = Integer.parseInt(sf[0]);
                    int vertexTwo = Integer.parseInt(sf[1]);
                    graph.addEdge(vertexOne, vertexTwo);
                }
            }
        }
        in.close();
        br.close();
    }
    /*
    *Aufgabenteil Anziehen
    *Liefert lieste einer topologisch sortierten Anziehreihenfolge
    */
    public static void anziehen(){
                //morgendliche Anziehen im Winter mit folgenden Tätigkeiten:
        String[] anziehen = {"Unterhose", "Unterhemd", "Strümpfe", "Hose",
            "Gürtel", "Hemd", "Pullover", "Schuhe", "Mantel", "Schal",
            "Handschuhe", "Mütze"};
        AdjacencyListDirectedGraph anzGraph = new AdjacencyListDirectedGraph();
        //Tätigkeiten in DirectedGraph als Vertex/Knoten einfügen
        for (int i = 0; i < anziehen.length; i++) {
            anzGraph.addVertex(anziehen[i]);
        }
        //Kanten einfügen
        anzGraph.addEdge("Unterhose", "Unterhemd");
        anzGraph.addEdge("Unterhose", "Strümpfe");
        anzGraph.addEdge("Unterhemd", "Strümpfe");
        anzGraph.addEdge("Strümpfe", "Hose");
        anzGraph.addEdge("Hose", "Gürtel");
        anzGraph.addEdge("Gürtel", "Hemd");
        anzGraph.addEdge("Hemd", "Pullover");
        anzGraph.addEdge("Pullover", "Schuhe");
        anzGraph.addEdge("Schuhe", "Mantel");
        anzGraph.addEdge("Mantel", "Schal");
        anzGraph.addEdge("Schal", "Handschuhe");
        anzGraph.addEdge("Handschuhe", "Mütze");
        
        System.out.println("topologische Sortierung prüfen");

        List<String> topologicalOrder = GraphTraversion.topologicalSort(anzGraph);
        System.out.println(topologicalOrder);
    }
    /*
    *Tiefen- und Breitensuche für ungerichteten Graph
    */
    public static void undirectedGraphSearch(Graph undirectedGraph){
        for (int i = 0; i < 10; i++) {
            undirectedGraph.addVertex(i);
        }
        for (int i = 0; i < TEN; i++) {
            int k = (i + 1) % TEN;
            undirectedGraph.addEdge(i, k);
        }

        List undirectedGraphList = undirectedGraph.getVertexList();

        System.out.println("undirectedGraph Suche");
        for (int i = 0; i < undirectedGraphList.size(); i++) {
            System.out.print(GraphTraversion.depthFirstSearch(undirectedGraph,
                    i));
            System.out.println("");
            System.out.print(GraphTraversion.breadthFirstSearch(undirectedGraph,
                    i));
            System.out.println("");
        }
    }
    /*
    *Tiefen- und Breitensuche für gerichteten Graph
    */
    public static void directedGraphSearch(Graph directedGraph){
        for (int i = 0; i < 10; i++) {
            directedGraph.addVertex(i);
        }

        for (int i = 0; i < TEN; i++) {
            int k = (i + 1) % TEN;
            directedGraph.addEdge(i, k);
        }

        List directedGraphList = directedGraph.getVertexList();

        System.out.println("DirectedGraph Suche:");

        for (int i = 0; i < directedGraphList.size(); i++) {
            System.out.print(GraphTraversion.depthFirstSearch(directedGraph,
                    i));
            System.out.println("");
            System.out.print(GraphTraversion.breadthFirstSearch(directedGraph,
                    i));
            System.out.println("");
        }
    }
}
