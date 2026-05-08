import java.util.*;

public class DijkstraTask5 {

    static class Edge {
        String destination;
        int weight;

        Edge(String destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    static class Node {
        String city;
        int distance;

        Node(String city, int distance) {
            this.city = city;
            this.distance = distance;
        }
    }

    static Map<String, List<Edge>> graph = new HashMap<>();

    public static void addEdge(String source, String destination, int weight) {
        graph.putIfAbsent(source, new ArrayList<>());
        graph.putIfAbsent(destination, new ArrayList<>());

        graph.get(source).add(new Edge(destination, weight));
        graph.get(destination).add(new Edge(source, weight));
    }

    public static void dijkstra(String start, String end) {

        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();

        PriorityQueue<Node> pq =
                new PriorityQueue<>(Comparator.comparingInt(n -> n.distance));

        for (String city : graph.keySet()) {
            distances.put(city, Integer.MAX_VALUE);
        }

        distances.put(start, 0);

        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {

            Node current = pq.poll();

            String currentCity = current.city;

            for (Edge edge : graph.get(currentCity)) {

                int newDistance =
                        distances.get(currentCity) + edge.weight;

                if (newDistance < distances.get(edge.destination)) {

                    distances.put(edge.destination, newDistance);

                    previous.put(edge.destination, currentCity);

                    pq.add(new Node(edge.destination, newDistance));
                }
            }
        }

        List<String> path = new ArrayList<>();

        String step = end;

        while (step != null) {
            path.add(step);
            step = previous.get(step);
        }

        Collections.reverse(path);

        System.out.println("Shortest Path:");
        System.out.println(String.join(" -> ", path));

        System.out.println("Total Distance: "
                + distances.get(end));
    }

    public static void main(String[] args) {

        addEdge("Glasgow", "Stirling", 50);
        addEdge("Glasgow", "Edinburgh", 70);
        addEdge("Stirling", "Perth", 40);
        addEdge("Stirling", "Edinburgh", 50);
        addEdge("Perth", "Edinburgh", 100);
        addEdge("Perth", "Dundee", 60);

        dijkstra("Edinburgh", "Dundee");
    }
}