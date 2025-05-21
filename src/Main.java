import it.itisgalileiroma.models.Edge;
import it.itisgalileiroma.models.Graph;
import it.itisgalileiroma.models.Node;
import it.itisgalileiroma.algorithms.WFI;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Main class to demonstrate the Floyd-Warshall algorithm on randomly generated graphs.
 * This program creates two random graphs, processes them in parallel using threads,
 * and prints the shortest path distances between all pairs of nodes.
 */
public class Main {
    public static void main(String[] args) {
        // Create two random graphs
        Graph graph1 = createRandomGraph(5, 7); // 5 nodes, 7 edges
        Graph graph2 = createRandomGraph(6, 10); // 6 nodes, 10 edges

        // Create threads for processing each graph
        Thread thread1 = new Thread(() -> processGraph(graph1, "Graph 1"));
        Thread thread2 = new Thread(() -> processGraph(graph2, "Graph 2"));

        // Start both threads
        thread1.start();
        thread2.start();

        // Wait for both threads to finish
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a random graph with the specified number of nodes and edges.
     * @param numNodes the number of nodes in the graph
     * @param numEdges the number of edges in the graph
     * @return a randomly generated graph
     */
    private static Graph createRandomGraph(int numNodes, int numEdges) {
        Random random = new Random();
        List<Node> nodes = new ArrayList<>();
        List<Edge> edges = new ArrayList<>();

        // Create nodes
        for (int i = 1; i <= numNodes; i++) {
            nodes.add(new Node(i));
        }

        // Create random edges
        for (int i = 0; i < numEdges; i++) {
            // Randomly select two different nodes to connect
            int sourceIndex = random.nextInt(numNodes);
            int targetIndex = random.nextInt(numNodes);

            // Ensure the source and target nodes are different
            while (sourceIndex == targetIndex) {
                targetIndex = random.nextInt(numNodes); // Avoid self-loops
            }

            int weight = random.nextInt(20) + 1; // Random weight between 1 and 20
            edges.add(new Edge(nodes.get(sourceIndex), nodes.get(targetIndex), weight));
        }

        // Create the graph
        Graph graph = new Graph(nodes);
        for (Edge edge : edges) {
            graph.addEdge(edge);
        }
        return graph;
    }

    /**
     * Processes a graph by computing its shortest path distances using the Floyd-Warshall algorithm
     * and printing the results to the console.
     * @param graph the graph to process
     * @param graphName the name of the graph (used for labeling output)
     */
    private static void processGraph(Graph graph, String graphName) {
        int[][] shortestPaths = WFI.computeShortestPaths(graph);

        // Print the shortest path distances
        synchronized (System.out) {
            System.out.println(graphName + " - Shortest path distances:");
            List<Node> nodes = graph.nodes();
            System.out.print("    ");
            for (Node node : nodes) {
                System.out.printf("%4d", node.id());
            }
            System.out.println();

            for (int i = 0; i < shortestPaths.length; i++) {
                System.out.printf("%4d", nodes.get(i).id());
                for (int j = 0; j < shortestPaths[i].length; j++) {
                    if (shortestPaths[i][j] == Integer.MAX_VALUE) {
                        System.out.print(" INF");
                    } else {
                        System.out.printf("%4d", shortestPaths[i][j]);
                    }
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
