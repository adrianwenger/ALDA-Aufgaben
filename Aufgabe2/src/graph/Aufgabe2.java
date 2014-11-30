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
        Graph<V> undirectedGraph = new AdjacencyListUndirectedGraph<>();
        Graph<V> directedGraph = new AdjacencyListDirectedGraph<>();
        V[] vertArray = new V[n];

        //morgendliche Anziehen im Winter mit folgenden Tätigkeiten:
//        String[] anziehen = {"Unterhose", "Unterhemd", "Strümpfe", "Hose",
//            "Gürtel", "Hemd", "Pullover", "Schuhe", "Mantel", "Schal",
//            "Handschuhe", "Mütze"};
         //morgendliche Anziehen im Winter mit folgenden Tätigkeiten:
        int[] anziehen = {1,2,3,4,5,6,7,8,9,10};

        Graph<V> directedGraphAnziehen = new AdjacencyListDirectedGraph<>();
        //Tätigkeiten in DirectedGraph als Vertex/Knoten einfügen
        for (int i = 0; i < anziehen.length; i++) {
            directedGraphAnziehen.addVertex(new V(anziehen[i]));
        }
        //Kanten einfügen
        for (int i = 0; i < anziehen.length; i++) {
            int k = (i + 1) % anziehen.length;
            directedGraphAnziehen.addEdge(new V(anziehen[i]), new V(anziehen[k]));
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

        System.out.println("topologische Sortierung prüfen");

            List<V> topologicalOrder = new LinkedList();
            topologicalOrder = GraphTraversion.topologicalSort(
                    (DirectedGraph<V>) directedGraphAnziehen);

            for (V vertex : topologicalOrder) {
                System.out.println(vertex.getNumber());
            }
        }
        /**
         *
         */
    private static class V {

        /**
         *
         */
        private final Integer number;

        /**
         *
         * @param num Nummer
         */
        public V(final int num) {
            this.number = num;
        }

        /**
         *
         * @return number
         */
        public int getNumber() {
            return number;
        }

        @Override
        public String toString() {
            return number.toString();
        }
    }
}
