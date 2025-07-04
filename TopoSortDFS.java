import java.util.*;

public class TopoSortDFS {
    static void dfs(int u, List<List<Integer>> graph, boolean[] visited, Stack<Integer> stack) {
        visited[u] = true;
        for (int v : graph.get(u))
            if (!visited[v])
                dfs(v, graph, visited, stack);
        stack.push(u);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int n = sc.nextInt();

        System.out.print("Enter number of edges: ");
        int e = sc.nextInt();

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        System.out.println("Enter edges (from to):");
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph.get(u).add(v);
        }

        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++)
            if (!visited[i])
                dfs(i, graph, visited, stack);

        System.out.println("Topological Sort (DFS):");
        while (!stack.isEmpty())
            System.out.print(stack.pop());
    }
}

