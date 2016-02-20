package visualization.model;

import aStar.model.Node;
import aStar.model.Type;
import java.util.ArrayList;
import java.util.List;

public class Iteration {

    private final List<Node> nodes;

    public Iteration() {
        nodes = new ArrayList();
    }

    public void addVisitedNode(Node n) {
        if (n.getType() != Type.START_NODE) {
            n.setType(Type.VISITED);
        }
        Node node = new Node(n.getName(), n.getXPos(), n.getYPos(), n.getType());
        node.setGVal(n.getGVal());
        node.setHVal(n.getHVal());
        nodes.add(node);
    }

    public void addNeighborNode(Node n) {
        n.setType(Type.NEIGHBOUR);
        Node node = new Node(n.getName(), n.getXPos(), n.getYPos(), n.getType());
        node.setGVal(n.getGVal());
        node.setHVal(n.getHVal());
        nodes.add(node);
    }

    public List<Node> getNodes() {
        return nodes;
    }
}
