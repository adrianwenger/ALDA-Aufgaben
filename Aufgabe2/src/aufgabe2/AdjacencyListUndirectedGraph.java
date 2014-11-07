package aufgabe2;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author philippschultheiss
 */
public class AdjacencyListUndirectedGraph<V> implements UndirectedGraph<V> {

    HashMap<V, HashMap<V, Double>> adjacencyList;

    public AdjacencyListUndirectedGraph() {
        this.adjacencyList = new HashMap<>();
    }

    @Override
    public int getDegree(V v) {
        return 1;
    }

    @Override
    public boolean addVertex(V v) {
        boolean returnValue = false;
        returnValue = containsVertex(v);
        //Insert Vertex
        if (!returnValue) {
            adjacencyList.put(v, new HashMap<V, Double>());
        }
        return returnValue;
    }

    @Override
    public boolean addEdge(V v, V w) {
        boolean containsEdge = containsEdge(v, w);
        if (!containsEdge) {
            adjacencyList.get(v).put(v, 1.0);
        }
        return containsEdge;
    }

    @Override
    public boolean addEdge(V v, V w, double weight) {
        try {
            if (containsVertex(v) && containsVertex(w) && v != w) {
                if (!containsEdge(v, w)) {
                    adjacencyList.get(v).put(v, weight);
                }
                return containsEdge(v, w);
            } else {
                throw new IllegalArgumentException("einer der Knoten nicht im Graph vorhanden oder Knoten identischÏ");
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e);
        } finally {
            return containsEdge(v, w);
        }
    }

    @Override
    public boolean containsVertex(V v) {
        return adjacencyList.containsKey(v);
    }

    @Override
    public boolean containsEdge(V v, V w) {
        return adjacencyList.get(v).containsKey(w);
    }

    @Override
    public double getWeight(V v, V w) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getNumberOfVertexes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getNumberOfEdges() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<V> getVertexList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Edge<V>> getEdgeList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<V> getAdjacentVertexList(V v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Edge<V>> getIncidentEdgeList(V v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
