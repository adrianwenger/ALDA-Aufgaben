package aufgabe2;

import java.util.HashMap;

/**
 *
 * @author philippschultheiss
 */
public class Aufgabe2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int n = 7;
        Graph g = new Vertex(5);
        Graph test = new AdjacencyListUndirectedGraph<Graph>();
        
        for(int i = 0; i < n; i++){
        test.adjacencyList.put(g, new AdjacencyListUndirectedGraph<Graph>());
        }
        test.adjacencyList.get(4).put(g, new AdjacencyListUndirectedGraph<Graph>());  
    }
}
