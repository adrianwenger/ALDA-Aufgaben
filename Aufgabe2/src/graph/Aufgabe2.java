package graph;

import SYSimulation.src.sim.SYSimulation;
import java.awt.Color;
import java.io.BufferedReader;
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

    /**
     *
     */
    private Aufgabe2() {
    }
    /**
     *
     */
    public static final int THREE = 3;
    /**
     *
     */
    public static final int TEN = 10;
    /**
     *
     */
    public static final int ZWEIHUNDERT = 200;

    /**
     * @param args the command line arguments.
     * @throws IOException
     */
    public static void main(final String[] args) throws IOException {
        Graph undirectedGraph = new AdjacencyListUndirectedGraph();
        Graph directedGraph = new AdjacencyListDirectedGraph();
        Graph<Integer> scotland = new AdjacencyListUndirectedGraph<>();

        //Anziehen im Winter
        anziehen();

        //----------------------------------------------------------------------
        for (int i = 1; i < ZWEIHUNDERT; i++) {
            scotland.addVertex(i);
        }

        //Read Taxi-List
        readInTaxi(scotland);
        System.out.println("EdgeList - ScotlandYard - TAXI:");
        System.out.println(scotland.getEdgeList());

        //Read All possible alternative
        readInAll(scotland);
        System.out.println("EdgeList - ScotlandYard - ALL:");
        System.out.println(scotland.getEdgeList());

        //Simulation
        SYSimulation sim;
        try {
            sim = new SYSimulation();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        List<Integer> breadthTaxi =
                GraphTraversion.breadthFirstSearch(scotland, 1);
        List<Integer> depthTaxi = GraphTraversion.depthFirstSearch(scotland, 1);
        List<Integer> dijkstraTaxi = new LinkedList<>();

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
        sim.startSequence("DijkstraTaxi");
        DijkstraShortestPath<Integer> dijkstra
                = new DijkstraShortestPath<>(scotland);
        dijkstra.searchShortestPath(1, 199);
        dijkstraTaxi = dijkstra.getShortestPath();
        //Taxistationen besuchen
        for (Integer v : dijkstraTaxi) {
            sim.visitStation(v);
        }

        iter = dijkstraTaxi.iterator();
        iter2 = dijkstraTaxi.iterator();
        iter2.next();
        while (iter.hasNext()) {
            int i = iter.next();
            if (!iter2.hasNext()) {
                break;
            }
            int j = iter2.next();
            switch ((int) scotland.getWeight(i, j)) {
                case 2:
                    sim.drive(i, j, Color.GREEN);
                    break;
                case 3:
                    sim.drive(i, j, Color.YELLOW);
                    break;
                case 5:
                    sim.drive(i, j, Color.MAGENTA);
                    break;
                default:
                    sim.drive(i, j, Color.CYAN);
            }
        }
        sim.stopSequence();

        undirectedGraphSearch(undirectedGraph);
        directedGraphSearch(directedGraph);

        System.exit(0);
    }

    /**
     * Read in Taxi-Connection.
     * @param graph graph
     * @throws IOException if file not found
     */
    public static void readInTaxi(final Graph graph) throws IOException {
        //Einlesen Taxi-Verbindungen
        //FileReader fr = new FileReader("/Users/philippschultheiss/Documents/HTWG/3_Semester/ALDA/ALDA-Aufgaben/Aufgabe2/src/graph/ScotlandYard.txt");
        FileReader fr = new FileReader("/Users/Adi/Studium/Semester3/Git_Hub/ALDA-Aufgaben/Aufgabe2/src/graph/ScotlandYard.txt");
        BufferedReader br = new BufferedReader(fr);
        LineNumberReader in = null;
        in = new LineNumberReader(fr);
        String line;
        while ((line = in.readLine()) != null) {
            String[] sf = line.split(" ");
            if (sf.length == THREE) {
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

    /**
     * read all lines from fr into graph scotland.
     * @param graph graph
     * @throws IOException If FIle not found
     */
    public static void readInAll(final Graph graph) throws IOException {
        //Einlesen Taxi-Verbindungen
        //FileReader fr = new FileReader("/Users/philippschultheiss/Documents/HTWG/3_Semester/ALDA/ALDA-Aufgaben/Aufgabe2/src/graph/ScotlandYard.txt");
        FileReader fr = new FileReader("/Users/Adi/Studium/Semester3/Git_Hub/ALDA-Aufgaben/Aufgabe2/src/graph/ScotlandYard.txt");
        BufferedReader br = new BufferedReader(fr);
        LineNumberReader in = null;
        in = new LineNumberReader(fr);
        String line;
        while ((line = in.readLine()) != null) {
            String[] sf = line.split(" ");
            if (sf.length == THREE) {
                int vertexOne = Integer.parseInt(sf[0]);
                int vertexTwo = Integer.parseInt(sf[1]);
                if (sf[2].equals("Bus")) {
                    graph.addEdge(vertexOne, vertexTwo, 2);
                }
                if (sf[2].equals("UBahn")) {
                    graph.addEdge(vertexOne, vertexTwo, 5);
                }
                if (sf[2].equals("Taxi")) {
                    graph.addEdge(vertexOne, vertexTwo, 3);
                }
            }
        }
        in.close();
        br.close();
    }

    /**
     * Aufgabenteil Anziehen Liefert lieste einer topologisch sortierten.
     * Anziehreihenfolge
     */
    public static void anziehen() {
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

        List<String> topologicalOrder =
                GraphTraversion.topologicalSort(anzGraph);
        System.out.println(topologicalOrder);
    }

    /**
     * Tiefen- und Breitensuche für ungerichteten Graph.
     * @param undirectedGraph undirectedGraph
     */
    public static void undirectedGraphSearch(final Graph undirectedGraph) {
        for (int i = 0; i < TEN; i++) {
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

    /**
     * Tiefen- und Breitensuche für gerichteten Graph.
     * @param directedGraph directedGraph
     */
    public static void directedGraphSearch(final Graph directedGraph) {
        for (int i = 0; i < TEN; i++) {
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
