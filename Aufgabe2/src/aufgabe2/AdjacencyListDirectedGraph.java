package aufgabe2;

import java.util.HashMap;

/**
 *
 * @author philippschultheiss
 */
public class AdjacencyListDirectedGraph<V> implements DirectedGraph<V>{
    HashMap<V, HashMap<V, Integer>> adjacencyInput = new HashMap<V, HashMap<V, Integer>>();
    HashMap<V, HashMap<V, Integer>> adjacencyOuput = new HashMap<V, HashMap<V, Integer>>();
    
}
