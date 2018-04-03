import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class userImpl implements userInterface {

    private final static String SINGLE_WHITE_SPACE=" ";

    Map<String, userGraphMap> visitedMap=new ConcurrentHashMap<>(); 

    @Override
    public String findShortestPath(String entryGate, String destGate, List<DirectedEdge> edges) {
        userGraphMap GraphMap;
        if(visitedMap.containsKey(entryGate)){
            GraphMap = visitedMap.get(entryGate);
        }else {
            GraphMap = new userGraphMap(edges);
            GraphMap.dijkstra(entryGate);
            visitedMap.put(entryGate,GraphMap);
        }

        List<Vertex> shortestPath= GraphMap.getShortestPath(destGate);
        return generatePathLine(shortestPath);
    }

    private String generatePathLine(List<Vertex> path){
        StringBuffer line = new StringBuffer();

        for(Vertex vertex:path){
            line.append(vertex.getName()).append(SINGLE_WHITE_SPACE);
        }
        line.append(": ").append(path.get(path.size()-1).getTime());
        return line.toString();
    }

}
