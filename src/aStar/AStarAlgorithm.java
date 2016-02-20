package aStar;

import aStar.heuristic.IHeuristic;
import aStar.model.Edge;
import aStar.model.Node;
import aStar.model.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import visualization.model.Iteration;

/**
 * Todo: Refresh queue when Gval is updated
 *
 * @author Tobias Jacobsen
 */
public class AStarAlgorithm {

    private final IHeuristic heuristic;
    private final List<Node> curNodes;
    private final List<Iteration> iterations;
    private int step;

    public AStarAlgorithm(IHeuristic heuristic) {
        this.heuristic = heuristic;
        curNodes = new ArrayList();
        iterations = new ArrayList();
    }

    public Iterable<Node> findShortestPath(Node start, Node goal) {
        Queue<Node> openSet = new PriorityQueue();
        Set<Node> closedSet = new HashSet();

        start.setGVal(0);
        start.setType(Type.START_NODE);
        goal.setType(Type.GOAL_NODE);

        Node curNode = start;
        step = 1;

        while (true) {
            System.out.println("Considering: " + curNode);
            Iteration iteration = new Iteration();
            if (step == 1) {
                curNode.setType(Type.START_NODE);
            }
            for (Edge edge : curNode) {
                Node other = edge.getEnd();
                if (!closedSet.contains(other)) {

                    double newG = edge.getWeight() + curNode.getGVal();
                    if (other.getGVal() > newG) {
                        other.setGVal(newG);
                        other.setPrev(curNode);

                        // refresh priority
                        if (openSet.contains(other)) {
                            openSet.remove(other);
                            openSet.add(other);
                        }

                    }
                    if (!openSet.contains(other)) {
                        other.setHVal(heuristic.getMinimumDist(other, goal));
                        openSet.add(other);
                    }
                    iteration.addNeighborNode(other);
                }
            }
            if (openSet.isEmpty()) {
                System.out.println("OpenSet is empty");
                return null;
            }

            closedSet.add(curNode);
            iteration.addVisitedNode(curNode);
            iterations.add(iteration);

            curNode = openSet.poll();

            {
                if (curNode == goal) {
                    ArrayList<Node> res = new ArrayList();
                    do {
                        if (curNode.getXPos() == goal.getXPos() && curNode.getYPos() == goal.getYPos()) {
                            curNode.setType(Type.GOAL_NODE);
                        }
                        res.add(curNode);
                        curNode = curNode.getPrev();
                    } while (curNode != null);
                    Collections.reverse(res);
                    return res;
                }
            }
            step++;
        }
    }

    public List<Node> getCurNodes() {
        return curNodes;
    }

    public int getSteps() {
        return step;
    }

    public List<Iteration> getIterations(int step) {
        List<Iteration> tempList = new ArrayList();
        for (int i = 0; i < step; i++) {
            tempList.add(iterations.get(i));
        }
        return tempList;
    }
}
