import java.util.*;

class Edge {
    int src, dest, w;
    Edge(int src, int dest, int w) {
        this.src = src;
        this.dest = dest;
        this.w = w;
    }
}

public class BF {
    static void bellmanFord(List<Edge> edges, int V, int source) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        for (int i = 1; i < V; i++) {
            for (Edge e : edges) {
                if (dist[e.src] != Integer.MAX_VALUE && dist[e.src] + e.w < dist[e.dest])
                    dist[e.dest] = dist[e.src] + e.w;
            }
        }

        System.out.println("\nVertex distances from source " + source + ":");
        for (int i = 0; i < V; i++) {
            if (dist[i] == Integer.MAX_VALUE)
                System.out.println("Vertex " + i + ": Unreachable");
            else
                System.out.println("Vertex " + i + ": " + dist[i]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int V = sc.nextInt();

        System.out.print("Enter the number of edges: ");
        int E = sc.nextInt();

        List<Edge> edges = new ArrayList<>();
        System.out.println("Enter edges (src dest weight):");
        for (int i = 0; i < E; i++) {
            int src = sc.nextInt();
            int dest = sc.nextInt();
            int w = sc.nextInt();
            edges.add(new Edge(src, dest, w));
            edges.add(new Edge(dest, src, w)); 
        }

        for (int i = 0; i < V; i++)
            bellmanFord(edges, V, i);

        sc.close();
    }
}

