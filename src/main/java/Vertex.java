

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Vertex implements Comparable<Vertex>{
    private final String name;
    private int time =Integer.MAX_VALUE; 
    private Vertex prevVertext = null;
    private final Map<Vertex, Integer> neighbours = new HashMap<>();
    public Vertex(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setPrevVertext(Vertex prevVertext) {
        this.prevVertext = prevVertext;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Vertex getPrevVertext() {
        return prevVertext;
    }

    public Map<Vertex, Integer> getNeighbours() {
        return neighbours;
    }

    public int compareTo(Vertex other)
    {
        if (time == other.time)
            return name.compareTo(other.name);

        return Integer.compare(time, other.time);
    }

  

    public List<Vertex> getShortestPathTo()
    {
        List<Vertex> path = new ArrayList();
        path.add(this);
        Vertex vertex=this.getPrevVertext();
        while (vertex!=null && !path.contains(vertex)) {
            path.add(vertex);
            vertex=vertex.getPrevVertext();
        }

        Collections.reverse(path);
        return path;
    }

    @Override
    public String toString(){
        return this.name+":"+this.time;
    }
}
