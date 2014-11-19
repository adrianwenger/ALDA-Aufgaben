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
    HashMap<V, HashMap<V, Double>> adjacencyInput;
    HashMap<V, HashMap<V, Double>> adjacencyOutput;

    public AdjacencyListDirectedGraph() {
        this.adjacencyInput = new HashMap<>();
        this.adjacencyOutput = new HashMap<>();
    }

    @Override
    public int getInDegree(V v) {
        if (!containsVertex(v)) {
            throw new IllegalArgumentException("Knoten v nicht im Graph vorhanden");
        }
        containsVertex(v);
        return adjacencyInput.get(v).size();
    }

    @Override
    public int getOutDegree(V v) {
        if (!containsVertex(v)) {
            throw new IllegalArgumentException("Knoten v nicht im Graph vorhanden");
        }
        containsVertex(v);
        return adjacencyOutput.get(v).size();
    }

    @Override
    public List<V> getPredecessorVertexList(V v) {
        if (!containsVertex(v)) {
            throw new IllegalArgumentException("Knoten v nicht im Graph vorhanden");
        }
        // Checks if Vertex in Graph 
        containsVertex(v);
        List<V> vertList = new LinkedList<>();
        for (V vertex : adjacencyInput.get(v).keySet()) {
            vertList.add(vertex);
        }
        return vertList;
    }

    @Override
    public List<V> getSuccessorVertexList(V v) {
        if (!containsVertex(v)) {
            throw new IllegalArgumentException("Knoten v nicht im Graph vorhanden");
        }
        // Checks if Vertex in Graph 
        containsVertex(v);
        List<V> vertList = new LinkedList<>();
        for (V vertex : adjacencyOutput.get(v).keySet()) {
            vertList.add(vertex);
        }
        return vertList;
    }

    @Override
    public List<Edge<V>> getOutgoingEdgeList(V v) {
        if (!containsVertex(v)) {
            throw new IllegalArgumentException("Knoten v nicht im Graph vorhanden");
        }
        // Checks if Vertex in Graph 
        containsVertex(v);
        List<Edge<V>> edgList = new LinkedList<>();
        for (V vertex : adjacencyOutput.get(v).keySet()) {
            edgList.add(new Edge<>(v, vertex, adjacencyOutput.get(v).get(vertex)));
        }
        return edgList;
    }

    @Override
    public List<Edge<V>> getIncomingEdgeList(V v) {
        if (!containsVertex(v)) {
            throw new IllegalArgumentException("Knoten v nicht im Graph vorhanden");
        }
        // Checks if Vertex in Graph 
        containsVertex(v);
        List<Edge<V>> edgList = new LinkedList<>();
        for (V vertex : adjacencyInput.get(v).keySet()) {
            edgList.add(new Edge<>(v, vertex, adjacencyInput.get(v).get(vertex)));
        }
        return edgList;
    }

    @Override
    public boolean addVertex(V v) {
        // Checks if Vertex in Graph 
        if (!adjacencyInput.containsKey(v)) {
            adjacencyInput.put(v, new HashMap<>());
            adjacencyOutput.put(v, new HashMap<>());
            return true;
        }
        return false;
    }

    @Override
    public boolean addEdge(V v, V w) {
        return addEdge(v, w, 1.0);
    }

    @Override
    public boolean addEdge(V v, V w, double weight) {
        if (containsVertex(v) && containsVertex(w) && v != w) {
        // Checks if Vertex in Graph 
        containsVertex(v);
        if (!containsEdge(v, w)) {
            adjacencyInput.get(v).put(w, weight);
            adjacencyOutput.get(v).put(w, weight);
            numOfEdge++;
            return true;
        }
        return false;
    }else{
            throw new IllegalArgumentException("einer der Knoten nicht im"
                    + " Graph vorhanden oder Knoten identisch");
        }
    }

    @Override
    public boolean containsVertex(V v) {
        if (!adjacencyInput.containsKey(v)) {
            if (!adjacencyOutput.containsKey(v)) {
                throw new IllegalArgumentException("Vertex not in graph!");
            }
        }
        return true;
    }

    @Override
    public boolean containsEdge(V v, V w) {
        if(!containsVertex(v) && !containsVertex(w)){
            throw new IllegalArgumentException("Knoten v und w nicht im Graph vorhanden");
        }
        // Checks if Vertex in Graph 
        containsVertex(v);
        if (adjacencyInput.get(v).containsKey(w)) {
            return true;
        }
        return false;
    }

    @Override
    public double getWeight(V v, V w) {
        if(!containsVertex(v) && !containsVertex(w)){
            throw new IllegalArgumentException("Knoten v und w nicht im Graph vorhanden");
        }
        // Checks if Vertex in Graph 
        containsVertex(v);
        return adjacencyInput.get(v).get(w);
    }

    @Override
    public int getNumberOfVertexes() {
        return adjacencyInput.size();
    }

    @Override
    public int getNumberOfEdges() {
        return numOfEdge;
    }

    @Override
    public List<V> getVertexList() {
        List<V> vertList = new LinkedList<>();
        for (V vertex : adjacencyInput.keySet()) {
            vertList.add(vertex);
        }
        return vertList;
    }

    @Override
    public List<Edge<V>> getEdgeList() {
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
    public List<V> getAdjacentVertexList(V v) {
        if(!containsVertex(v)){
            throw new IllegalArgumentException("Knoten v nicht im Graph vorhanden");
        }
        containsVertex(v);
        List<V> vertList = new LinkedList<>();
        for (V vertex : adjacencyInput.get(v).keySet()) {
            vertList.add(vertex);
        }
        return vertList;
    }

    @Override
    public List<Edge<V>> getIncidentEdgeList(V v) {
        if(!containsVertex(v)){
            throw new IllegalArgumentException("Knoten v nicht im Graph vorhanden");
        }
        containsVertex(v);
        List<Edge<V>> edgList = new LinkedList<>();
        for (V vertex : adjacencyInput.get(v).keySet()) {
            edgList.add(new Edge<>(v, vertex, adjacencyInput.get(v).get(vertex)));
        }
        return edgList;
    }

}
