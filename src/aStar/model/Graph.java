package aStar.model;

import java.util.ArrayList;
import java.util.Collections;

public class Graph {

    private final ArrayList<Node> nodes;
    private final ArrayList<Edge> edges;

    public Graph() {
        this.nodes = new ArrayList();
        this.edges = new ArrayList();
    }

    public Iterable<Node> getNodes() {
        return Collections.unmodifiableList(nodes);
    }

    public Node createNode(String name, int xPos, int yPos, Type type) {
        Node res = new Node(name, xPos, yPos, type);
        nodes.add(res);
        return res;
    }

    public void createEdge(Node begin, Node end, double weight) {
        Edge edge = new Edge(begin, end, weight);
        edges.add(edge);
    }
}
