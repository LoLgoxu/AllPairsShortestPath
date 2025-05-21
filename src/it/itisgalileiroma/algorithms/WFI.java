package it.itisgalileiroma.algorithms;

import it.itisgalileiroma.models.Graph;
import it.itisgalileiroma.models.Node;

import java.util.List;

public class WFI {

    /**
     * Executes the Floyd-Warshall algorithm on the given graph.
     * @param graph the graph on which to compute shortest paths
     * @return a 2D array representing the shortest path distances between all pairs of nodes
     */
    public static int[][] computeShortestPaths(Graph graph) {
        List<Node> nodes = graph.nodes();
        int n = nodes.size();
        int[][] dist = new int[n][n];

        // Initialize the distance matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    dist[i][j] = 0; // Distance to self is 0
                } else {
                    dist[i][j] = Integer.MAX_VALUE; // Initialize with "infinity"
                }
            }
        }

        // Set initial distances based on edges
        graph.edges().forEach(edge -> {
            int sourceIndex = getNodeIndex(nodes, edge.source().id());
            int targetIndex = getNodeIndex(nodes, edge.target().id());
            dist[sourceIndex][targetIndex] = edge.weight();
        });

        // Floyd-Warshall algorithm
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        return dist;
    }

    /**
     * Helper method to get the index of a node in the list by its ID.
     * @param nodes the list of nodes
     * @param id the ID of the node
     * @return the index of the node in the list
     */
    private static int getNodeIndex(List<Node> nodes, int id) {
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).id() == id) {
                return i;
            }
        }
        throw new IllegalArgumentException("Node with ID " + id + " not found in the graph.");
    }


}
