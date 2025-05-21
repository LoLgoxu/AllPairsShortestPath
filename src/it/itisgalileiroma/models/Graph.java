package it.itisgalileiroma.models;

import java.util.ArrayList;
import java.util.List;

/***
 * A graph is characterized by
 * a list of nodes and a list of edges
 */

/**
 * Represents a graph consisting of a list of nodes and a list of edges.
 * Provides methods to add nodes and edges to the graph.
 */
public class Graph {

    private List<Node> nodes; // List of nodes in the graph
    private List<Edge> edges; // List of edges in the graph

    /**
     * Default constructor for Graph.
     */
    public Graph() {}

    /**
     * Constructs a Graph with the specified nodes and edges.
     * @param nodes the list of nodes
     * @param edges the list of edges
     */
    public Graph(List<Node> nodes, List<Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    /**
     * Constructs a Graph with the specified nodes and no edges.
     * @param nodes the list of nodes
     */
    public Graph(List<Node> nodes) {
        this.nodes = nodes;
        this.edges = new ArrayList<Edge>();
    }

    /**
     * Returns the list of nodes in the graph.
     * @return the list of nodes
     */
    public List<Node> nodes() {
        return nodes;
    }

    /**
     * Adds a node to the graph if it is not already present.
     * @param node the node to add
     * @return the index of the node in the graph
     */
    public int addNode(Node node) {
        boolean notEquals = true;
        int indexNode = -1; // we need to check the index of the node to insert (or alreay inserted)

        for(int i = 0; i < nodes.size(); i++) {
            if(nodes.get(i).id() == node.id()) {
                notEquals = false;
                indexNode = i;
                break;
            }
        }
        if(notEquals) {
            nodes.add(node);
            indexNode = nodes.size() - 1;
        }
        return indexNode;
    }

    /**
     * Adds an edge to the graph if it is not already present.
     * Also adds the edge's nodes to the graph if they are not already present.
     * @param edge the edge to add
     */
    public void addEdge(Edge edge) {
        boolean exists = false;
        for(int i = 0; i < edges.size(); i++) {
            // todo add a comment here!
            if((edge.target().id() == edges.get(i).target().id()) && (edge.source().id() == edges.get(i).source().id())) {
                exists = true;
                break;
            }
        }

        if(!exists) {
            // first we add  nodes if they are not already inserted
            // get the indices of the nodes because we
            // we need to set the neighbors of each node in the next statements
            // for each edge, target node is the neighbor of  source node
            // and, since we assume that tha graph is not directed,
            // for eache edge, source node is the neighbor of the target node
            int indexSourceNode = this.addNode(edge.source());
            int indexTargetNode = this.addNode(edge.target());

            this.nodes().get(indexSourceNode).addNeighbor(edge.target().id());
            this.nodes().get(indexTargetNode).addNeighbor(edge.source().id());

            Edge e = new Edge(edge.target(), edge.source(), edge.weight());
            edges.add(edge);
            edges.add(e);
        }
    }

    /**
     * Sets the list of nodes in the graph.
     * @param nodes the new list of nodes
     */
    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    /**
     * Returns the list of edges in the graph.
     * @return the list of edges
     */
    public List<Edge> edges() {
        return edges;
    }

    /**
     * Sets the list of edges in the graph.
     * @param edges the new list of edges
     */
    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }
}

