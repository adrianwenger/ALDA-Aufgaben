package graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Adrian Wenger
 * @param <V> generic
 */
public class DijkstraShortestPath<V> {

    /**
     * graph to be searched.
     */
    private final Graph<V> graph;

    /**
     * saves the shortest Path.
     */
    private List<V> shortestPath;

    /**
     * saves the lentgh of the shortest path.
     */
    private double distance;

    /**
     *
     */
    private boolean success = false;

    /**
     * Constructor.
     *
     * @param g Graph
     */
    public DijkstraShortestPath(final Graph<V> g) {
        this.graph = g;
    }

    /**
     * searches the shortest way from s to g.
     *
     * @param s start vertex
     * @param z target vertex
     * @return boolean value
     */
    public final boolean searchShortestPath(final V s, final V z) {
        // candidate list
        List<V> kl = graph.getAdjacentVertexList(s);
        // Distance Map
        double[] d = new double[graph.getNumberOfVertexes()];
        // predecessor Array
        Map<V, V> p = new HashMap<>();

        List<V> vertList = graph.getVertexList();
        // walk through every vetex and set d[v] = endless
        for (int i = 0; i < graph.getNumberOfVertexes(); i++) {
            // alike endless
            d[i] = Double.MAX_VALUE;
        }

        // put in p[v] every vertex with  null value
        for (V vertex : vertList) {
            p.put(vertex, null);
        }

        // save Startvertex at point d[s]
        int i = 0;
        // find index of start Vertex and count i++ till reached;
        for (V vertex : vertList) {
            if (vertex.equals(s)) {
                break;
            }
            i++;
        }
        d[i] = 0;
        // add startvertex into candidate list
        kl.add(s);

        // walk through candidate list
        while (!kl.isEmpty()) {
            double minDist = Double.MAX_VALUE;
            int index = 0;
            for (V candidate : kl) {
                double j = 0;
                i = vertList.lastIndexOf(candidate);
                // vertrex at position i is same than traget Vertrex z
                if (vertList.get(i).equals(z)) {
                    //distance at d[i] < minDist
                    if (d[i] < minDist) {
                        // set minDist to d[i]
                        minDist = d[i];
                        // set index to i
                        index = i;
                    }
                    continue;
                }
                // 
                if ((d[i] + graph.getWeight(vertList.get(i), z)) < minDist) {
                    minDist = d[i] + graph.getWeight(vertList.get(i), z);
                    index = i;
                }
            }
            // save vertrex at position index (min vertex)
            V vertex = vertList.get(index);
            // if vertex equals traget vertrex z
            // push the vertrex into shortestPath
            if (vertex.equals(z)) {
                LinkedList<V> l = new LinkedList<>();
                l.push(z);
                V help = p.get(z);
                // help == traget vertex
                // ipush help to shortestPath and set help
                // to a new help from p
                while (help != s) {
                    l.push(help);
                    help = p.get(help);
                }
                // push help at last
                l.push(help);
                this.shortestPath = l;
                // shortest path found
                this.distance = minDist; 
                success = true;
                return true;
            }
            // remove vertrex of candidates
            kl.remove(vertex);
            // every adjacent vertex w to v
            for (V v : graph.getAdjacentVertexList(vertex)) {
                i = 0;
                // walk through vertexList till v reached with i++
                for (V w : vertList) {
                    if (w.equals(v)) {
                        break;
                    }
                    i++;
                }
                // not visited and not in kl
                if (d[i] == Double.MAX_VALUE) {
                    // add into candidate list kl
                    kl.add(vertList.get(i));
                }
                if ((d[index] + graph.getWeight(vertex, v)) < d[i]) {
                    p.put(v, vertex);
                    //d[i] = d[index] + graph.getWeight(vertex, v);
                    d[i] = d[index] + graph.getWeight(vertex, v);
                }
            }
        }
        success = false;
        return false;
    }

    /**
     * Returns shortest way.
     *
     * @return List
     */
    public final List<V> getShortestPath() {
        if (!success) {
            throw new IllegalStateException("kein Weg vorhanden");
        }
        return shortestPath;
    }

    /**
     * return length of shortest way.
     *
     * @return length shortest way
     */
    public final double getDistance() {
        if (!success) {
            throw new IllegalStateException("kein Weg vorhanden");
        }
        return distance;
    }

}
