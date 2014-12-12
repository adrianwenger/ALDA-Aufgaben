package graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author philippschultheiss
 * @param <V>
 */
public class AdjacencyListUndirectedGraph<V> implements UndirectedGraph<V> {
    /**
     * counts amount of Edges.
     */
    private int numOfEdge = 0;
    /**
     * 
     */
    private final HashMap<V, HashMap<V, Double>> adjacencyList;

    /**
     * public Constructor.
     */
    public AdjacencyListUndirectedGraph() {
        this.adjacencyList = new HashMap<>();
    }

    @Override
    public final int getDegree(final V v) {
        if (!containsVertex(v)) {
            throw new IllegalArgumentException("Vertex not in graph");
        }
        return adjacencyList.get(v).size();
    }

    @Override
    public final boolean addVertex(final V v) {
        // check if Vertex already exists
        boolean returnValue = containsVertex(v);
        //Insert Vertex
        if (!returnValue) {
            adjacencyList.put(v, new HashMap<>());
        }
        return returnValue;
    }

    @Override
    public final boolean addEdge(final V v, final V w) {
        return addEdge(v, w, 1.0);
    }

    @Override
    public final boolean addEdge(final V v, final V w, final double weight) {
        if (containsVertex(v) && containsVertex(w) && v != w) {
            if (!containsEdge(v, w)) {
                adjacencyList.get(v).put(w, weight);
                adjacencyList.get(w).put(v, weight);
                numOfEdge++;
                return true;
            }
            adjacencyList.get(v).put(w, weight);
            adjacencyList.get(w).put(v, weight);
            return false;
        } else {
            throw new IllegalArgumentException("einer der Knoten nicht im"
                    + " Graph vorhanden oder Knoten identisch");
        }
    }

    @Override
    public final boolean containsVertex(final V v) {
        return adjacencyList.containsKey(v);
    }

    @Override
    public final boolean containsEdge(final V v, final V w) {
        if (containsVertex(v) && containsVertex(w)) {
            return adjacencyList.get(v).containsKey(w);
        } else {
            throw new IllegalArgumentException("Kante nicht "
                    + "im Graphen vorhanden");
        }
    }

    @Override
    public final double getWeight(final V v, final V w) {
        if (!containsEdge(v, w)) {
            return 0;
        }
        return adjacencyList.get(v).get(w);
    }

    @Override
    public final int getNumberOfVertexes() {
        return adjacencyList.size();
    }

    @Override
    public  final int getNumberOfEdges() {
        return numOfEdge;
    }

    @Override
    public final List<V> getVertexList() {
        List<V> vertList = new LinkedList<>();
        for (V vertex : adjacencyList.keySet()) {
            vertList.add(vertex);
        }
        return vertList;
    }

    @Override
    public final List<Edge<V>> getEdgeList() {
        List<Edge<V>> edgList = new LinkedList<>();
        for (V vertex : adjacencyList.keySet()) {
            HashMap<V, Double> map = adjacencyList.get(vertex);
            for (V edge : map.keySet()) {
                edgList.add(new Edge<V>(vertex, edge, map.get(edge)));
            }
        }
        return edgList;
    }

    @Override
    public final List<V> getAdjacentVertexList(final V v) {
        if (containsVertex(v)) {
            List<V> vertList = new LinkedList<>();
            for (V vertex : adjacencyList.get(v).keySet()) {
                vertList.add(vertex);
            }
            return vertList;
        } else {
            throw new IllegalArgumentException("Knoten v kommt "
                    + "nicht im Graphen vor");
        }
    }

    @Override
    public final List<Edge<V>> getIncidentEdgeList(final V v) {
        if (containsVertex(v)) {
            List<Edge<V>> edgList = new LinkedList<>();
            for (V vertex : adjacencyList.get(v).keySet()) {
                edgList.add(new Edge<>(v, vertex,
                        adjacencyList.get(v).get(vertex)));
            }
            return edgList;
        } else {
            throw new IllegalArgumentException("Knoten v kommt nicht "
                    + "im Graphen vor");
        }
    }
}
