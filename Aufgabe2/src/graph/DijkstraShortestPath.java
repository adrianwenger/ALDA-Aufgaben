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
        List<V> kl = new LinkedList<>();
        // Distance Array
        double[] d = new double[graph.getNumberOfVertexes()];
        // predecessor Array
        Map<V, V> p = new HashMap<>();

        // walk through every vetex d[v] = endless
        for (int i = 0; i < graph.getNumberOfVertexes(); i++) {
            d[i] = Double.MAX_VALUE; // alike endless
        }
        // put in p[v] = undef
        for (V vertex : graph.getVertexList()) {
            p.put(vertex, null);
        }

        // save Startvertex at point d[s]
        int i = 0;
        for (V vertex : graph.getVertexList()) {
            if (vertex.equals(s)) {
                break;
            }
            i++;
        }
        d[i] = 0;
        // add startvertex into candidate list
        kl.add(s);

        double j = 0;
        int index = 0;
        // walk through candidate list
        while (!kl.isEmpty()) {
            double minDist = Double.MAX_VALUE;
            for (V candidate : kl) {
                i = graph.getVertexList().lastIndexOf(candidate);
                if (graph.getVertexList().get(i).equals(z)) {
                    if (d[i] < minDist) {
                        minDist = d[i];
                        index = i;
                    }
                    continue;
                }
                // 
                if ((d[i] + graph.getWeight(graph.getVertexList().get(i), z)) < minDist) {
                    minDist = d[i] + graph.getWeight(graph.getVertexList().get(i), z);
                    index = i;
                }
            }
            // v with minimal d[v]
            V vertex = graph.getVertexList().get(index);
            kl.remove(vertex);

            if (vertex.equals(z)) {
                LinkedList<V> l = new LinkedList<>();
                l.push(z);
                V help = p.get(z);
                while (help != s) {
                    l.push(help);
                    help = p.get(help);
                }
                l.push(help);
                this.shortestPath = l;
                success = true;
                return true;
            }
            // every adjacent vertex w to v
            for (V v : graph.getAdjacentVertexList(vertex)) {
                i = 0;
                for (V w : graph.getVertexList()) {
                    if (w.equals(v)) {
                        break;
                    }
                    i++;
                }
                // not visited and not in kl
                if (d[i] == Double.MAX_VALUE) {
                    // add into candidate list kl
                    kl.add(graph.getVertexList().get(i));
                }
                if ((d[index] + graph.getWeight(vertex, v)) < d[i]) {
                    p.put(v, vertex);
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
