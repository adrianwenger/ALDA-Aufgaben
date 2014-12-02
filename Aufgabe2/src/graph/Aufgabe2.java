package graph;

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
    public static void main(final String[] args) {

        int n = TEN;
        Graph undirectedGraph = new AdjacencyListUndirectedGraph();
        Graph directedGraph = new AdjacencyListDirectedGraph();
        
        //morgendliche Anziehen im Winter mit folgenden Tätigkeiten:
        String[] anziehen = {"Unterhose", "Unterhemd", "Strümpfe", "Hose",
            "Gürtel", "Hemd", "Pullover", "Schuhe", "Mantel", "Schal",
            "Handschuhe", "Mütze"};
         //morgendliche Anziehen im Winter mit folgenden Tätigkeiten:
        //int[] anziehen = {1,2,3,4,5,6,7,8,9,10};
        
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
        

        for (int i = 0; i < n; i++) {
            undirectedGraph.addVertex(i);
            directedGraph.addVertex(i);
        }

        for (int i = 0; i < TEN; i++) {
            int k = (i + 1) % TEN;
            undirectedGraph.addEdge(i, k);
            directedGraph.addEdge(i, k);
        }

        List undirectedGraphList = undirectedGraph.getVertexList();
        List directedGraphList = directedGraph.getVertexList();

        System.out.println("undirectedGraph Suche");
        for (int i = 0; i < undirectedGraphList.size(); i++) {
            System.out.print(GraphTraversion.depthFirstSearch(undirectedGraph,
                    i));
            System.out.println("");
            System.out.print(GraphTraversion.breadthFirstSearch(undirectedGraph,
                    i));
            System.out.println("");
        }

        System.out.println("DirectedGraph Suche:");

        for (int i = 0; i < directedGraphList.size(); i++) {
            System.out.print(GraphTraversion.depthFirstSearch(directedGraph,
                    i));
            System.out.println("");
            System.out.print(GraphTraversion.breadthFirstSearch(directedGraph,
                    i));
            System.out.println("");
        }

        System.out.println("topologische Sortierung prüfen");

            List<String> topologicalOrder = new LinkedList();
            topologicalOrder = GraphTraversion.topologicalSort(anzGraph);

            for (String vertex : topologicalOrder) {
                System.out.println(vertex);
            }
        }
}
