package it.itisgalileiroma.models;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a node in a graph, identified by a unique integer ID.
 * Each node can have a list of neighboring node IDs.
 */
public class Node {
    private int id; // Unique identifier for the node
    private List<Integer> neighbors; // List of neighboring node IDs

    /**
     * Constructs a Node with the specified ID and neighbors.
     * @param id the unique ID of the node
     * @param neighbors the list of neighboring node IDs
     */
    public Node(int id, List<Integer> neighbors) {
        this.id = id;
        this.neighbors = neighbors;
    }

    /**
     * Constructs a Node with the specified ID and no neighbors.
     * @param id the unique ID of the node
     */
    public Node(int id) {
        this.id = id;
    }

    /**
     * Default constructor for Node.
     */
    public Node() {}

    /**
     * Returns the ID of the node.
     * @return the node ID
     */
    public int id() {
        return id;
    }

    /**
     * Sets the ID of the node.
     * @param id the new ID of the node
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the list of neighboring node IDs.
     * @return the list of neighbors
     */
    public List<Integer> neighbors() {
        return neighbors;
    }

    /**
     * Sets the list of neighboring node IDs.
     * @param neighbors the new list of neighbors
     */
    public void setNeighbors(List<Integer> neighbors) {
        this.neighbors = neighbors;
    }

    /**
     * Adds a neighbor to the node.
     * @param id the ID of the neighboring node to add
     */
    public void addNeighbor(int id) {
        if (this.neighbors == null) {
            this.neighbors = new ArrayList<>();
        }
        this.neighbors.add(id);
    }
}
