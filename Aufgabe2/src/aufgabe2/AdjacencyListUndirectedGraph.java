package aufgabe2;

import java.util.HashMap;

/**
 *
 * @author philippschultheiss
 */
public class AdjacencyListUndirectedGraph<V> implements UndirectedGraph<V> {

   HashMap<V, HashMap<V, Double>> adjacencyList;
   
   public AdjacencyListUndirectedGraph(){
       this.adjacencyList = new HashMap<V, HashMap<V, Double>>();
   }
   
}
