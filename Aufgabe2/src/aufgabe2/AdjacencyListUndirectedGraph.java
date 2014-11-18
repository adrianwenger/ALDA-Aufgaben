package aufgabe2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author philippschultheiss
 */
public class AdjacencyListUndirectedGraph<V> implements UndirectedGraph<V> {

    private int numOfEdge = 0;
    HashMap<V, HashMap<V, Double>> adjacencyList;

    public AdjacencyListUndirectedGraph() {
        this.adjacencyList = new HashMap<>();
    }

    @Override
    public int getDegree(V v) {
        int i = 0;
        for (V vertex : adjacencyList.get(v).keySet()) {
            i++;
        }
        return i;
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
        return addEdge(v, w, 1.0);
    }

    @Override
    public boolean addEdge(V v, V w, double weight) {
        if (containsVertex(v) && containsVertex(w) && v != w) {
            if (!containsEdge(v, w)) {
                adjacencyList.get(v).put(v, weight);
                numOfEdge++;
                return true;
            }
            return false;
        } else {
            throw new IllegalArgumentException("einer der Knoten nicht im Graph vorhanden oder Knoten identisch");
        }
    }

    @Override
    public boolean containsVertex(V v) {
        return adjacencyList.containsKey(v);
    }

    @Override
    public boolean containsEdge(V v, V w) {
        if (containsVertex(v) && containsVertex(w)) {
            return adjacencyList.get(v).containsKey(w);
        } else {
            throw new IllegalArgumentException("Knoten nicht im Graphen vorhanden");
        }
    }

    @Override
    public double getWeight(V v, V w) {
        if (containsVertex(v) && containsVertex(w)) {
            if (containsEdge(v, w)) {
                return adjacencyList.get(v).get(w);
            } else {
                return 0;
            }
        }
        throw new IllegalArgumentException("Knoten nicht im Graphen vorhanden");
    }

    @Override
    public int getNumberOfVertexes() {
        return adjacencyList.size();
    }

    @Override
    public int getNumberOfEdges() {
        return numOfEdge;
    }

    @Override
    public List<V> getVertexList() {
        List<V> vertList = new LinkedList<>();
        for (V vertex : adjacencyList.keySet()) {
            vertList.add(vertex);
        }
        return vertList;
    }

    @Override
    public List<Edge<V>> getEdgeList() {
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
    public List<V> getAdjacentVertexList(V v) {
        if (containsVertex(v)) {
            List<V> vertList = new LinkedList<>();
            for (V vertex : adjacencyList.get(v).keySet()) {
                vertList.add(vertex);
            }
            return vertList;
        } else {
            throw new IllegalArgumentException("Knoten v kommt nicht im Graphen vor");
        }
    }

    @Override
    public List<Edge<V>> getIncidentEdgeList(V v) {
        if (containsVertex(v)) {
            List<Edge<V>> edgList = new LinkedList<>();
            for (V vertex : adjacencyList.get(v).keySet()) {
                edgList.add(new Edge<>(v, vertex, adjacencyList.get(v).get(vertex)));
            }
            return edgList;
        } else {
            throw new IllegalArgumentException("Knoten v kommt nicht im Graphen vor");
        }
    }
}
