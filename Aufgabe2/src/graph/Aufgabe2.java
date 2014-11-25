package graph;

import java.util.List;

/**
 *
 * @author philippschultheiss
 */
public final class Aufgabe2 {

    private Aufgabe2() {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(final String[] args) {
        int n = 10;
        Graph<V> undirectedGraph = new AdjacencyListUndirectedGraph<>();
        Graph<V> directedGraph = new AdjacencyListDirectedGraph<>();
        V[] vertArray = new V[n];

        //morgendliche Anziehen im Winter mit folgenden Tätigkeiten:
        String[] anziehen = {"Unterhose", "Unterhemd", "Strümpfe", "Hose", "Gürtel", "Hemd",  "Pullover", "Schuhe", "Mantel", "Schal", "Handschuhe", "Mütze"};

        Graph<String> directedGraph1 = new AdjacencyListDirectedGraph<>();
        //Tätigkeiten in DirectedGraph als Vertex/Knoten einfügen
        for(int i = 0; i < anziehen.length; i++){
            directedGraph1.addVertex(anziehen[i]);
        }
        //Kanten einfügen
        for(int i = 0; i < anziehen.length - 1; i++){
            directedGraph1.addEdge(anziehen[i], anziehen[i+1]);
        }

        for (int i = 0; i < vertArray.length; i++) {
            vertArray[i] = new V(i);
        }

        for (int i = 0; i < n; i++) {
            undirectedGraph.addVertex(vertArray[i]);
            directedGraph.addVertex(vertArray[i]);
        }

        for (int i = 0; i < vertArray.length; i++) {
            int k = (i + 1) % vertArray.length;
            undirectedGraph.addEdge(vertArray[i], vertArray[k]);
            directedGraph.addEdge(vertArray[i], vertArray[k]);
        }

        List<V> undirectedGraphList = undirectedGraph.getVertexList();
        List<V> directedGraphList = directedGraph.getVertexList();

       System.out.println("undirectedGraph Suche");
        for (V vertex : undirectedGraphList) {
            System.out.print(GraphTraversion.depthFirstSearch(undirectedGraph,
                    vertex));
            System.out.println("");
            System.out.print(GraphTraversion.breadthFirstSearch(undirectedGraph,
                    vertex));
            System.out.println("");
        }

        System.out.println("DirectedGraph Suche:");


        for (V vertex : directedGraphList) {
            System.out.print(GraphTraversion.depthFirstSearch(directedGraph,
                    vertex));
            System.out.println("");
            System.out.print(GraphTraversion.breadthFirstSearch(directedGraph,
                    vertex));
            System.out.println("");
        }
    }

    private static class V {

        private final Integer number;

        public V(final int number) {
            this.number = number;
        }

        public int getNumber() {
            return number;
        }

        @Override
        public String toString() {
            return number.toString();
        }
    }
}
