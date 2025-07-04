import java.util.*;

public class Dijkstra{
    static class Edge {
        int dest, cost;
        Edge(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }

    static void dijkstra(List<List<Edge>> graph, int V, int start) {
        int[] key = new int[V];           // Minimum distance from source
        int[] parent = new int[V];        // To trace path
        boolean[] inMST = new boolean[V]; // Same as visited[]

        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        key[start] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();
            int u = curr.dest;
            if (inMST[u]) continue;

            inMST[u] = true;

            for (Edge e : graph.get(u)) {
                if (!inMST[e.dest] && key[u] + e.cost < key[e.dest]) {
                    key[e.dest] = key[u] + e.cost;
                    parent[e.dest] = u;
                    pq.offer(new Edge(e.dest, key[e.dest]));
                }
            }
        }

        System.out.println("\nShortest distances from location " + start + ":");
        for (int i = 0; i < V; i++) {
            System.out.print("To " + i + " = " + key[i]);
            if (parent[i] != -1)
                System.out.print(" (via " + parent[i] + ")");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of locations: ");
        int V = sc.nextInt();

        System.out.print("Enter number of routes: ");
        int E = sc.nextInt();

        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++)
            graph.add(new ArrayList<>());

        System.out.println("Enter routes as: from to cost");
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt(), v = sc.nextInt(), cost = sc.nextInt();
            graph.get(u).add(new Edge(v, cost));
            // For undirected graph, also add:
            // graph.get(v).add(new Edge(u, cost));
        }

        System.out.print("Enter starting location: ");
        int start = sc.nextInt();

        dijkstra(graph, V, start);
    }
}

