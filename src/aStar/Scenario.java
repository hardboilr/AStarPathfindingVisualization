package aStar;

import aStar.heuristic.EulerHeristic;
import aStar.heuristic.IHeuristic;
import aStar.model.Graph;
import aStar.model.Node;
import static aStar.model.Node.getNodeNeighbour;
import aStar.model.Type;
import java.util.List;

public class Scenario {

    private final int sizeX;
    private final int sizeY;
    private final List<Node> blockNodes;

    public Scenario(int sizeX, int sizeY, List<Node> blockNodes) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;

//        System.out.println("Scenario, sizeX: " + sizeX);
//        System.out.println("Scenario, sizeY: " + sizeY);

        this.blockNodes = blockNodes;
    }

    public Node[][] createScenario() {
        IHeuristic h = new EulerHeristic();
        Graph graph = new Graph();
        Node[][] nodes = new Node[sizeX][sizeY];

        // create nodes
        for (int y = 0; y < sizeY; ++y) {
            for (int x = 0; x < sizeX; ++x) {

                boolean canPass = true;
                for (Node node : blockNodes) {
                    if (x == node.getXPos() && y == node.getYPos()) {
//                        System.out.println("Found block node: " + x + "," + y);
                        nodes[x][y] = null;
                        canPass = false;
                        break;
                    }
                }
                if (canPass) {
//                    System.out.println("found regular node: " + x + "," + y);
                    nodes[x][y] = graph.createNode("(" + x + "," + y + ")", x, y, Type.REGULAR_NODE);
                }
            }
        }

        // set neighbour nodes
        for (int y = 0; y < sizeY; ++y) {
            for (int x = 0; x < sizeX; ++x) {
                Node m = nodes[x][y];
                if (m != null) {
//                    System.out.println("setting neighbours");
                    //North
                    int nx = x;
                    int ny = y + 1;
                    getNodeNeighbour(graph, nodes, nx, ny, m, h);
                    //North-East
                    nx = x + 1;
                    ny = y + 1;
                    getNodeNeighbour(graph, nodes, nx, ny, m, h);
                    //East
                    nx = x + 1;
                    ny = y;
                    getNodeNeighbour(graph, nodes, nx, ny, m, h);
                    //South-East
                    nx = x + 1;
                    ny = y - 1;
                    getNodeNeighbour(graph, nodes, nx, ny, m, h);
                    //South
                    nx = x;
                    ny = y - 1;
                    getNodeNeighbour(graph, nodes, nx, ny, m, h);
                    //South-West
                    nx = x - 1;
                    ny = y - 1;
                    getNodeNeighbour(graph, nodes, nx, ny, m, h);
                    //West
                    nx = x - 1;
                    ny = y;
                    getNodeNeighbour(graph, nodes, nx, ny, m, h);
                    //North-West
                    nx = x - 1;
                    ny = y + 1;
                    getNodeNeighbour(graph, nodes, nx, ny, m, h);
                }
            }
        }
        return nodes;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }
}
