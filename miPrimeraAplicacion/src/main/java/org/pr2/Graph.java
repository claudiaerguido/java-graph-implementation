package pr2.org;
import java.util.*;

public class Graph<V> {
    private Map<V, Set<V>> adjacencyList = new HashMap<>();

    public boolean addVertex(V v) {
        if (adjacencyList.containsKey(v)) {
            return false;
        }
        adjacencyList.put(v, new HashSet<>());
        return true;
    }

    public boolean addEdge(V v1, V v2) {
        addVertex(v1);
        addVertex(v2);
        if (adjacencyList.get(v1).contains(v2)) {
            return false;
        }
        adjacencyList.get(v1).add(v2);
        return true;
    }

    public Set<V> obtainAdjacents(V v) throws Exception {
        Set<V> adjacents = adjacencyList.get(v);
        if (adjacents == null) {
            throw new Exception("Vertex not found");
        }
        return adjacents;
    }

    public boolean containsVertex(V v) {
        return adjacencyList.containsKey(v);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (V vertex : adjacencyList.keySet()) {
            sb.append(vertex.toString()).append(": ");
            for (V adjacent : adjacencyList.get(vertex)) {
                sb.append(adjacent.toString()).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public List<V> onePath(V v1, V v2) {
        Map<V, V> trace = new HashMap<>();
        Stack<V> open = new Stack<>();
        open.push(v1);
        trace.put(v1, null);
        boolean found = false;

        while (!open.isEmpty() && !found) {
            V v = open.pop();
            found = v.equals(v2);

            if (!found) {
                for (V s : adjacencyList.get(v)) {
                    if (!trace.containsKey(s)) {
                        open.push(s);
                        trace.put(s, v);
                    }
                }
            }
        }

        if (found) {
            List<V> path = new ArrayList<>();
            V v = v2;
            while (v != null) {
                path.add(0, v);
                v = trace.get(v);
            }
            return path;
        } else {
            return null;
        }
    }
}
