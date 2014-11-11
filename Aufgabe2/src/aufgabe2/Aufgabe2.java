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
        Graph g = new AdjacencyListUndirectedGraph();
        Graph test = new AdjacencyListUndirectedGraph<>();
        
        for(int i = 0; i < n; i++){
            g.addVertex(new V_alt(5));
        }
        System.out.println(g.toString());
       // test.adjacencyList.get(4).put(g, new AdjacencyListUndirectedGraph<V>());  
    }
}
