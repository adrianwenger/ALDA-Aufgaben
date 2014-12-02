package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
     * @param g target vertex
     * @return boolean value
     */
    public final boolean searchShortestPath(final V s, final V g) {
        // leere Kandidatenliste erstellen
        List<V> kl = new LinkedList<>();
        // Distance Array
        double[] d = new double[graph.getNumberOfVertexes()];
        // predecessor Array
        List<V> p = new ArrayList<>();

        // walk through every vetex
        for (int i = 0; i < graph.getNumberOfVertexes(); i++) {
            d[i] = Double.MAX_VALUE; // alike endless
            p.set(i, null);   // undefined value
        }
        // Startvertex
        int i = 0;
        for (V vertex : graph.getVertexList()) {
            if (vertex == s) {
                break;
            }
            i++;
        }
        d[i] = 0;
        kl.add(s);

        double j = 0;
        int index = 0;
        while (!kl.isEmpty()) {
            for (int k = 0; i < d.length; k++) {
                if (d[k] == 0) {
                    continue;
                }
                if (d[k] < j) {
                    j = d[k];
                    index = k;
                    d[k] = 0;
                }
            }
            V vertex = graph.getVertexList().get(index);
            kl.remove(graph.getVertexList().get(index));

            // target vertex reached --> shortest path found
            if (vertex == g) {
                //shortestPath = p;
                //distance = p.size();
                return true;
            }
            
            for (V v : graph.getAdjacentVertexList(vertex)) {
                i = 0;
                for (V vert : graph.getVertexList()) {
                    if (vert == v) {
                        break;
                    }
                    i++;
                }
                // not visited and not in kl
                if (d[i] == Double.MAX_VALUE) {
                    kl.add(graph.getVertexList().get(i));
                }
                if ((d[index] + graph.getWeight(vertex, v)) < d[i]) {
                    p.set(i, vertex);
                    d[i] = d[index] + graph.getWeight(vertex, v);
                }
            }
        }
        return false;
    }

    /**
     * Returns shortest way.
     *
     * @return List
     */
    public final List<V> getShortestPath() {

        return shortestPath;
    }

    /**
     * return length of shortest way.
     *
     * @return length shortest way
     */
    public final double getDistance() {

        return 0;
    }

}
