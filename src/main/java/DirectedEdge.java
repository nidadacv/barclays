

public class DirectedEdge {
    private final Vertex source;
    private final Vertex destination;
    private final int time;

    public DirectedEdge(Vertex source, Vertex destination, int time) {
        this.source = source;
        this.destination = destination;
        this.time = time;
    }

    public DirectedEdge(String sourceName, String destinationName, Integer time) {
        this.source = new Vertex(sourceName);
        this.destination = new Vertex(destinationName);
        this.time = time;
    }

    public Vertex getSource() {
        return source;
    }

    public Vertex getDestination() {
        return destination;
    }

    public int getTime() {
        return time;
    }
}
