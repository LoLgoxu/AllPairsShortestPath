package it.itisgalileiroma.models;

/***
 * Edge between two nodes (source and target)
 * The edge can be weighted
 */
public class Edge {

    private int weight;
    private Node source;
    private Node target;

    public Edge(int weight, Node source, Node target) {
        this.weight = weight;
        this.source = source;
        this.target = target;
    }

    public Edge(Node source, Node target, int weight) {
        this.weight = weight;
        this.source = source;
        this.target = target;
    }

    public Edge() {

    }

    /***
     *
     * @return weight of the edge
     */
    public int weight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     *
     * @return source node
     */
    public Node source() {
        return source;
    }

    public void setSource(Node source) {
        this.source = source;
    }

    /***
     *
     * @return target node
     */
    public Node target() {
        return target;
    }

    public void setTarget(Node target) {
        this.target = target;
    }
}
