package graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author philippschultheiss
 * @param <V>
 */
public final class GraphTraversion<V> {

    private GraphTraversion() {
    }

    /**
     * @param <V> Vertex Type
     * @param g graph
     * @param s start Vertex
     * @return LinkedList besucht
     */
    public static <V> List<V> depthFirstSearch(final Graph<V> g, final V s) {
        List<V> besucht = new LinkedList<>();
        return depthFirstSearchR(s, g, besucht);
    }

    private static <V> List<V> depthFirstSearchR(final V s, final Graph<V> g,
            List<V> besucht) {
        if (besucht.size() == g.getNumberOfVertexes()) {
            return besucht;
        }
        besucht.add(s);
        for (V vertex : g.getAdjacentVertexList(s)) {
            if (!besucht.contains(vertex)) {
                besucht = depthFirstSearchR(vertex, g, besucht);
            }
        }
        return besucht;
    }

    /**
     *
     * @param <V> Vertex Type
     * @param g graph
     * @param s vertex value
     * @return linkedList
     */
    public static <V> List<V> breadthFirstSearch(final Graph<V> g, final V s) {
        List<V> besucht = new LinkedList<>();
        return breadthFirstSearch(s, g, besucht);
    }

    /**
     *
     * @param <V>
     * @param s
     * @param g
     * @param besucht
     * @return
     */
    private static <V> List<V> breadthFirstSearch(V s, final Graph<V> g,
            final List<V> besucht) {
        Queue<V> schlange = new LinkedList<>();

        schlange.add(s);

        while (!schlange.isEmpty()) {
            s = schlange.remove();

            if (besucht.contains(s)) {
                continue;
            }
            besucht.add(s);

            for (V vertex : g.getAdjacentVertexList(s)) {
                if (!besucht.contains(vertex)) {
                    schlange.add(vertex);
                }
            }
        }
        return besucht;
    }

    /**
     * @param <V> Vertex type
     * @param g graph
     * @return LinkedList
     */
    public static <V> List<V> topologicalSort(final DirectedGraph<V> g) {
        throw new UnsupportedOperationException("not yet");
    }

}
