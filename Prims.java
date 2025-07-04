import java.util.*;

public class Prims{
    static class Edge {
        int dest, cost;
        Edge(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }

    static void prims(int V, List<List<Edge>> graph) {
        boolean[] inMST = new boolean[V];
        int[] key = new int[V];
        int[] parent = new int[V];
        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0;
        parent[0] = -1;

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));
        pq.offer(new Edge(0, 0));

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();
            int u = curr.dest;
            if (inMST[u]) continue;

            inMST[u] = true;

            for (Edge e : graph.get(u)) {
                if (!inMST[e.dest] && e.cost < key[e.dest]) {
                    key[e.dest] = e.cost;
                    parent[e.dest] = u;
                    pq.offer(new Edge(e.dest, key[e.dest]));
                }
            }
        }

        int total = 0;
        System.out.println("\nEdge\tWeight");
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + key[i]);
            total += key[i];
        }
        System.out.println("Minimum Total Cost: " + total);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of locations: ");
        int V = sc.nextInt();

        System.out.print("Enter number of routes: ");
        int E = sc.nextInt();

        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) graph.add(new ArrayList<>());

        System.out.println("Enter routes as: from to cost");
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt(), v = sc.nextInt(), cost = sc.nextInt();
            graph.get(u).add(new Edge(v, cost));
            graph.get(v).add(new Edge(u, cost));
        }

        prims(V, graph);
    }
}

