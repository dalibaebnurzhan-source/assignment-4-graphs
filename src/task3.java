import java.util.*;

public class task3 {

    static Map<String, List<String>> graph = new HashMap<>();
    static Set<String> visited = new HashSet<>();

    public static void dfs(String node) {

        visited.add(node);
        System.out.print(node + " ");

        for (String neighbor : graph.get(node)) {

            if (!visited.contains(neighbor)) {
                dfs(neighbor);
            }
        }
    }

    public static void bfs(String start) {

        Queue<String> queue = new LinkedList<>();
        Set<String> visitedBFS = new HashSet<>();

        queue.add(start);
        visitedBFS.add(start);

        while (!queue.isEmpty()) {

            String current = queue.poll();

            System.out.print(current + " ");

            for (String neighbor : graph.get(current)) {

                if (!visitedBFS.contains(neighbor)) {

                    visitedBFS.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    public static void main(String[] args) {

        graph.put("A", Arrays.asList("C", "B", "D"));
        graph.put("B", Arrays.asList("A", "C", "E", "G"));
        graph.put("C", Arrays.asList("A", "B", "D"));
        graph.put("D", Arrays.asList("C", "A"));
        graph.put("E", Arrays.asList("G", "F", "B"));
        graph.put("F", Arrays.asList("G", "E"));
        graph.put("G", Arrays.asList("F", "B"));

        System.out.println("DFS:");
        dfs("A");

        System.out.println();

        System.out.println("BFS:");
        bfs("A");
    }
}