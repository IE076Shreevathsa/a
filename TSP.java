import java.util.*;
public class TSP {
static int minCost = Integer.MAX_VALUE;
static List<Integer> bestPath = new ArrayList<>();
static void tsp(int[][] graph, boolean[] visited, int pos, int n, int count, int
cost, int start, List<Integer> path) {
if (count == n && graph[pos][start] > 0) {
int totalCost = cost + graph[pos][start];
if (totalCost < minCost) {
minCost = totalCost;
bestPath = new ArrayList<>(path);
bestPath.add(start); // return to start
}
return;
}
for (int i = 0; i < n; i++) {
if (!visited[i] && graph[pos][i] > 0) {
visited[i] = true;
path.add(i);
tsp(graph, visited, i, n, count + 1, cost + graph[pos][i], start,
path);
visited[i] = false; // backtrack
path.remove(path.size() - 1);
}
}
}
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
// Step 1: Input
System.out.print("Enter number of cities: ");
int n = sc.nextInt();
int[][] graph = new int[n][n];
System.out.println("Enter the distance matrix row by row (space separated):");
for (int i = 0; i < n; i++)
for (int j = 0; j < n; j++)
graph[i][j] = sc.nextInt();
// Step 2: TSP with path tracking
boolean[] visited = new boolean[n];
visited[0] = true;
List<Integer> path = new ArrayList<>();
path.add(0); // start from city 0
tsp(graph, visited, 0, n, 1, 0, 0, path);// Step 3: Output
System.out.println("Minimum cost: " + minCost);
System.out.print("Optimal path: ");
for (int city : bestPath) {
System.out.print(city + " ");
}
sc.close();
}
}
