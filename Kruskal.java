import java.util.*;

class Edge implements Comparable<Edge> {
    int src, dest, cost;

    Edge(int s, int d, int c) {
        src = s;
        dest = d;
        cost = c;
    }

    public int compareTo(Edge other) {
        return this.cost - other.cost;
    }
}

class Kruskals{
    static int[] parent;

    static int find(int u) {
        if (parent[u] != u)
            parent[u] = find(parent[u]);
        return parent[u];
    }

    static void union(int u, int v) {
        parent[find(u)] = find(v);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of cities: ");
        int V = sc.nextInt();

        System.out.print("Enter number of connections: ");
        int E = sc.nextInt();

        List<Edge> edges = new ArrayList<>();

        System.out.println("Enter each connection as: city1 city2 cost");
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int cost = sc.nextInt();
            edges.add(new Edge(u, v, cost));
        }

        Collections.sort(edges); // Sort by cost

        parent = new int[V];
        for (int i = 0; i < V; i++)
            parent[i] = i;

        int totalCost = 0;
        System.out.println("\nEdge \tWeight");

        for (Edge e : edges) {
            if (find(e.src) != find(e.dest)) {
                union(e.src, e.dest);
                System.out.println(e.src + " - " + e.dest + "\t" + e.cost);
                totalCost += e.cost;
            }
        }

        System.out.println("Minimum Total Cost: " + totalCost);
    }
}

