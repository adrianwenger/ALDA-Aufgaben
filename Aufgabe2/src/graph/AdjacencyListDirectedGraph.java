package graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author philippschultheiss, Adrian Wenger
 * @param <V>
 */
public class AdjacencyListDirectedGraph<V> implements DirectedGraph<V> {

    // Amount of edges;
    private int numOfEdge = 0;
    /**
     * stores the vertex and egdges in front.
     */
    private final HashMap<V, HashMap<V, Double>> adjacencyInput;
    /**
     * stores the vertex and edges after.
     */
    private final HashMap<V, HashMap<V, Double>> adjacencyOutput;

    /**
     * Public Constructor.
     */
    public AdjacencyListDirectedGraph() {
        this.adjacencyInput = new HashMap<>();
        this.adjacencyOutput = new HashMap<>();
    }

    @Override
    public final int getInDegree(final V v) {
        if (!containsVertex(v)) {
            throw new IllegalArgumentException("Knoten v nicht"
                    + " im Graph vorhanden");
        }
        return adjacencyInput.get(v).size();
    }

    @Override
    public final int getOutDegree(final V v) {
        if (!containsVertex(v)) {
            throw new IllegalArgumentException("Knoten v "
                    + "nicht im Graph vorhanden");
        }
        return adjacencyOutput.get(v).size();
    }

    @Override
    public final List<V> getPredecessorVertexList(final V v) {
        if (!containsVertex(v)) {
            throw new IllegalArgumentException("Knoten v "
                    + "nicht im Graph vorhanden");
        }
        List<V> vertList = new LinkedList<>();
        for (V vertex : adjacencyInput.get(v).keySet()) {
            vertList.add(vertex);
        }
        return vertList;
    }

    @Override
    public final List<V> getSuccessorVertexList(final V v) {
        if (!containsVertex(v)) {
            throw new IllegalArgumentException("Knoten v "
                    + "nicht im Graph vorhanden");
        }
        List<V> vertList = new LinkedList<>();
        for (V vertex : adjacencyOutput.get(v).keySet()) {
            vertList.add(vertex);
        }
        return vertList;
    }

    @Override
    public final List<Edge<V>> getOutgoingEdgeList(final V v) {
        if (!containsVertex(v)) {
            throw new IllegalArgumentException("Knoten v "
                    + "nicht im Graph vorhanden");
        }
        List<Edge<V>> edgList = new LinkedList<>();
        for (V vertex : adjacencyOutput.get(v).keySet()) {
            edgList.add(new Edge<>(v, vertex,
                    adjacencyOutput.get(v).get(vertex)));
        }
        return edgList;
    }

    @Override
    public final List<Edge<V>> getIncomingEdgeList(final V v) {
        if (!containsVertex(v)) {
            throw new IllegalArgumentException("Knoten v "
                    + "nicht im Graph vorhanden");
        }
        List<Edge<V>> edgList = new LinkedList<>();
        for (V vertex : adjacencyInput.get(v).keySet()) {
            edgList.add(new Edge<>(v, vertex,
                    adjacencyInput.get(v).get(vertex)));
        }
        return edgList;
    }

    @Override
    public final boolean addVertex(final V v) {
        // Checks if Vertex in Graph
        if (!adjacencyInput.containsKey(v)) {
            adjacencyInput.put(v, new HashMap<>());
            adjacencyOutput.put(v, new HashMap<>());
            return true;
        }
        return false;
    }

    @Override
    public final boolean addEdge(final V v, final V w) {
        return addEdge(v, w, 1.0);
    }

    @Override
    public final boolean addEdge(final V v, final V w, final double weight) {
        if (!containsEdge(v, w)) {
            adjacencyInput.get(w).put(v, weight);
            adjacencyOutput.get(v).put(w, weight);
            numOfEdge++;
            return true;
        }
        return false;
    }

    @Override
    public final boolean containsVertex(final V v) {
        return adjacencyInput.containsKey(v);
    }

    @Override
    public final boolean containsEdge(final V v, final V w) {
        if (!containsVertex(v) && !containsVertex(w) && v != w) {
            throw new IllegalArgumentException("Knoten v und w "
                    + "nicht im Graph vorhanden");
        }
        return adjacencyInput.get(w).containsKey(v);
    }

    @Override
    public final double getWeight(final V v, final V w) {
        if (containsEdge(v, w)) {
            return 0;
        }
        return adjacencyInput.get(v).get(w);
    }

    @Override
    public final int getNumberOfVertexes() {
        return adjacencyInput.size();
    }

    @Override
    public final int getNumberOfEdges() {
        return numOfEdge;
    }

    @Override
    public final List<V> getVertexList() {
        List<V> vertList = new LinkedList<>();
        for (V vertex : adjacencyInput.keySet()) {
            vertList.add(vertex);
        }
        return vertList;
    }

    @Override
    public final List<Edge<V>> getEdgeList() {
        List<Edge<V>> edgeList = new LinkedList<>();
        for (V vertex : adjacencyInput.keySet()) {
            HashMap<V, Double> map = adjacencyInput.get(vertex);
            for (V edge : map.keySet()) {
                edgeList.add(new Edge<>(vertex, edge, map.get(edge)));
            }
        }
        return edgeList;
    }

    @Override
    public final List<V> getAdjacentVertexList(final V v) {
        return getSuccessorVertexList(v);
    }

    @Override
    public final List<Edge<V>> getIncidentEdgeList(final V v) {
        return getOutgoingEdgeList(v);
    }

}
