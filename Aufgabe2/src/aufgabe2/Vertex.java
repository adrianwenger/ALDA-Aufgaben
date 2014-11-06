package aufgabe2;

import java.util.LinkedList;
import java.util.List;

public class Vertex
{
    public List<Edge> adjacents;
    public int weight;

    public Vertex(int weight)
    {
        //adjacents = new LinkedList<Edge>();
        this.weight = weight;
    }
}
