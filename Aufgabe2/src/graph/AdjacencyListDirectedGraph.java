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
     * public constructor.
     */
    public AdjacencyListDirectedGraph() {
        this.adjacencyInput = new HashMap<>();
        this.adjacencyOutput = new HashMap<>();
    }

    @Override
    public final int getInDegree(final V v) {
        containsVertex(v);
        return adjacencyInput.get(v).size();
    }

    @Override
    public final int getOutDegree(final V v) {
        containsVertex(v);
        return adjacencyOutput.get(v).size();
    }

    @Override
    public final List<V> getPredecessorVertexList(final V v) {
        // Checks if Vertex in Graph
        containsVertex(v);
        List<V> vertList = new LinkedList<>();
        for (V vertex : adjacencyInput.get(v).keySet()) {
            vertList.add(vertex);
        }
        return vertList;
    }

    @Override
    public final List<V> getSuccessorVertexList(final V v) {
        // Checks if Vertex in Graph
        containsVertex(v);
        List<V> vertList = new LinkedList<>();
        for (V vertex : adjacencyOutput.get(v).keySet()) {
            vertList.add(vertex);
        }
        return vertList;
    }

    @Override
    public final List<Edge<V>> getOutgoingEdgeList(final V v) {
        // Checks if Vertex in Graph
        containsVertex(v);
        List<Edge<V>> edgList = new LinkedList<>();
        for (V vertex : adjacencyOutput.get(v).keySet()) {
            edgList.add(new Edge<>(v, vertex,
                    adjacencyOutput.get(v).get(vertex)));
        }
        return edgList;
    }

    @Override
    public final List<Edge<V>> getIncomingEdgeList(final V v) {
        // Checks if Vertex in Graph
        containsVertex(v);
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
        // Checks if Vertex in Graph
        containsVertex(v);
        if (!containsEdge(v, w)) {
            adjacencyInput.get(v).put(w, weight);
            adjacencyOutput.get(v).put(w, weight);
            numOfEdge++;
            return true;
        }
        return false;
    }

    @Override
    public final boolean containsVertex(final V v) {
        if (!adjacencyInput.containsKey(v)) {
            if (!adjacencyOutput.containsKey(v)) {
                throw new IllegalArgumentException("Vertex not in graph!");
            }
        }
        return true;
    }

    @Override
    public final boolean containsEdge(final V v, final V w) {
        // Checks if Vertex in Graph
        containsVertex(v);
        if (adjacencyInput.get(v).containsKey(w)) {
            return true;
        }
        return false;
    }

    @Override
    public final  double getWeight(final V v, final V w) {
        // Checks if Vertex in Graph
        containsVertex(v);
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
                edgeList.add(new Edge<V>(vertex, edge, map.get(edge)));
            }
        }
        return edgeList;
    }

    @Override
    public final List<V> getAdjacentVertexList(final V v) {
        containsVertex(v);
        List<V> vertList = new LinkedList<>();
        for (V vertex : adjacencyInput.get(v).keySet()) {
            vertList.add(vertex);
        }
        return vertList;
    }

    @Override
    public final List<Edge<V>> getIncidentEdgeList(final V v) {
        containsVertex(v);
        List<Edge<V>> edgList = new LinkedList<>();
        for (V vertex : adjacencyInput.get(v).keySet()) {
            edgList.add(new Edge<>(v, vertex,
                    adjacencyInput.get(v).get(vertex)));
        }
        return edgList;
    }

}
