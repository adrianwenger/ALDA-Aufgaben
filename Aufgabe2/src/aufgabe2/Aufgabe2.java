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
            g.addVertex(new Vertex(5));
            test.addVertex(new Vertex(10));
        }
       // System.out.println(g.toString());
        System.out.println(test.toString());
    }
}
