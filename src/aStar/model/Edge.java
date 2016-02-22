package aStar.model;

public class Edge {

    private final Node begin;
    private final Node end;
    private final double weight;

    public Edge(Node begin, Node end, double weight) {
        this.begin = begin;
        this.end = end;
        this.weight = weight;
        this.begin.addEdge(this);
    }

    public Node getBegin() {
        return begin;
    }

    public Node getEnd() {
        return end;
    }

    public double getWeight() {
        return weight;
    }
}
