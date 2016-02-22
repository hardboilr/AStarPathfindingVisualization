package aStar.model;

import static aStar.Board.boardSizeX;
import static aStar.Board.boardSizeY;
import aStar.heuristic.IHeuristic;
import java.util.ArrayList;
import java.util.Iterator;

public class Node implements Iterable<Edge>, Comparable<Node> {

    private Type type;
    private String name;
    private final int xPos;
    private final int yPos;
    private Node prev;
    private double gVal;
    private double hVal;
    private final ArrayList<Edge> edges;

    public Node(String name, int xPos, int yPos, Type type) {
        this.type = type;
        this.name = name;
        this.xPos = xPos;
        this.yPos = yPos;
        prev = null;
        gVal = Double.POSITIVE_INFINITY;
        hVal = Double.POSITIVE_INFINITY;
        edges = new ArrayList();
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public double getGVal() {
        return gVal;
    }

    public void setGVal(double gVal) {
        this.gVal = gVal;
    }

    public double getFVal() {
        return gVal + hVal;
    }
    
    public double getHVal() {
        return hVal;
    }

    public void setHVal(double hVal) {
        this.hVal = hVal;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
    
    @Override
    public String toString() {
        return name + ": (" + xPos + ", " + yPos + ")";
    }

    @Override
    public Iterator<Edge> iterator() {
        return edges.iterator();
    }

    @Override
    public int compareTo(Node o) {
        if (this.getFVal() < o.getFVal()) {
            return -1;
        }
        if (this.getFVal() > o.getFVal()) {
            return 1;
        }
        if (this.getHVal() < o.getHVal()) {
            return -1;
        }
        if (this.getHVal() > o.getHVal()) {
            return 1;
        }
        return 0;
    }

    public static boolean isInside(int x, int y) {
        return (x >= 0 && x < boardSizeX && y >= 0 && y < boardSizeY);
    }

    public static void getNodeNeighbour(Graph g, Node[][] nodes, int x, int y, Node m, IHeuristic h) {
        if (isInside(x, y)) {
            Node n = nodes[x][y];
            if (n != null) {
                g.createEdge(m, n, h.getMinimumDist(m, n));
            }
        }
    }
}
